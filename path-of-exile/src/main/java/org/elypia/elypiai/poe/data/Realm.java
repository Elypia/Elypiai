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

package org.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public enum Realm {

    UNKNOWN("Unknown"),

    @SerializedName("pc")
    PC("PC"),

    @SerializedName("xbox")
    XBOX("Xbox"),

    @SerializedName("sony")
    SONY("Sony");

    private final String NAME;

    Realm(final String NAME) {
        this.NAME = NAME;
    }

    public String getName() {
        return NAME;
    }

    public static Realm get(String name) {
        for (Realm realm : values()) {
            if (realm.NAME.equals(name))
                return realm;
        }

        return UNKNOWN;
    }
}