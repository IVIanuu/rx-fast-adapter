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

package com.ivianuu.rxfastadapter.kotlin

import android.support.annotation.CheckResult
import com.ivianuu.rxfastadapter.Functions
import com.ivianuu.rxfastadapter.RxFastAdapter
import com.ivianuu.rxfastadapter.click.ClickEvent
import com.ivianuu.rxfastadapter.longclick.LongClickEvent
import com.ivianuu.rxfastadapter.longclick.LongClickObservable
import com.ivianuu.rxfastadapter.selection.SelectionEvent
import com.ivianuu.rxfastadapter.touch.TouchEvent
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import io.reactivex.Observable
import io.reactivex.functions.Predicate

/**
 * Emits on clicks
 */
@CheckResult
fun <T : IItem<*, *>?> FastAdapter<T>.clicks(): Observable<ClickEvent<T>> = RxFastAdapter.clicks(this)

/**
 * Emits on clicks
 */
@CheckResult
fun <T : IItem<*, *>?> FastAdapter<T>.clicks(predicate: Predicate<ClickEvent<T>>): Observable<ClickEvent<T>>
        = RxFastAdapter.clicks(this, predicate)

/**
 * Emits on clicks
 * This will use event hooks internally
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.clicks(vararg ids: Int): Observable<ClickEvent<T>>
        = RxFastAdapter.clicks(this, ids.asList())

/**
 * Emits on clicks
 * This will use event hooks internally
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.clicks(ids: List<Int>): Observable<ClickEvent<T>> = RxFastAdapter.clicks(this, ids)

/**
 * Emits on pre clicks
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.preClicks(): Observable<ClickEvent<T>> = RxFastAdapter.preClicks(this)

/**
 * Emits on pre clicks
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.preClicks(predicate: Predicate<ClickEvent<T>>): Observable<ClickEvent<T>>
        = RxFastAdapter.preClicks(this, predicate)

/**
 * Emits on long clicks
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.longClicks(): Observable<LongClickEvent<T>> = RxFastAdapter.longClicks(this)

/**
 * Emits on long clicks
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.longClicks(predicate: Predicate<LongClickEvent<T>>): Observable<LongClickEvent<T>>
        = RxFastAdapter.longClicks(this, predicate)

/**
 * Emits on long clicks
 * This will use event hooks internally
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.longClicks(vararg ids: Int): Observable<LongClickEvent<T>>
        = RxFastAdapter.longClicks(this, ids.asList())

/**
 * Emits on long clicks
 * This will use event hooks internally
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.longClicks(ids: List<Int>): Observable<LongClickEvent<T>>
        = RxFastAdapter.longClicks(this, ids)

/**
 * Emits on long clicks
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.longClicks(predicate: Predicate<LongClickEvent<T>>,
                                                vararg ids: Int): Observable<LongClickEvent<T>>
        = RxFastAdapter.longClicks(this, predicate, ids.asList())

/**
 * Emits on long clicks
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.longClicks(adapter: FastAdapter<T>,
                                 predicate: Predicate<LongClickEvent<T>>,
                                 ids: List<Int>): Observable<LongClickEvent<T>>
        = RxFastAdapter.longClicks(this, predicate, ids)

/**
 * Emits on pre long clicks
 */
@CheckResult
fun <T : IItem<*, *>> preLongClicks(adapter: FastAdapter<T>): Observable<LongClickEvent<T>> {
    return preLongClicks(adapter, Functions.always(true))
}

/**
 * Emits on pre long clicks
 */
@CheckResult
fun <T : IItem<*, *>> preLongClicks(adapter: FastAdapter<T>,
                                    predicate: Predicate<LongClickEvent<T>>
): Observable<LongClickEvent<T>> {
    return LongClickObservable.create(adapter, predicate, true)
}

/**
 * Emits on touches
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.touches(): Observable<TouchEvent<T>> = RxFastAdapter.touches(this)

/**
 * Emits on touches
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.touches(vararg ids: Int): Observable<TouchEvent<T>>
        = RxFastAdapter.touches(this, ids.asList())

/**
 * Emits on touches
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.touches(ids: List<Int>): Observable<TouchEvent<T>>
        = RxFastAdapter.touches(this, ids)

/**
 * Emits on touches
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.touches(predicate: Predicate<TouchEvent<T>>): Observable<TouchEvent<T>>
        = RxFastAdapter.touches(this, predicate)

/**
 * Emits on touches
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.touches(predicate: Predicate<TouchEvent<T>>, vararg ids: Int): Observable<TouchEvent<T>>
        = RxFastAdapter.touches(this, predicate, ids.asList())

/**
 * Emits on touches
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.touches(predicate: Predicate<TouchEvent<T>>, ids: List<Int>): Observable<TouchEvent<T>>
        = RxFastAdapter.touches(this, predicate, ids)

/**
 * Emits on selection changes
 */
@CheckResult
fun <T : IItem<*, *>> FastAdapter<T>.selections(): Observable<SelectionEvent<T>> =  RxFastAdapter.selections(this)