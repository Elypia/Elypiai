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

package org.elypia.elypiai.orna.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Abstract class that represents any {@link AbstractEntity}
 * which has some kind a tier associated with it.
 *
 * @author seth@elypia.org (Syed Shah)
 */
public abstract class TieredEntity extends AbstractEntity {

    @SerializedName("tier")
    protected int tier;

    public int getTier() {
        return tier;
    }
}