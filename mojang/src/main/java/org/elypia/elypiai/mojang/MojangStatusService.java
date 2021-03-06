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

package org.elypia.elypiai.mojang;

import io.reactivex.rxjava3.core.Single;
import org.elypia.elypiai.mojang.models.MojangServer;
import org.elypia.elypiai.mojang.models.ServerStatus;
import retrofit2.http.GET;

import java.util.Map;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public interface MojangStatusService {

    @GET("check")
    Single<Map<MojangServer, ServerStatus>> getServerStatuses();
}
