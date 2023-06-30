package com.aditya.fitfriend_android.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.aditya.fitfriend_android.data.YogaDataBase
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity
import com.aditya.fitfriend_android.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SleepSegmentDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: YogaDataBase
    private lateinit var dao: SleepSegmentDao
    private lateinit var sleepSegments: List<SleepSegmentEntity>

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            YogaDataBase::class.java
            ).allowMainThreadQueries().build()
        dao = database.sleepSegmentDao()
        sleepSegments = listOf(
            SleepSegmentEntity(42316895415,4213865432,53821654),
            SleepSegmentEntity(32879419256,213869543,2689211254)
        )
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun AddTheListOfSleepSegmentEvent() {
        runTest {
            dao.insertSleepSegments(sleepSegments)
            val allSegments: List<SleepSegmentEntity> = dao.getSleepSegments().getOrAwaitValue()
            assertThat(allSegments).containsExactlyElementsIn(sleepSegments)
        }
    }

}