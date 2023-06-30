package com.aditya.fitfriend_android.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.aditya.fitfriend_android.data.YogaDataBase
import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import com.aditya.fitfriend_android.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class SleepClassifyDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: YogaDataBase
    private lateinit var dao: SleepClassifyDao
    private lateinit var classifyEvents: List<SleepClassifyEntity>

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            YogaDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = database.sleepClassifyDao()
        classifyEvents = listOf(SleepClassifyEntity(113654,23),
        SleepClassifyEntity(2378156854,87)
        )
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun shouldInsertClassfyEventsInDB() {
        runTest {
            dao.insertClassifyEvents(classifyEvents)
            val allEvents = dao.getClassifyEvents().getOrAwaitValue()
            assertThat(allEvents).containsExactlyElementsIn(classifyEvents)
        }
    }
}