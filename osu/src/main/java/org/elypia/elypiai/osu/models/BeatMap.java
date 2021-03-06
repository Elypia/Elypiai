/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

package org.elypia.elypiai.osu.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.elypia.retropia.gson.deserializers.EmptyNullDeserializer;

import java.time.OffsetDateTime;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class BeatMap {

	/** The URL format for the previewable MP3 of an osu! beatmap. */
	private static final String PREVIEW_FORMAT = "https://b.ppy.sh/preview/%d.mp3";

	@SerializedName("approved")
	private MapStatus status;

	@SerializedName("submit_date")
	private OffsetDateTime submitDate;

	@SerializedName("approved_date")
	private OffsetDateTime approvedDate;

	@SerializedName("last_update")
	private OffsetDateTime lastUpdated;

	@SerializedName("artist")
	private String artist;

	@SerializedName("beatmap_id")
	private int id;

	@SerializedName("beatmapset_id")
	private int setId;

	@SerializedName("bpm")
	private int bpm;

	@SerializedName("creator")
	private String creator;

	@SerializedName("creator_id")
	private int creatorId;

	@SerializedName("hit_length")
	private int hitLength;

	@SerializedName("source")
	@JsonAdapter(EmptyNullDeserializer.class)
	private String source;

	@SerializedName("genre_id")
	private MapGenre genre;

	@SerializedName("language_id")
	private MapLanguage language;

	@SerializedName("title")
	private String title;

	@SerializedName("total_length")
	private int totalLength;

	@SerializedName("version")
	private String version;

	@SerializedName("file_md5")
	private String fileMd5;

	@SerializedName("mode")
	private OsuMode mode;

	@SerializedName("tags")
	private String tags;

	@SerializedName("favourite_count")
	private int favouriteCount;

	@SerializedName("playcount")
	private int playcount;

	@SerializedName("passcount")
	private int passcount;

	@SerializedName("max_combo")
	private int maxCombo;

	private MapDifficulty difficulty;

	/**
	 * @return	The maps current status.
	 */
	public MapStatus getStatus() {
		return status;
	}

	public OffsetDateTime getSubmissionDate() {
		return submitDate;
	}

	/**
	 * Date and time the map was approved in the format.
	 *
	 * @return 	Date the map was approved.
	 */
	public OffsetDateTime getApprovedDate() {
		return approvedDate;
	}

	/**
	 * Date and time the map was last updated in the format <br>
	 * "YYYY-MM-DD HH:MM:SS" (UTC+8)
	 *
	 * @return 	Date the map was last updated.
	 */

	public OffsetDateTime getLastUpdate() {
		return lastUpdated;
	}

	/**
	 * @return 	The artist of the beatmap.
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @return 	The beatmap id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return 	The id of the beatmap set. (All difficulties)
	 */
	public int getSetId() {
		return setId;
	}

	/**
	 * @return 	The BPM of the beatmap.
	 */
	public int getBPM() {
		return bpm;
	}

	/**
	 * @return 	The name of the creator of the beatmap.
	 */
	public String getCreator() {
		return creator;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public MapDifficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(MapDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @return 	The duration of the beatmap from the first note
	 * 			to the last, excluding breaks in between in seconds.
	 */
	public int getHitLength() {
		return hitLength;
	}

	public String getSource() {
		return source;
	}

	/**
	 * @return 	The genre for the song.
	 */
	public MapGenre getGenre() {
		return genre;
	}

	public MapLanguage getLanguage() {
		return language;
	}

	/**
	 * @return 	The name of the beatmap.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return 	The total length of the beatmap,
	 * 			inclusive of breaks in seconds.
	 */
	public int getTotalLength() {
		return totalLength;
	}

	/**
	 * @return 	The current difficulty's name.
	 */
	public String getVersion() {
		return version;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	/**
	 * @return 	What game mode this beatmap is for.
	 */
	public OsuMode getMode() {
		return mode;
	}

	/**
	 * The tags are space delimeted, which may cause confusion
	 * when the tags themselves contain spaces.
	 *
	 * @return	Tags associated with this beatmap.
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @return	Number of times this beatmap was favourtied.
	 */
	public int getFavouriteCount() {
		return favouriteCount;
	}

	/**
	 * @return	Number of times this beatmap was played.
	 */
	public int getPlayCount() {
		return playcount;
	}

	/**
	 * @return	Numvber of times the beatmap was completed.
	 * 			(Didn't fail or retry, finished the map.)
	 */
	public int getPassCount() {
		return passcount;
	}

	/**
	 * @return	The maximum combo possible on this beatmap.
	 */
	public int getMaxCombo() {
		return maxCombo;
	}

	/**
	 * @return The audio file preview as displayed on the website.
	 */
	public String getPreviewUrl() {
		return String.format(PREVIEW_FORMAT, setId);
	}
}
