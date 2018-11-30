package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.deserializers.RuneScapeDateDeserializer;
import com.google.gson.annotations.*;

import java.util.Date;

public class Activity {

    @SerializedName("date")
    @JsonAdapter(RuneScapeDateDeserializer.class)
    private Date date;

    @SerializedName("details")
    private String details;

    @SerializedName("text")
    private String text;

    public Date getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public String getText() {
        return text;
    }
}