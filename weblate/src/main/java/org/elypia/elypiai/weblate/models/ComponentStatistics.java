/*
 * Copyright 2019-2020 Elypia CIC
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

package org.elypia.elypiai.weblate.models;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.2
 */
public class ComponentStatistics extends Statistics {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("last_change")
    private Instant lastChange;

    @SerializedName("recent_changes")
    private int recentChanges;

    @SerializedName("fuzzy")
    private int fuzzy;

    @SerializedName("fuzzy_percent")
    private double fuzzyPercentage;

    @SerializedName("failing")
    private int failing;

    @SerializedName("failing_percent")
    private double failingPercentage;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Instant getLastChange() {
        return lastChange;
    }

    public int getRecentChanges() {
        return recentChanges;
    }

    public int getFuzzy() {
        return fuzzy;
    }

    public double getFuzzyPercentage() {
        return fuzzyPercentage;
    }

    public int getFailing() {
        return failing;
    }

    public double getFailingPercentage() {
        return failingPercentage;
    }
}
