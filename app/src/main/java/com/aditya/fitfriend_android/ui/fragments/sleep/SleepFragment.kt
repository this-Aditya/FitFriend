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

package com.aditya.fitfriend_android.ui.fragments.sleep

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aditya.fitfriend_android.R
import com.aditya.fitfriend_android.adapters.SleepSparkAdapter
import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity
import com.aditya.fitfriend_android.databinding.FragmentSleepBinding
import com.aditya.fitfriend_android.models.DataLog
import com.aditya.fitfriend_android.models.DataLog.CENT
import com.aditya.fitfriend_android.models.DataLog.MAX
import com.aditya.fitfriend_android.models.DataLog.TEN
import com.aditya.fitfriend_android.models.Sleep
import com.aditya.fitfriend_android.models.SleepMetric.SLEEP_CLASSIFY
import com.aditya.fitfriend_android.models.SleepMetric.SLEEP_SEGMENTS
import com.aditya.fitfriend_android.models.SleepMetric
import com.aditya.fitfriend_android.utils.TimeTranformer
import com.aditya.fitfriend_android.viewmodels.SleepStateEvent.*
import com.aditya.fitfriend_android.viewmodels.SleepViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "SleepFragment"

@AndroidEntryPoint
class SleepFragment : Fragment() {

    private val viewmodel: SleepViewModel by viewModels()
    private lateinit var binding: FragmentSleepBinding
    private lateinit var adapter: SleepSparkAdapter
    private val sleep = Sleep(mutableListOf(), mutableListOf())
    private var num = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSleepBinding.inflate(inflater, container, false)
        viewmodel.setStateEvent(SegmentEvent)
        viewmodel.setStateEvent(ClassifyEvent)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateInitialDisplay()
        subscribeToLocalDataObservers()
        adapter = SleepSparkAdapter(sleep)
        binding.sparkView.adapter = adapter
        setUpEventListeners()
    }

    private fun subscribeToLocalDataObservers() {
        viewmodel.sleepSegmentEvents.observe(viewLifecycleOwner) { segments ->
            Log.i(TAG, "Observed data from SleepSegmentData ")
            try {
                sleep.segment.clear()
                sleep.segment.addAll(segments)
                adapter.sleep.segment.clear()
                adapter.sleep.segment.addAll(segments)
                adapter.notifyDataSetChanged()
            } catch (ex: Exception) {
                Log.w(TAG, "Exception while subscribing to sleep segments -> $ex", )
            }

        }
        viewmodel.sleepClassifyEvents.observe(viewLifecycleOwner) { classifydata ->
            Log.i(TAG, "Observed data from SleepClassifyData")
            try {
                sleep.classify.clear()
                sleep.classify.addAll(classifydata)
                updateInfoForSleep(sleep)
                adapter.sleep.classify.clear()
                adapter.sleep.classify.addAll(classifydata)
                adapter.notifyDataSetChanged()
            } catch (ex: Exception) {
                Log.w(TAG, "Exception while subscribing to sleep classify data -> $ex", )
            }

        }
    }

    private fun setUpEventListeners() {
        binding.sparkView.isScrubEnabled = true
        binding.sparkView.setScrubListener { value ->
            try {
                when (adapter.metric) {
                    SLEEP_SEGMENTS -> {
                        val segment = value as SleepSegmentEntity
                        updateInfoForSleep(Sleep(mutableListOf(segment), mutableListOf()))
                    }

                    SLEEP_CLASSIFY -> {
                        val classify = value as SleepClassifyEntity
                        updateInfoForSleep(Sleep(mutableListOf(), mutableListOf(classify)))
                    }
                }
            } catch (ex: NullPointerException) {
                Log.w(TAG, "Null Pointer Exception: ${ex.message}")
            } catch (ex: Exception) {
                Log.w(TAG, "Exception: $ex")
            }
        }

        binding.rgMetric.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbClassify -> updateDisplayMetric(SLEEP_CLASSIFY)
                R.id.rbSegment -> updateDisplayMetric(SLEEP_SEGMENTS)
            }
        }
        binding.rgDuration.setOnCheckedChangeListener {_, checkedId ->
            when (checkedId) {
                R.id.rbTen -> updateDurationData(TEN)
                R.id.rbCent -> updateDurationData(CENT)
                R.id.rbMax -> updateDurationData(MAX)
            }
        }
    }

    private fun updateDurationData(log: DataLog) {
        adapter.log = log
        adapter.notifyDataSetChanged()
    }

    private fun updateDisplayMetric(metric: SleepMetric) {
        adapter.metric = metric
        adapter.notifyDataSetChanged()
        updateInfoForSleep(sleep)
    }

    private fun updateInitialDisplay() {
        binding.tvSleep.text = "Sleep Confidence:"
        binding.rbClassify.isChecked = true
        binding.rbMax.isChecked = true
    }

    @SuppressLint("SetTextI18n")
    private fun updateInfoForSleep(sleep: Sleep) {
        when (adapter.metric) {
            SLEEP_CLASSIFY -> {
                try {
                    binding.tvSleep.text = "Sleep Confidence:"
                    binding.tvDate.text =
                        TimeTranformer.epochToDateTime(sleep.classify.last().timeStamp)
                    binding.tvConfidence.text = "Confidence: ${sleep.classify.last().confidence}"

                } catch (ex: Exception) {
                    Log.d(TAG, "Exception: $ex")
                }
            }

            SLEEP_SEGMENTS -> {
                try {
                    binding.tvSleep.text = "Sleep Start Time and Segment Duration:"
                    binding.tvDate.text =
                        TimeTranformer.epochToDateTime(sleep.segment.last().startTime)
                    binding.tvConfidence.text = "Sleep Duration: ${TimeTranformer.convertMsToHMS(sleep.segment.last().duration)}"
                } catch (ex: Exception) {
                    Log.d(TAG, "Exception: $ex")
                }
            }
        }

    }
}







