package com.elypia.elypiai.poe;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StashTabs {

	@SerializedName("next_change_id")
	private String cursor;

	@SerializedName("stashes")
	private List<Stash> stashes;

	public String getCursor() {
		return cursor;
	}

	public List<Stash> getStashes() {
		return stashes;
	}
}
