package com.elypia.elypiai.runescape;

import com.elypia.elypiai.common.core.*;
import com.elypia.elypiai.common.core.ext.WrapperExtension;
import com.elypia.elypiai.common.gson.deserializers.DateDeserializer;
import com.elypia.elypiai.runescape.deserializers.*;
import com.elypia.elypiai.runescape.impl.RuneScapeService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.*;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.*;

public class RuneScape extends ApiWrapper {

	private static final Logger logger = LoggerFactory.getLogger(RuneScape.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("https://apps.runescape.com/runemetrics/");
		} catch (MalformedURLException ex) {
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private RuneScapeService service;

	public RuneScape(WrapperExtension... exts) {
		this(BASE_URL, exts);
	}

	public RuneScape(URL baseUrl, WrapperExtension... exts) {
		super(exts);

		GsonBuilder gsonBuilder = new GsonBuilder()
			.registerTypeAdapter(Date.class, new DateDeserializer("dd-MMM-yyyy hh:mm"))
			.registerTypeAdapter(new TypeToken<List<QuestStats>>(){}.getType(), new QuestStatDeserializer());

		gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer(gsonBuilder.create()));

		service = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(RequestService.withExtensionInterceptor(this))
			.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
			.build()
			.create(RuneScapeService.class);
	}

	/**
	 * Return the RuneScape player with the username provided.
	 * Possible null, if user doesn't exist. If the user does exist
	 * but has their profile set to private, name will be "PROFILE_PRIVATE"
	 * and the rest of the object will be effectively null.
	 *
	 * @param username The username of the player to get.
	 * @return A rest action represeting this HTTP request.
	 */
	public RestAction<Player> getUser(String username) {
		Call<Player> call = service.getUser(username);
		return new RestAction<>(call);
	}

	public RestAction<List<QuestStats>> getQuestStatuses(String user) {
		Call<List<QuestStats>> call = service.getQuestStats(user);
		return new RestAction<>(call);
	}

    /**
     * Convert XP to the level equivilent.
     *
     * @param	xp      The xp to convert to level.
     * @return	        The level a player would be with the XP provided.
     */
	public static int parseXpAsLevel(long xp) {
		if (xp < 0)
			throw new IllegalArgumentException("XP (long) can not be of a negative value.");

		int level = 1;
		long result;

		while (xp >= (result = parseLevelAsXp(level + 1))) {
			if (result == -1)
				break;

			level++;
		}

		return level;
	}

	/**
	 * Convert a level, or virtual level to the XP equivilent using
	 * RuneScapes XP formula. <br>
	 * Note: Returns -1 if the level is too high.
	 *
	 * @param   level	The level to convert to XP.
	 * @return			The XP required to attain this level.
	 */
	public static long parseLevelAsXp(int level) {
		if (level < 1)
			throw new IllegalArgumentException("Level (int) can not be zero or a negative value.");

		double xp = 0;

		for (int count = 1; count < level; count++) {
			xp += (long)(count + 300 * Math.pow(2, (double)count / 7));

			if (xp >= (double)Long.MAX_VALUE * 4)
				return -1;
		}

		return (long)(xp / 4);
	}
}
