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
import android.support.annotation.RestrictTo;

import io.reactivex.functions.Predicate;

/**
 * Functions
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public final class Functions {

    private Functions() {
        // no instances
    }

    /**
     * Returns always the same value
     */
    @NonNull
    public static <T> Predicate<T> always(final boolean always) {
        return t -> always;
    }

}
