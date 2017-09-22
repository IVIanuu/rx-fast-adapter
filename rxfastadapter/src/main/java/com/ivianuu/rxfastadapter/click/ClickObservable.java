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

package com.ivianuu.rxfastadapter.click;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Predicate;

/**
 * Fast adapter click observable
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public final class ClickObservable<T extends IItem> implements ObservableOnSubscribe<ClickEvent<T>> {

    private final FastAdapter<T> adapter;
    private final Predicate<ClickEvent<T>> predicate;
    private final boolean preClick;

    private ClickObservable(FastAdapter<T> adapter,
                            Predicate<ClickEvent<T>> predicate,
                            boolean preClick) {
        this.adapter = adapter;
        this.predicate = predicate;
        this.preClick = preClick;
    }

    /**
     * Emits on clicks
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> create(@NonNull FastAdapter<T> adapter,
                                                                     @NonNull Predicate<ClickEvent<T>> predicate,
                                                                     boolean preClick) {
        return Observable.create(new ClickObservable<>(adapter, predicate, preClick));
    }

    @Override
    public void subscribe(final ObservableEmitter<ClickEvent<T>> e) throws Exception {
        if (preClick) {
            adapter.withOnPreClickListener((v, adapter, item, position) -> {
                if (!e.isDisposed()) {
                    ClickEvent<T> clickEvent = new ClickEvent<>(v, adapter, item, position);
                    e.onNext(clickEvent);
                    try {
                        return predicate.test(clickEvent);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                return false;
            });
        } else {
            adapter.withOnClickListener((v, adapter, item, position) -> {
                if (!e.isDisposed()) {
                    ClickEvent<T> clickEvent = new ClickEvent<>(v, adapter, item, position);
                    e.onNext(clickEvent);
                    try {
                        return predicate.test(clickEvent);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                return false;
            });

        }

        e.setCancellable(() -> {
            if (preClick) {
                adapter.withOnPreClickListener(null);
            } else {
                adapter.withOnClickListener(null);
            }
        });
    }
}
