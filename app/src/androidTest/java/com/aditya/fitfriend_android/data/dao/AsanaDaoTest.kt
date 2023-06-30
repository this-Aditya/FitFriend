package com.aditya.fitfriend_android.data.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aditya.fitfriend_android.data.YogaDataBase
import com.aditya.fitfriend_android.data.mappers.AsanaCacheMapper
import com.aditya.fitfriend_android.getOrAwaitValue
import com.aditya.fitfriend_android.models.Asana
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AsanaDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: YogaDataBase
    private lateinit var dao: AsanaDao
    private lateinit var asana: Asana
    private lateinit var asanaCacheMapper: AsanaCacheMapper

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            YogaDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = database.asanaDao()
        asanaCacheMapper = AsanaCacheMapper()
        asana = Asana(
            1, "Svargasana",
            "Sarvangasana is a posture where the whole body is balanced on the shoulder and is therefore, also known as a Shoulder stand. \n" +
                    "Sarvangasana constitute 3 words “Sarva”, “anga” and “asana”. “Sarva” means “all”, “anga” means body part and “asana” means posture. As the name indicates, Sarvangasana is the posture involving the whole body. \n" +
                    "It is referred to as the Queen of asanas because it maintains the physical and mental health of an individual.",
            "Relaxation: Svaragasana promotes deep relaxation and helps to calm the mind and release stress. It allows the body to enter a state of complete rest, helping to reduce anxiety, fatigue, and tension.\n" +
                    "Stress Reduction: By consciously relaxing different parts of the body while in Svaragasana, you can release physical and mental tension. This practice can be particularly beneficial for individuals experiencing high levels of stress or dealing with stressful situations.\n" +
                    "Restores Energy: Svaragasana rejuvenates and re-energizes the body. By allowing the muscles to relax completely, the pose helps to restore energy levels, improve focus, and enhance mental clarity.\n" +
                    "Deepens Awareness: This asana promotes heightened awareness and a deeper connection between the body and mind. By practicing Svaragasana, you can become more mindful and develop a greater understanding of your own thoughts, sensations, and emotions.\n" +
                    "Improves Sleep: Regular practice of Svaragasana can help improve the quality of sleep. By entering a state of deep relaxation, the body and mind become more prepared for rest, leading to better sleep patterns and a sense of well-restedness.\n" +
                    "Lowers Blood   Pressure: Svaragasana has been found to have a positive impact on blood pressure. When practiced regularly, it can help reduce hypertension and promote a healthier cardiovascular system.\n" +
                    "Enhances Digestion: By inducing a state of deep relaxation, Svaragasana can stimulate the parasympathetic nervous system, which is responsible for rest and digestion. This can improve digestive functions, reduce bloating, and enhance overall gut health.\n" +
                    "Balances Hormones: This asana helps to regulate the endocrine system and balance hormone levels in the body. Regular practice can be particularly beneficial for individuals dealing with hormonal imbalances, such as those related to menopause or stress.\n" +
                    "Supports Healing and Recovery: Svaragasana allows the body to rest and heal, making it a beneficial pose for individuals recovering from illness, injury, or surgery. It promotes overall healing, both physically and mentally.\n" +
                    "Cultivates Inner Peace: Through the practice of Svaragasana, one can cultivate a sense of inner peace, tranquility, and serenity. It provides an opportunity to detach from the external world and find a deep sense of calm within oneself.",
            "Begin by lying flat on your back on a yoga mat or a comfortable surface. Keep your legs extended and your arms alongside your body, palms facing downward.\n" +
                    "Bend your knees and draw them toward your chest. Place your hands on your lower back for support, with your fingers pointing upward.\n" +
                    "Inhale deeply and press your palms against your lower back to lift your legs off the ground. Use your core strength to engage your abdominal muscles.\n" +
                    "Gradually extend your legs upward, keeping them straight and firm. Point your toes toward the ceiling. Maintain a steady and controlled movement throughout.\n" +
                    "Support your lower back with your hands and keep your elbows shoulder-width apart. This will help you maintain stability and balance in the pose.\n" +
                    "Lift your hips off the ground as you continue to lengthen your legs toward the sky. Your weight should be resting on your shoulders, upper arms, and back of your head. Avoid putting pressure on your neck.\n" +
                    "Keep your gaze fixed upward or toward your toes. Relax your facial muscles and maintain a steady breathing pattern.\n" +
                    "Hold the pose for a comfortable duration, starting with a few breaths and gradually increasing the time as you become more comfortable.\n" +
                    "To release the pose, slowly lower your legs back down to the ground, one at a time. Gently roll your spine down onto the mat, vertebra by vertebra, until your entire back is resting comfortably.",
            "If you are new to this pose or have any neck or shoulder issues, it is advisable to practice under the guidance of a qualified yoga instructor.\n" +
                    "Avoid this pose if you have any existing medical conditions or injuries that could be aggravated by inversions.\n" +
                    "Always listen to your body and do not push yourself beyond your limits. Modify or come out of the pose if you experience any pain or discomfort.",
            "https://yogavinirishikesh.com/wp-content/uploads/2016/07/shouder.jpg",
            "https://www.youtube.com/watch?v=kuFWLbCUiKI"
        )

    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun shouldAddAndDeleteAsanaToDB() = runTest {
        dao.addAsana(asanaCacheMapper.mapToEntity(asana))
        val allAsanas = dao.getAsanas().getOrAwaitValue()
        assertThat(allAsanas).contains(asanaCacheMapper.mapToEntity(asana))
        dao.deleteAsana(asanaCacheMapper.mapToEntity(asana))
        val updatedAsanas = dao.getAsanas().getOrAwaitValue()
        assertThat(updatedAsanas).doesNotContain(asanaCacheMapper.mapToEntity(asana))

    }
}