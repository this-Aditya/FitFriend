package com.aditya.fitfriend_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aditya.fitfriend_android.data.entities.ActivityEntity
import com.aditya.fitfriend_android.databinding.ItemRowActivityBinding
import com.aditya.fitfriend_android.utils.TimeTranformer

class ActivityAdapter(private val context: Context) :
    RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {
    private lateinit var binding: ItemRowActivityBinding
    private val activities = mutableListOf<ActivityEntity>()

    class ActivityViewHolder(private val binding: ItemRowActivityBinding) :
        ViewHolder(binding.root) {
        fun binddata(activity: ActivityEntity) {
            this.binding.tvRow.text = buildString {
                append(activity.transition)
                append(" ")
                append(activity.activityName)
            }
            binding.tvRow2.text = buildString {
                append("AT ")
                append(TimeTranformer.convertMsToHMS(activity.time))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        binding = ItemRowActivityBinding.inflate(LayoutInflater.from(context), parent, false)
        return ActivityViewHolder(binding)
    }

    override fun getItemCount(): Int = activities.size

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.binddata(activity)
    }
}