package com.health.fitfriend.datasource.impl

import com.health.fitfriend.datasource.PranayamDataSource
import com.health.fitfriend.model.Pranayam
import org.springframework.stereotype.Repository

@Repository
class PranayamDataSourceImpl : PranayamDataSource {

    override fun retrivePranayams(): Collection<Pranayam> = pranayams

    override fun retrivePranayamById(id: Int): Pranayam = pranayams[id-1]

    override fun retrivePranayamByName(name: String): Pranayam =
        pranayams.firstOrNull { name.equals(it.name, ignoreCase = true) } ?: throw NoSuchElementException("Couldn't find pranayam named $name")

    val pranayam1 = Pranayam(1, "Anulom Vilom", "Anulom Vilom is a specific type of pranayama, or controlled breathing, in yoga. It involves holding one nostril closed while inhaling, then holding the other nostril closed while exhaling. The process is then reversed and repeated.",
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
    val pranayam2 = Pranayam(2,"Bhramari","Bhramari Pranayama, also known as Humming Bee Breath, is a calming breathing practice that soothes the nervous system and helps to connect us with our truest inner nature.\n"
                 + "Bhramari is the Sanskrit word for “bee,” and this pranayama is so named because of the humming sound produced at the back of the throat during the practice—like the gentle humming of a bee.",
"Calms and quiets the mind\n" +
        "Releases cerebral tension\n" +
        "Stimulates the pineal and pituitary glands, supporting their proper functioning\n" +
        "Soothes the nerves\n" +
        "Relieves stress and anxiety\n" +
        "Dissipates anger\n" +
        "Lowers blood pressure\n" +
        "Bolsters the health of the throat\n" +
        "Strengthens and improves the voice\n" +
        "Supports the healing of bodily tissues\n" +
        "Induces sound sleep",
        "Choose a comfortable sitting position. If you are able, it is best to sit cross-legged on the floor with a cushion or blanket to comfortably elevate the hips. Alternatively, you may choose to sit toward the front of a chair, with your feet flat on the floor. Allow the spine to lengthen so that the back, neck, and head are erect.\n" +
                "Gently close the lips, keeping the teeth slightly apart, and bring the tip of your tongue to the space behind the upper front teeth. Maintain this position of the mouth throughout the practice, frequently checking to ensure that the jaw remains relaxed.\n" +
                "Then, close each ear with the thumbs, place the index fingers at the midpoint of the forehead—just above the eyebrows—and reach the middle, ring, and pinky fingers across the eyes so that the tips of these fingers press very gently against the bridge of the nose.  \n" +
                "To begin, take a long, deep breath in through the nostrils, bringing the breath all the way into the belly. Drop the chin to the chest and begin to exhale slowly, making a steady, low-pitched ‘hmmm’ sound at the back of the throat—like the humming of a bee.\n" +
                "Focus on making the sound soft, smooth, and steady. The positioning of the tongue allows the vibration to better resonate throughout the head, affecting the tissues of the brain. Keep the body completely still and bring your awareness to the center of the head—to ajna chakra—letting the sound fill the head and spread to the body. Merge with the sound and allow the vibration to permeate your entire being.\n" +
                "At the end of the exhalation, slowly straighten your neck as you inhale again through the nostrils to repeat the process. Begin with seven repetitions. You may either continue with seven repetitions, or you may add one repetition per week, slowly building up to a total of seventeen repetitions.\n" +
                "After the final exhalation, allow your breath to return to normal and observe any changes that have occurred. How do you feel physically, mentally, emotionally, and spiritually? What energetic shifts do you notice as a result of this practice? Where do you notice sensation in your body and how is it different from when you started?\n" +
                "When you are ready, gently open your eyes, continuing to direct some of your awareness within. If it is morning, slowly stand and offer your full attention to the rest of your day; if it is evening, notice the vibrational calm that this practice has initiated in your body and try to maintain it as you prepare to retire for the night.\n" +
                "The above instructions are meant to provide a safe and general introduction to the practice of bhramari. Advanced practitioners sometimes add variations such as breath retention (khumbaka), muscular locks (bandhas), and may also hum on the inhalation—as in ujjayi pranayama. These additional techniques are best learned in person from a qualified teacher.",
        "Place your thumb on the tragus cartilage. ...\n" +
                "Do not press on the tragus cartilage too hard, but just place your thumb gently on it.\n" +
                "Bhramari Pranayama should be practiced with an empty stomach or at least four to five hours after your meal.",
        "https://www.swaminarayan.faith/media/3212/bhramari.jpg",
        "https://www.youtube.com/watch?v=hR2ewXJIZSo"
        )

    private val pranayams by lazy { listOf(pranayam1, pranayam2) }

}