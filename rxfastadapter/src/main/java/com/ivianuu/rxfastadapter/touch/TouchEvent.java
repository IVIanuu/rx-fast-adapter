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

package com.ivianuu.rxfastadapter.touch;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;

/**
 * Touch event
 */
public class TouchEvent<T extends IItem> {

    private final View view;
    private final MotionEvent motionEvent;
    private final IAdapter<T> adapter;
    private final T item;
    private int position;

    TouchEvent(@NonNull View view,
               @NonNull MotionEvent motionEvent,
               @NonNull IAdapter<T> adapter,
               @NonNull T item,
               int position) {
        this.view = view;
        this.motionEvent = motionEvent;
        this.adapter = adapter;
        this.item = item;
        this.position = position;
    }

    /**
     * Returns the view
     */
    @NonNull
    public View view() {
        return view;
    }

    /**
     * Returns the motion event
     */
    public MotionEvent motionEvent() {
        return motionEvent;
    }

    /**
     * Returns the adapter
     */
    @NonNull
    public IAdapter<T> adapter() {
        return adapter;
    }

    /**
     * Returns the item
     */
    @NonNull
    public T item() {
        return item;
    }

    /**
     * Returns the position
     */
    public int position() {
        return position;
    }
}
