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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aditya.fitfriend_android.data.entities.ActivityEntity
import com.aditya.fitfriend_android.databinding.ItemRowActivityBinding
import com.aditya.fitfriend_android.diffutils.ActivityDiffutil
import com.aditya.fitfriend_android.utils.TimeTranformer

class ActivityAdapter(private val context: Context) :
    RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {
    private lateinit var binding: ItemRowActivityBinding
    private var activities = emptyList<ActivityEntity>()

    class ActivityViewHolder(private val binding: ItemRowActivityBinding) :
        ViewHolder(binding.root) {
        fun binddata(activity: ActivityEntity) {
            this.binding.tvRow.text = buildString {
                append(ActivityEntity.toTransitionType(activity.transition))
                append(" ")
                append(ActivityEntity.toActivityType(activity.activityType))
            }
            binding.tvRow2.text = buildString {
                append("AT ")
                append(TimeTranformer.epochToDateTime(activity.time))
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

    fun setdata(updatedActivities: List<ActivityEntity>) {
        val diffUtil = ActivityDiffutil(activities, updatedActivities)
        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
        activities = updatedActivities
        diffUtilResults.dispatchUpdatesTo(this)
    }
}