package com.aditya.fitfriend_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.aditya.fitfriend_android.abstracts.AbstractyogasAdapter
import com.aditya.fitfriend_android.databinding.ItemRowLayoutBinding
import com.aditya.fitfriend_android.diffutils.MeditationDiffutil
import com.aditya.fitfriend_android.models.Meditation
import com.squareup.picasso.Picasso

class MeditationAdapter(context: Context, itemClicked: (Meditation) -> Unit) : AbstractyogasAdapter<Meditation>(
    context, itemClicked) {

    override var yogas: List<Meditation> = emptyList()

    class MeditationViewholder(binding: ItemRowLayoutBinding) : AbstractViewHolder<Meditation>(binding) {
        override fun bindView(yoga: Meditation) {
            super.binding.tvRow.text = yoga.name
            Picasso.get().load(yoga.imageUrl).into(binding.ivRowImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<Meditation> {
        binding = ItemRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MeditationViewholder(binding)
    }

    fun setData(updatedMeditations: List<Meditation>) {
        val diffUtil = MeditationDiffutil(yogas, updatedMeditations)
        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
        yogas = updatedMeditations
        diffUtilResults.dispatchUpdatesTo(this)
    }

}