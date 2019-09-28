/*
 * Copyright 2019-2019 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.twitch;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import org.elypia.elypiai.common.core.*;
import org.elypia.elypiai.common.core.data.AuthenticationType;
import org.elypia.elypiai.common.core.ext.ExtensionInterceptor;
import org.elypia.elypiai.common.gson.GsonService;
import org.elypia.elypiai.common.gson.deserializers.DateDeserializer;
import org.elypia.elypiai.twitch.data.Scope;
import org.elypia.elypiai.twitch.deserializers.*;
import org.elypia.elypiai.twitch.entity.User;
import org.elypia.elypiai.twitch.notifier.TwitchNotifier;
import org.elypia.elypiai.twitch.notifier.event.*;
import org.elypia.elypiai.twitch.service.*;
import org.elypia.webhooker.WebHooker;
import org.elypia.webhooker.controller.*;
import org.slf4j.*;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class Twitch extends ApiWrapper {

	private static Logger logger = LoggerFactory.getLogger(Twitch.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("https://api.twitch.tv/helix/");
		} catch (MalformedURLException ex) {
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private static URL AUTH_URL;

	static {
		try {
			AUTH_URL = new URL("https://id.twitch.tv/");
		} catch (MalformedURLException ex) {
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private final String CLIENT_ID;
	private final String CLIENT_SECRET;
	private final AuthenticationType AUTH_TYPE;
	private final Scope[] SCOPES;

	private Gson gson;
	private TwitchService service;
	private TwitchAppService appService;
	private AccessToken token;

	/**
	 * Allows calls to the Twitch API, can call various information
	 * on users, or get stream information if the user is live.
	 *
	 * @param clientId API key obtained from Twitch website.
	 */
	public Twitch(String clientId, String clientSecret) throws IOException {
		this(clientId, clientSecret, AuthenticationType.BEARER);
	}

	public Twitch(String clientId, String clientSecret, AuthenticationType grantType) throws IOException {
		this(clientId, clientSecret, grantType, (Scope[])null);
	}

	public Twitch(String clientId, String clientSecret, AuthenticationType grantType, Scope... scopes) throws IOException {
		this(BASE_URL, AUTH_URL, clientId, clientSecret, grantType, scopes);
	}

	public Twitch(URL baseUrl, URL authUrl, String clientID, String clientSecret, AuthenticationType grantType, Scope... scopes) throws IOException {
		Objects.requireNonNull(baseUrl);
		Objects.requireNonNull(authUrl);
		CLIENT_ID = Objects.requireNonNull(clientID);
		CLIENT_SECRET = Objects.requireNonNull(clientSecret);
		AUTH_TYPE = Objects.requireNonNull(grantType);
		SCOPES = scopes;

		initTwitchAppService(authUrl);
		initTwitchService(baseUrl);
	}

	private void initTwitchAppService(URL authUrl) throws IOException {
		appService = new Retrofit.Builder()
			.baseUrl(authUrl)
			.client(RequestService.withExtensionInterceptor(this))
			.addConverterFactory(GsonService.getInstance())
			.build()
			.create(TwitchAppService.class);

		Optional<AccessToken> optToken = getToken().complete();

		this.token = optToken.orElseThrow(() ->
			new IllegalStateException("Client credentials were invalid, refer to Twitch and generate new ones.")
		);
	}

	private void initTwitchService(URL baseUrl) {
		OkHttpClient client = RequestService.getBuilder()
			.addInterceptor((chain) -> {
				var req = chain.request().newBuilder()
					.addHeader("Authorization", "Bearer " + token.getToken())
					.build();

				return chain.proceed(req);
			})
			.addInterceptor(new ExtensionInterceptor(this))
			.build();

		GsonBuilder gsonBuilder = new GsonBuilder()
			.registerTypeAdapter(Date.class, new DateDeserializer("yyyy-MM-dd'T'HH:mm:ss'Z'"))
			.registerTypeAdapter(new TypeToken<List<User>>(){}.getType(), new TwitchUserDeserializer(this));

		Gson gson = gsonBuilder.create();

		gsonBuilder
				.registerTypeAdapter(FollowEvent.class, new FollowEventDeserializer(gson))
				.registerTypeAdapter(StreamUpdateEvent.class, new StreamEventDeserializer(gson))
				.registerTypeAdapter(UserUpdatedEvent.class, new UserEventDeserializer(gson));

		this.gson = gsonBuilder.create();

		service = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create(this.gson))
			.build()
			.create(TwitchService.class);
	}

	/**
	 * Use the users Client-Id and Client-Secret
	 * to create an app access token;
	 *
	 * @return A new access token.
	 */
	private RestAction<AccessToken> getToken() {
		Call<AccessToken> call = appService.getToken(
			CLIENT_ID,
			CLIENT_SECRET,
			AUTH_TYPE.getApiName(),
			Scope.forQuery(SCOPES)
		);

		return new RestAction<>(call);
	}

	public RestAction<List<User>> getUsers(TwitchQuery query) {
		if (!query.getGames().isEmpty())
			throw new IllegalArgumentException("Can not query users by game id.");

		if (query.getTotal() > 100)
			throw new IllegalArgumentException("Can not query over 100 entities at once.");

		Call<List<User>> call = service.getUsers(query.getUserIds(), query.getUsernames());
		return new RestAction<>(call);
	}

	public StreamPaginator getStreams(TwitchQuery query, int limit) {
		if (limit > 100)
			throw new IllegalArgumentException("Can not retrieve more than 100 users per page.");

		return new StreamPaginator(this, query, limit);
	}

	public TwitchNotifier createNotifier(String publicUrl) throws MalformedURLException {
		return createNotifier(publicUrl, 4567);
	}

	public TwitchNotifier createNotifier(String publicUrl, int port) throws MalformedURLException {
		return createNotifier(publicUrl, port, new MapClientController());
	}

	public TwitchNotifier createNotifier(String publicUrl, int port, ClientController controller) throws MalformedURLException {
		WebHooker hooker = new WebHooker(publicUrl, port, controller, gson);
		return new TwitchNotifier(this, hooker);
	}

	public String getClientId() {
		return CLIENT_ID;
	}

	public Gson getGson() {
		return gson;
	}

	/**
	 * Despite usually keeping service internal, the Twitch API has
	 * several components so there is an exception so those components
	 * can also access the TwitchService.
	 *
	 * @return The service class for making the actual Twitch API calls.
	 */
	public TwitchService getService() {
		return service;
	}
}