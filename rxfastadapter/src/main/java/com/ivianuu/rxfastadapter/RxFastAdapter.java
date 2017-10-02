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

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.ivianuu.rxfastadapter.click.ClickEvent;
import com.ivianuu.rxfastadapter.click.ClickEventHookObservable;
import com.ivianuu.rxfastadapter.click.ClickObservable;
import com.ivianuu.rxfastadapter.eventhookcallback.IdEventHookCallback;
import com.ivianuu.rxfastadapter.longclick.LongClickEvent;
import com.ivianuu.rxfastadapter.longclick.LongClickEventHookObservable;
import com.ivianuu.rxfastadapter.longclick.LongClickObservable;
import com.ivianuu.rxfastadapter.selection.SelectionEvent;
import com.ivianuu.rxfastadapter.selection.SelectionObservable;
import com.ivianuu.rxfastadapter.touch.TouchEvent;
import com.ivianuu.rxfastadapter.touch.TouchEventHookObservable;
import com.ivianuu.rxfastadapter.touch.TouchObservable;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

import static com.ivianuu.rxfastadapter.Preconditions.checkNotNull;

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
    @CheckResult @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> clicks(@NonNull FastAdapter<T> adapter) {
        return clicks(adapter, Functions.<ClickEvent<T>>always(true));
    }

    /**
     * Emits on clicks
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> clicks(@NonNull FastAdapter<T> adapter,
                                                                     @NonNull Predicate<ClickEvent<T>> predicate) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(predicate, "predicate == null");
        return ClickObservable.create(adapter, predicate, false);
    }

    /**
     * Emits on clicks
     * This will use event hooks internally
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> clicks(@NonNull FastAdapter<T> adapter,
                                                                     @NonNull Integer... ids) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(ids, "ids == null");
        return ClickEventHookObservable.create(adapter, IdEventHookCallback.with(ids));
    }

    /**
     * Emits on clicks
     * This will use event hooks internally
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> clicks(@NonNull FastAdapter<T> adapter,
                                                                     @NonNull List<Integer> ids) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(ids, "ids == null");
        return ClickEventHookObservable.create(adapter, IdEventHookCallback.with(ids));
    }

    /**
     * Emits on pre clicks
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> preClicks(@NonNull FastAdapter<T> adapter) {
        return preClicks(adapter, Functions.<ClickEvent<T>>always(true));
    }

    /**
     * Emits on pre clicks
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> preClicks(@NonNull FastAdapter<T> adapter,
                                                                        @NonNull Predicate<ClickEvent<T>> predicate) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(predicate, "predicate == null");
        return ClickObservable.create(adapter, predicate, true);
    }

    /**
     * Emits on long clicks
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> longClicks(@NonNull FastAdapter<T> adapter) {
        return longClicks(adapter, Functions.<LongClickEvent<T>>always(true));
    }

    /**
     * Emits on long clicks
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> longClicks(@NonNull FastAdapter<T> adapter,
                                                                             @NonNull Predicate<LongClickEvent<T>> predicate) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(predicate, "predicate == null");
        return LongClickObservable.create(adapter, predicate, false);
    }

    /**
     * Emits on long clicks
     * This will use event hooks internally
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> longClicks(@NonNull FastAdapter<T> adapter,
                                                                             @NonNull Integer... ids) {
        return longClicks(adapter, Functions.<LongClickEvent<T>>always(true), ids);
    }

    /**
     * Emits on long clicks
     * This will use event hooks internally
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> longClicks(@NonNull FastAdapter<T> adapter,
                                                                             @NonNull List<Integer> ids) {
        return longClicks(adapter, Functions.<LongClickEvent<T>>always(true), ids);
    }

    /**
     * Emits on long clicks
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> longClicks(@NonNull FastAdapter<T> adapter,
                                                                             @NonNull Predicate<LongClickEvent<T>> predicate,
                                                                             @NonNull Integer... ids) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(predicate, "predicate == null");
        checkNotNull(ids, "ids == null");
        return LongClickEventHookObservable.create(adapter, IdEventHookCallback.with(ids), predicate);
    }

    /**
     * Emits on long clicks
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> longClicks(@NonNull FastAdapter<T> adapter,
                                                                             @NonNull Predicate<LongClickEvent<T>> predicate,
                                                                             @NonNull List<Integer> ids) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(predicate, "predicate == null");
        checkNotNull(ids, "ids == null");
        return LongClickEventHookObservable.create(adapter, IdEventHookCallback.with(ids), predicate);
    }

    /**
     * Emits on pre long clicks
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> preLongClicks(@NonNull FastAdapter<T> adapter) {
        return preLongClicks(adapter, Functions.<LongClickEvent<T>>always(true));
    }

    /**
     * Emits on pre long clicks
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> preLongClicks(@NonNull FastAdapter<T> adapter,
                                                                                @NonNull Predicate<LongClickEvent<T>> predicate) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(predicate, "predicate == null");
        return LongClickObservable.create(adapter, predicate, true);
    }

    /**
     * Emits on touches
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<TouchEvent<T>> touches(@NonNull FastAdapter<T> adapter,
                                                                      @NonNull Integer... ids) {
        return touches(adapter, Functions.<TouchEvent<T>>always(true), ids);
    }

    /**
     * Emits on touches
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<TouchEvent<T>> touches(@NonNull FastAdapter<T> adapter,
                                                                      @NonNull List<Integer> ids) {
        return touches(adapter, Functions.<TouchEvent<T>>always(true), ids);
    }

    /**
     * Emits on touches
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<TouchEvent<T>> touches(@NonNull FastAdapter<T> adapter) {
        checkNotNull(adapter, "adapter == null");
        return touches(adapter, Functions.<TouchEvent<T>>always(true));
    }

    /**
     * Emits on touches
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<TouchEvent<T>> touches(@NonNull FastAdapter<T> adapter,
                                                                      @NonNull Predicate<TouchEvent<T>> predicate) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(predicate, "predicate == null");
        return TouchObservable.create(adapter, predicate);
    }

    /**
     * Emits on touches
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<TouchEvent<T>> touches(@NonNull FastAdapter<T> adapter,
                                                                      @NonNull Predicate<TouchEvent<T>> predicate,
                                                                      @NonNull Integer... ids) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(predicate, "predicate == null");
        checkNotNull(ids, "ids == null");
        return TouchEventHookObservable.create(adapter, IdEventHookCallback.with(ids), predicate);
    }

    /**
     * Emits on touches
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<TouchEvent<T>> touches(@NonNull FastAdapter<T> adapter,
                                                                      @NonNull Predicate<TouchEvent<T>> predicate,
                                                                      @NonNull List<Integer> ids) {
        checkNotNull(adapter, "adapter == null");
        checkNotNull(predicate, "predicate == null");
        checkNotNull(ids, "ids == null");
        return TouchEventHookObservable.create(adapter, IdEventHookCallback.with(ids), predicate);
    }

    /**
     * Emits on selection changes
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<SelectionEvent<T>> selections(@NonNull FastAdapter<T> adapter) {
        checkNotNull(adapter, "adapter == null");
        return SelectionObservable.create(adapter);
    }

}
