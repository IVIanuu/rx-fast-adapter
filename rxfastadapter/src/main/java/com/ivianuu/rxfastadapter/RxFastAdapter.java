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

package com.ivianuu.rxfastadapter;

import android.support.annotation.NonNull;

import com.ivianuu.rxfastadapter.click.ClickEvent;
import com.ivianuu.rxfastadapter.click.ClickObservable;
import com.ivianuu.rxfastadapter.clickeventhook.ClickEventHookEvent;
import com.ivianuu.rxfastadapter.clickeventhook.ClickEventHookObservable;
import com.ivianuu.rxfastadapter.eventhookcallback.EventHookCallback;
import com.ivianuu.rxfastadapter.longclick.LongClickEvent;
import com.ivianuu.rxfastadapter.longclick.LongClickObservable;
import com.ivianuu.rxfastadapter.longclickeventhook.LongClickEventHookEvent;
import com.ivianuu.rxfastadapter.longclickeventhook.LongClickEventHookObservable;
import com.ivianuu.rxfastadapter.selection.SelectionEvent;
import com.ivianuu.rxfastadapter.selection.SelectionObservable;
import com.ivianuu.rxfastadapter.touch.TouchEvent;
import com.ivianuu.rxfastadapter.touch.TouchFlowable;
import com.ivianuu.rxfastadapter.toucheventhook.TouchEventHookEvent;
import com.ivianuu.rxfastadapter.toucheventhook.TouchEventHookFlowable;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

/**
 * Rx Fast adapter
 */
public class RxFastAdapter {

    private RxFastAdapter() {
        // no instances
    }

    /**
     * Emits on clicks
     */
    @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> clicks(@NonNull FastAdapter<T> adapter) {
        return clicks(adapter, Functions.<ClickEvent<T>>always(true));
    }

    /**
     * Emits on clicks
     */
    @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> clicks(@NonNull FastAdapter<T> adapter,
                                                                     @NonNull Predicate<ClickEvent<T>> predicate) {
        return ClickObservable.create(adapter, predicate, false);
    }

    /**
     * Emits on pre clicks
     */
    @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> preClicks(@NonNull FastAdapter<T> adapter) {
        return preClicks(adapter, Functions.<ClickEvent<T>>always(true));
    }

    /**
     * Emits on pre clicks
     */
    @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> preClicks(@NonNull FastAdapter<T> adapter,
                                                                     @NonNull Predicate<ClickEvent<T>> predicate) {
        return ClickObservable.create(adapter, predicate, true);
    }

    /**
     * Emits on long clicks
     */
    @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> longClicks(@NonNull FastAdapter<T> adapter) {
        return longClicks(adapter, Functions.<LongClickEvent<T>>always(true));
    }

    /**
     * Emits on long clicks
     */
    @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> longClicks(@NonNull FastAdapter<T> adapter,
                                                                     @NonNull Predicate<LongClickEvent<T>> predicate) {
        return LongClickObservable.create(adapter, predicate, false);
    }

    /**
     * Emits on pre long clicks
     */
    @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> preLongClicks(@NonNull FastAdapter<T> adapter) {
        return preLongClicks(adapter, Functions.<LongClickEvent<T>>always(true));
    }

    /**
     * Emits on pre long clicks
     */
    @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> preLongClicks(@NonNull FastAdapter<T> adapter,
                                                                                @NonNull Predicate<LongClickEvent<T>> predicate) {
        return LongClickObservable.create(adapter, predicate, true);
    }

    /**
     * Emits on touches
     */
    @NonNull
    public static <T extends IItem> Flowable<TouchEvent<T>> touches(@NonNull FastAdapter<T> adapter) {
        return touches(adapter, Functions.<TouchEvent<T>>always(true));
    }

    /**
     * Emits on touches
     */
    @NonNull
    public static <T extends IItem> Flowable<TouchEvent<T>> touches(@NonNull FastAdapter<T> adapter,
                                                                      @NonNull Predicate<TouchEvent<T>> predicate) {
        return touches(adapter, predicate, BackpressureStrategy.BUFFER);
    }

    /**
     * Emits on touches
     */
    @NonNull
    public static <T extends IItem> Flowable<TouchEvent<T>> touches(@NonNull FastAdapter<T> adapter,
                                                                    @NonNull Predicate<TouchEvent<T>> predicate,
                                                                    @NonNull BackpressureStrategy backpressureStrategy) {
        return TouchFlowable.create(adapter, predicate, backpressureStrategy);
    }

    /**
     * Emits on selection changes
     */
    @NonNull
    public static <T extends IItem> Observable<SelectionEvent<T>> selections(@NonNull FastAdapter<T> adapter) {
        return SelectionObservable.create(adapter);
    }

    /**
     * Emits on click events
     */
    @NonNull
    public static <T extends IItem> Observable<ClickEventHookEvent<T>> clickEventHooks(@NonNull FastAdapter<T> adapter,
                                                                                       @NonNull EventHookCallback callback) {
        return ClickEventHookObservable.create(adapter, callback);
    }

    /**
     * Emits on long click events
     */
    @NonNull
    public static <T extends IItem> Observable<LongClickEventHookEvent<T>> longClickEventHooks(@NonNull FastAdapter<T> adapter,
                                                                                               @NonNull EventHookCallback callback) {
        return longClickEventHooks(adapter, callback, Functions.<LongClickEventHookEvent<T>>always(true));
    }

    /**
     * Emits on long click events
     */
    @NonNull
    public static <T extends IItem> Observable<LongClickEventHookEvent<T>> longClickEventHooks(@NonNull FastAdapter<T> adapter,
                                                                                               @NonNull EventHookCallback callback,
                                                                                               @NonNull Predicate<LongClickEventHookEvent<T>> predicate) {
        return LongClickEventHookObservable.create(adapter, callback, predicate);
    }

    /**
     * Emits on touches
     */
    @NonNull
    public static <T extends IItem> Flowable<TouchEventHookEvent<T>> touchEventHooks(@NonNull FastAdapter<T> adapter,
                                                                                     @NonNull EventHookCallback callback) {
        return touchEventHooks(adapter, callback, Functions.<TouchEventHookEvent<T>>always(true));
    }

    /**
     * Emits on touches
     */
    @NonNull
    public static <T extends IItem> Flowable<TouchEventHookEvent<T>> touchEventHooks(@NonNull FastAdapter<T> adapter,
                                                                                     @NonNull EventHookCallback callback,
                                                                                     @NonNull Predicate<TouchEventHookEvent<T>> predicate) {
        return touchEventHooks(adapter, callback, predicate, BackpressureStrategy.BUFFER);
    }

    /**
     * Emits on touches
     */
    @NonNull
    public static <T extends IItem> Flowable<TouchEventHookEvent<T>> touchEventHooks(@NonNull FastAdapter<T> adapter,
                                                                                     @NonNull EventHookCallback callback,
                                                                                     @NonNull Predicate<TouchEventHookEvent<T>> predicate,
                                                                                     @NonNull BackpressureStrategy backpressureStrategy) {
        return TouchEventHookFlowable.create(adapter, callback, predicate, backpressureStrategy);
    }
}
