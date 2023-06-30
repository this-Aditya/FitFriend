package com.aditya.fitfriend_android.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.aditya.fitfriend_android.data.YogaDataBase
import com.aditya.fitfriend_android.data.entities.PranayamCacheEntity
import com.aditya.fitfriend_android.data.mappers.PranayamCacheMapper
import com.aditya.fitfriend_android.getOrAwaitValue
import com.aditya.fitfriend_android.models.Pranayam
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
class PranayamDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: YogaDataBase
    private lateinit var dao: PranayamDao
    private lateinit var pranayam: PranayamCacheEntity
    private lateinit var pranayamCacheMapper: PranayamCacheMapper

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            YogaDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = database.pranayamDao()
        pranayamCacheMapper = PranayamCacheMapper()
        val p = Pranayam(1, "Anulom Vilom", "Anulom Vilom is a specific type of pranayama, or controlled breathing, in yoga. It involves holding one nostril closed while inhaling, then holding the other nostril closed while exhaling. The process is then reversed and repeated.",
            "improved patience, focus, and control.\n" +
                    "relief from stress and anxiety.\n" +
                    "improvements to brain, respiratory, and cardiovascular health.\n" +
                    "a better overall sense of well-being.",
            "Choose a meditation sitting pose. Keep your spine and neck straight and close your eyes.\n" +
                    "Clear your mind of everything outside of this moment.\n" +
                    "Start with your outer wrists resting on your knees.\n" +
                    "Using your right hand, fold your middle and index fingers toward your palm.\n" +
                    "Place your thumb on your right nostril and your ring finger on your left nostril.\n" +
                    "Close your right nostril with your thumb and inhale through your left nostril, slowly and deeply, until your lungs are full. Focus on your breathing.\n" +
                    "Next, release your thumb and close your left nostril with your ring finger.\n" +
                    "Exhale slowly through the right nostril.\n" +
                    "Now practice it in reverse, this time inhaling through the right nostril and exhaling through the left.",
            "Initially, for at least 3-4 months, practice anulom-vilom without holding your breath.\n" +
                    "In the beginning, maintain the ratio of breathing, holding breath and exhaling as 1:2:2. ...\n" +
                    "Don't hold your breath forcefully.\n" +
                    "Sit still while practicing this asana.\n" +
                    "Increase the duration of the asana gradually.",
            "https://post.healthline.com/wp-content/uploads/2020/05/young-man-practicing-yoga-at-park-732x549-thumbnail-732x549.jpg",
            "https://www.youtube.com/watch?v=e_QzdnFrZ-M"
        )
        pranayam = pranayamCacheMapper.mapToEntity(p)
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun shouldAddAndDeletePranayamToDB() {
        runTest {
            dao.insertPranayam(pranayam)
            val pranayams: List<PranayamCacheEntity> = dao.getPranayams().getOrAwaitValue()
            assertThat(pranayams).contains(pranayam)
            dao.deletePranayam(pranayam)
            val updatedPranayam = dao.getPranayams().getOrAwaitValue()
            assertThat(updatedPranayam).doesNotContain(pranayams)
        }
    }
}
