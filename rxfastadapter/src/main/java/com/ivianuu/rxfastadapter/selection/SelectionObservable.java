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

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.ISelectionListener;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

/**
 * Selection observable
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class SelectionObservable<T extends IItem> implements ObservableOnSubscribe<SelectionEvent<T>> {

    private final FastAdapter<T> adapter;

    private SelectionObservable(FastAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @CheckResult @NonNull
    public static <T extends IItem>Observable<SelectionEvent<T>> create(@NonNull FastAdapter<T> adapter) {
        return Observable.create(new SelectionObservable<T>(adapter));
    }

    @Override
    public void subscribe(final ObservableEmitter<SelectionEvent<T>> e) throws Exception {
        adapter.withSelectionListener(new ISelectionListener<T>() {
            @Override
            public void onSelectionChanged(T item, boolean selected) {
                if (!e.isDisposed()) {
                    SelectionEvent<T> selectionEvent = new SelectionEvent<>(item, selected);
                    e.onNext(selectionEvent);
                }
            }
        });

        e.setDisposable(new Disposable() {
            private boolean disposed;
            @Override
            public void dispose() {
                if (!disposed) {
                    disposed = true;
                    adapter.withSelectionListener(null);
                }
            }

            @Override
            public boolean isDisposed() {
                return disposed;
            }
        });
    }
}
