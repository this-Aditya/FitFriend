/**
 * Copyright 2023 Aditya Mishra

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.aditya.fitfriend_android.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.aditya.fitfriend_android.models.Asana

private const val TAG = "AsanaDiffutil"

class AsanaDiffutil(val oldAsanas: List<Asana>,val newAsanas: List<Asana>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldAsanas.size

    override fun getNewListSize(): Int = newAsanas.size


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldAsanas[oldItemPosition].id == newAsanas[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldAsanas[oldItemPosition] == newAsanas[newItemPosition]
}
