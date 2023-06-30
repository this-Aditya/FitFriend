package com.aditya.fitfriend_android.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.aditya.fitfriend_android.data.YogaDataBase
import com.aditya.fitfriend_android.data.entities.MeditationCacheEntity
import com.aditya.fitfriend_android.data.mappers.MeditationCacheMapper
import com.aditya.fitfriend_android.getOrAwaitValue
import com.aditya.fitfriend_android.models.Meditation
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
class MeditationDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: YogaDataBase
    private lateinit var dao: MeditationDao
    private lateinit var meditation: Meditation
    private lateinit var meditationCacheMapper: MeditationCacheMapper

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            YogaDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = database.meditationDao()
        meditationCacheMapper = MeditationCacheMapper()
        meditation = Meditation(
            2,
            "Shambhavi Mudra",
            "Shambhavi Mahamudra is an integrative system of several breathing techniques that incorporate various limbs of traditional Raja Yoga or yoga described in sutras by Patanjali. It is a highly regarded gesture practiced in yoga and meditation. In English this gesture is called eyebrow center gazing. The name comes from Shambhavi, the name of Parvati who is the consort of Shiva, the Hindu lord. Parvati is a symbol of divine energy. The existence of human beings is supposed to be propelled from the same energy. Connecting these dots, you will realize that shambhavi mudra is way to way to give direction to the energy that properly your life. Shambhavi mudra can bring your mind into a balanced condition and a state of a high level of consciousness. There are a number of shambhavi mudra benefits that make it one of the most revered forms of yoga and meditation. If you do all the shambhavi mudra steps properly, it will help you in gazing inside of your conscious and that will eventually remove all kinds of mental distracts. Shambhavi yoga mudra is a form of shambhavi mudra developed by Sadhguru of Isha Foundation. It is actually a course that teaches you the mudra step by step. It should be noted here that Mahamudra steps are somewhat similar to shambhavi kriya steps. So, you can learn either to get the benefits of shambhavi mudra.",
            "It is the huge list of benefits that has ensured that this ancient form of yoga and meditation survived thousands of years. Although almost every form of yoga and meditation has general benefits like the betterment of physical and mental, there are certain benefits that come with shambhavi mudra that are exclusive to it. Here is a list of some of the most important shambhavi mudra benefits:\n" +
                    "It can help you transcend your mind in the shortest possible time. Although there are many other types of meditation that can help you do that, shambhavi mudra can help you do it in a short time.\n" +
                    "Sages and yoga gurus used to go in a state of samadhi to reach a high state of consciousness. By doing shambhavi mahamudra kriya steps, you also can achieve that state without actually going into samadhi.\n" +
                    "Our eyes are one of the most active sensory organs of our body. They give you inputs from the external world all the time when you are awake. However, when you do shambhavi mahamudra steps, you fix your eyes at one point and that helps you in focusing your thoughts on one point.\n" +
                    "Not just your conscience, this kriya can help you in the betterment of your physical health. Your eye muscles get strengthened when you practice the mudra.\n" +
                    "Your Ajna chakra gets activated when you do shambhavi mudra.\n",
            "Start by sitting in a meditative pose. You can sit in Padmasana, Siddhasana/Sukhasana, or any meditative pose that you are comfortable with. The place where you practice should be very quiet.\n" +
                    "Straighten your head and spine, and pose in Gyan mudra. Your hands should be placed firmly on your kneecaps.\n" +
                    "Close your eyes and relax your body including eyes, face muscles, forehead, and even behind the eyes.\n" +
                    "Open your eyes gradually and try to fix them at a point. Meanwhile, your body and head relaxed.\n" +
                    "Without moving your head, try to look inward and upward. The point that your both eyeballs should be focused on should be the eyebrow center.\n" +
                    "If you are performing this step correctly, your eyebrows curve will form a V shape with an apex at your eyebrow center. If a V-shape curve is not formed, your gaze is not pointed inwards and upward correctly.\n" +
                    "Do not blink while gazing. Once your eyes get tired or water starts to come out of them, discontinue.\n" +
                    "Do it again after a short rest.\n" +
                    "When your eyes are closed, do not think about anything. Focus on the darkness that is inside your eyes and mind. If you are unable to do it, you can gradually chant OM to shift your focus on the chanting sound.\n" +
                    "Second Stage\n" +
                    "Once you complete master these eye movements, you can then use your breath to coordinate shambhavi mudra.\n" +
                    "When you gaze at your eyebrows center, slowly inhale. Try to direct your awareness towards the Ajna chakra.\n" +
                    "When you gazing upward, hold your breath.\n" +
                    "Exhale when you lower down your gaze.\n",
            "Theos Bernard goes on to warn that this practice is to be learned from an experienced teacher or guru. He also says that this practice requires sincerity and perseverance before higher experiences can be awakened.\n" +
                    "Chapter 4, Verse 35 of Hatha Yoga Pradipika – in considering one of its interpretations – also hints that only the initiated were taught this practice.\n" +
                    "Don’t focus too hard or strain yourself while concentrating. Always remain calm and relaxed and listen to your body. If you are facing headaches or body pains after the practice that is severe, stop the practice immediately and seek an expert’s guidance.\n" +
                    "Always start any yoga practice for the least interval of time and gradually increase.\n" +
                    "Posture should be such that the back/spine is always straight. No compromise can be made with this basic rule.",
            "https://www.fitsri.com/wp-content/uploads/2022/05/shambhavi-mudra-practice-1024x683.jpg",
            "https://www.youtube.com/watch?v=C_xsXnRd_uc"
        )
    }

        @After
        fun teardown() {
            database.close()
        }

        @Test
        fun shouldAddAndDeletePranayamToDB() {
            runTest {
                dao.addMeditation(meditationCacheMapper.mapToEntity(meditation))
                val allMeditations: List<MeditationCacheEntity> = dao.getMeditations().getOrAwaitValue()
                assertThat(allMeditations).contains(meditationCacheMapper.mapToEntity(meditation))
                dao.deleteMeditation(meditationCacheMapper.mapToEntity(meditation))
                val updatedMeditations: List<MeditationCacheEntity> = dao.getMeditations().getOrAwaitValue()
                assertThat(updatedMeditations).doesNotContain(meditationCacheMapper.mapToEntity(meditation))
            }
        }

}