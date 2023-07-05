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

package com.aditya.fitfriend_android.abstracts

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.fitfriend_android.databinding.ItemRowLayoutBinding
import com.aditya.fitfriend_android.diffutils.AsanaDiffutil

abstract class AbstractyogasAdapter<R>(protected val context: Context, private val itemClicked: (R) -> Unit) :
    RecyclerView.Adapter<AbstractyogasAdapter.AbstractViewHolder<R>>() {

    lateinit var binding: ItemRowLayoutBinding
    abstract var yogas: List<R>

    open class AbstractViewHolder<R>(val binding: ItemRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
      open fun bindView(yoga: R) {}
    }

    override fun getItemCount(): Int = yogas.size

    override fun onBindViewHolder(holder: AbstractViewHolder<R>, position: Int) {
        val yoga: R = yogas[position]
        holder.bindView(yoga)
        holder.binding.cvExpRow.setOnClickListener {
            itemClicked(yoga)
        }
    }
}