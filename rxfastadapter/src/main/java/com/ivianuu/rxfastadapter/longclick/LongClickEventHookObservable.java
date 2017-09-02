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

package com.ivianuu.rxfastadapter.longclick;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ivianuu.rxfastadapter.eventhookcallback.EventHookCallback;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.listeners.LongClickEventHook;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

/**
 * Fast adpater click event hook observable
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class LongClickEventHookObservable<T extends IItem>
        implements ObservableOnSubscribe<LongClickEvent<T>> {

    private final FastAdapter<T> adapter;
    private final Predicate<LongClickEvent<T>> predicate;
    private final EventHookCallback callback;

    private LongClickEventHookObservable(FastAdapter<T> adapter,
                                         EventHookCallback callback,
                                         Predicate<LongClickEvent<T>> predicate) {
        this.adapter = adapter;
        this.callback = callback;
        this.predicate = predicate;
    }

    /**
     * Emits on click events
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<LongClickEvent<T>> create(@NonNull FastAdapter<T> adapter,
                                                                         @NonNull EventHookCallback callback,
                                                                         @NonNull Predicate<LongClickEvent<T>> predicate) {
        return Observable.create(new LongClickEventHookObservable<T>(adapter, callback, predicate));
    }

    @Override
    public void subscribe(final ObservableEmitter<LongClickEvent<T>> e) throws Exception {
        adapter.withEventHook(new LongClickEventHook<T>() {
            @Override
            public boolean onLongClick(View v, int position, FastAdapter<T> fastAdapter, T item) {
                if (!e.isDisposed()) {
                    LongClickEvent<T> event = new LongClickEvent<>(v, adapter.getAdapter(position), item, position);
                    e.onNext(event);
                    try {
                        return predicate.test(event);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                return false;
            }

            @Nullable
            @Override
            public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
                return callback.onBind(viewHolder);
            }

            @Nullable
            @Override
            public List<View> onBindMany(@NonNull RecyclerView.ViewHolder viewHolder) {
                return callback.onBindMany(viewHolder);
            }
        });

        e.setDisposable(new Disposable() {
            private boolean disposed;
            @Override
            public void dispose() {
                if (!disposed) {
                    disposed = true;
                }
            }

            @Override
            public boolean isDisposed() {
                return true;
            }
        });
    }
}
