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

package com.ivianuu.rxfastadapter.selection;

import android.support.annotation.NonNull;

import com.mikepenz.fastadapter.IItem;

/**
 * Selection event
 */
public class SelectionEvent<T extends IItem> {

    private final T item;
    private final boolean selected;

    SelectionEvent(@NonNull T item, boolean selected) {
        this.item = item;
        this.selected = selected;
    }

    /**
     * Returns the item
     */
    @NonNull
    public T item() {
        return item;
    }

    /**
     * Returns whether selected
     */
    public boolean selected() {
        return selected;
    }
}
