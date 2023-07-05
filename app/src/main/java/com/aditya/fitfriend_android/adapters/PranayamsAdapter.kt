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

package com.aditya.fitfriend_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.aditya.fitfriend_android.abstracts.AbstractyogasAdapter
import com.aditya.fitfriend_android.databinding.ItemRowLayoutBinding
import com.aditya.fitfriend_android.diffutils.PranayamDiffutil
import com.aditya.fitfriend_android.models.Pranayam
import com.squareup.picasso.Picasso

class PranayamsAdapter(context: Context, itemClicked: (Pranayam) -> Unit) : AbstractyogasAdapter<Pranayam>(
    context, itemClicked) {

    override var yogas: List<Pranayam> = emptyList()

    class PranayamViewholder(binding: ItemRowLayoutBinding) : AbstractViewHolder<Pranayam>(binding) {
        override fun bindView(yoga: Pranayam) {
            super.binding.tvRow.text = yoga.name
            Picasso.get().load(yoga.imageUrl).into(binding.ivRowImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<Pranayam> {
        binding = ItemRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return PranayamViewholder(binding)
    }

    fun setData(updatedPranayams: List<Pranayam>) {
        val diffUtil = PranayamDiffutil(yogas, updatedPranayams)
        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
        yogas = updatedPranayams
        diffUtilResults.dispatchUpdatesTo(this)
    }

}