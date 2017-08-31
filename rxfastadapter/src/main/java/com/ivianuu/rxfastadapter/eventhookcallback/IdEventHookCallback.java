/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.rxfastadapter.eventhookcallback;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Call back which searches the views by id
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public class IdEventHookCallback implements EventHookCallback {

    private final List<Integer> ids;

    private IdEventHookCallback(@NonNull List<Integer> ids) {
        this.ids = ids;
    }

    /**
     * This pass the views in on bind many
     */
    @NonNull
    public static EventHookCallback with(@NonNull Integer... ids) {
        return with(Arrays.asList(ids));
    }

    /**
     * This pass the views in on bind many
     */
    @NonNull
    public static EventHookCallback with(@NonNull List<Integer> ids) {
        if (ids.isEmpty()) {
            throw new IllegalArgumentException("you must specify atleast 1 id");
        }
        return new IdEventHookCallback(ids);
    }

    @Nullable
    @Override
    public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
        // ignore
        return null;
    }

    @Nullable
    @Override
    public List<View> onBindMany(@NonNull RecyclerView.ViewHolder viewHolder) {
        List<View> views = new ArrayList<>();
        for (Integer id : ids) {
            View view = viewHolder.itemView.findViewById(id);
            if (view != null) {
                views.add(view);
            }
        }

        return views;
    }
}
