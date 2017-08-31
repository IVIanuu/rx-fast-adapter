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

package com.ivianuu.rxfastadapter.toucheventhook;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.ivianuu.rxfastadapter.eventhookcallback.EventHookCallback;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.listeners.TouchEventHook;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

/**
 * Touch flowable
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class TouchEventHookFlowable<T extends IItem> implements FlowableOnSubscribe<TouchEventHookEvent<T>> {

    private final FastAdapter<T> adapter;
    private final EventHookCallback callback;
    private final Predicate<TouchEventHookEvent<T>> predicate;

    private TouchEventHookFlowable(FastAdapter<T> adapter,
                                     EventHookCallback callback,
                                     Predicate<TouchEventHookEvent<T>> predicate) {
        this.adapter = adapter;
        this.callback = callback;
        this.predicate = predicate;
    }

    /**
     * Emits on touches
     */
    @NonNull
    public static <T extends IItem> Flowable<TouchEventHookEvent<T>> create(@NonNull FastAdapter<T> adapter,
                                                                   @NonNull EventHookCallback callback,
                                                                   @NonNull Predicate<TouchEventHookEvent<T>> predicate,
                                                                   @NonNull BackpressureStrategy backpressureStrategy) {
        return Flowable.create(new TouchEventHookFlowable<T>(adapter, callback, predicate), backpressureStrategy);
    }

    @Override
    public void subscribe(final FlowableEmitter<TouchEventHookEvent<T>> e) throws Exception {
        adapter.withEventHook(new TouchEventHook<T>() {
            @Override
            public boolean onTouch(View v, MotionEvent event, int position, FastAdapter<T> fastAdapter, T item) {
                if (!e.isCancelled()) {
                    TouchEventHookEvent<T> touchEvent = new TouchEventHookEvent<>(v, event, adapter, item, position);
                    e.onNext(touchEvent);
                    try {
                        return predicate.test(touchEvent);
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
                return disposed;
            }
        });
    }
}
