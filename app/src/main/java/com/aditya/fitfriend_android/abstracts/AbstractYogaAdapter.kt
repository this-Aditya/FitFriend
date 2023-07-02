package com.aditya.fitfriend_android.abstracts

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.fitfriend_android.databinding.ItemRowLayoutBinding

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
        binding.cvExpRow.setOnClickListener {
            itemClicked(yoga)
        }
    }
    fun setData(updatedYogas: List<R>) {
        val diffUtil = AbstractYogaDiffutil<R>(yogas, updatedYogas)
        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
        yogas = updatedYogas
        diffUtilResults.dispatchUpdatesTo(this)
    }
}