package com.aditya.fitfriend_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.aditya.fitfriend_android.abstracts.AbstractyogasAdapter
import com.aditya.fitfriend_android.databinding.ItemRowLayoutBinding
import com.aditya.fitfriend_android.diffutils.AsanaDiffutil
import com.aditya.fitfriend_android.models.Asana
import com.squareup.picasso.Picasso


class AsanasAdapter(context: Context, itemClicked: (Asana) -> Unit) : AbstractyogasAdapter<Asana>(
    context, itemClicked) {

    override var yogas: List<Asana> = emptyList()

    class AsanaViewholder(binding: ItemRowLayoutBinding) : AbstractViewHolder<Asana>(binding) {
        override fun bindView(yoga: Asana) {
            super.binding.tvRow.text = yoga.name
            Picasso.get().load(yoga.imageUrl).into(binding.ivRowImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<Asana> {
        binding = ItemRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return AsanaViewholder(binding)
    }

    fun setData(updatedAsanas: List<Asana>) {
        val diffUtil = AsanaDiffutil(yogas, updatedAsanas)
        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
        yogas = updatedAsanas
        diffUtilResults.dispatchUpdatesTo(this)
    }

}