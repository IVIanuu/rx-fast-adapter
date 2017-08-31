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
import android.support.annotation.RestrictTo;
import android.view.MotionEvent;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;

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
public class TouchFlowable<T extends IItem> implements FlowableOnSubscribe<TouchEvent<T>> {

    private final FastAdapter<T> adapter;
    private final Predicate<TouchEvent<T>> predicate;

    private TouchFlowable(FastAdapter<T> adapter,
                          Predicate<TouchEvent<T>> predicate) {
        this.adapter = adapter;
        this.predicate = predicate;
    }

    public static <T extends IItem> Flowable<TouchEvent<T>> create(@NonNull FastAdapter<T> adapter,
                                                            @NonNull Predicate<TouchEvent<T>> predicate,
                                                            @NonNull BackpressureStrategy backpressureStrategy) {
        return Flowable.create(new TouchFlowable<T>(adapter, predicate), backpressureStrategy);
    }

    @Override
    public void subscribe(final FlowableEmitter<TouchEvent<T>> e) throws Exception {
        adapter.withOnTouchListener(new FastAdapter.OnTouchListener<T>() {
            @Override
            public boolean onTouch(View v, MotionEvent event, IAdapter<T> adapter, T item, int position) {
                if (!e.isCancelled()) {
                    TouchEvent<T> touchEvent = new TouchEvent<>(v, event, adapter, item, position);
                    e.onNext(touchEvent);
                    try {
                        return predicate.test(touchEvent);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                return false;
            }
        });

        e.setDisposable(new Disposable() {
            private boolean disposed;
            @Override
            public void dispose() {
                if (!disposed) {
                    disposed = true;
                    adapter.withOnTouchListener(null);
                }
            }

            @Override
            public boolean isDisposed() {
                return disposed;
            }
        });
    }
}
