package com.health.fitfriend.datasource.impl

import com.health.fitfriend.datasource.MeditationDataSource
import com.health.fitfriend.model.Meditation
import org.springframework.stereotype.Repository

@Repository
class MeditationDataSourceImpl : MeditationDataSource {

    override fun retriveMeditations(): Collection<Meditation> {
        return meditations
    }

    override fun retriveMeditationByName(name: String): Meditation =
        meditations.firstOrNull { it.name.equals(name, ignoreCase = true) }
            ?: throw NoSuchElementException("Couldn't find any meditation named $name")

    override fun retriveMeditationById(id: Int): Meditation =
        meditations.firstOrNull { it.id == id } ?: throw NoSuchElementException("No meditation found for id $id")

    override fun addMeditation(meditation: Meditation): Meditation {
        if (meditations.any { it.id == meditation.id }) {
            throw IllegalArgumentException("Meditation with id ${meditation.id} already exists")
        }
        meditations.add(meditation)
        meditations.sortBy { it.id }
        return meditation
    }

    override fun updateMeditation(meditation: Meditation): Meditation {
        val meditationToUpdate = meditations.firstOrNull{ it.id == meditation.id }
            ?: throw NoSuchElementException("Couldn't find any meditation with id ${meditation.id}")
        meditations.remove(meditationToUpdate)
        meditations.add(meditation)
        meditations.sortBy { it.id }
        return meditation
    }

    override fun deleteMeditation(id: Int) {
        val meditationToDelete = meditations.firstOrNull{ it.id == id }
            ?: throw NoSuchElementException("Couldn't find any meditation with id $id")
        meditations.remove(meditationToDelete)
    }

    val meditation1 = Meditation(1,"Dhyana", "How do you learn to meditate? In mindfulness meditation, we’re learning how to pay attention to the breath as it goes in and out, and notice when the mind wanders from this task. This practice of returning to the breath builds the muscles of attention and mindfulness.\n" +
            "When we pay attention to our breath, we are learning how to return to, and remain in, the present moment—to anchor ourselves in the here and now on purpose, without judgement.\n" +
            "The idea behind mindfulness seems simple—the practice takes patience. Indeed, renowned meditation teacher Sharon Salzberg recounts that her first experience with meditation showed her how quickly the mind gets caught up in other tasks. “I thought, okay, what will it be, like, 800 breaths before my mind starts to wander? And to my absolute amazement, it was one breath, and I’d be gone,” says Salzberg.\n" +
            "While meditation isn’t a cure-all, it can certainly provide some much-needed space in your life. Sometimes, that’s all we need to make better choices for ourselves, our families, and our communities. And the most important tools you can bring with you to your meditation practice are a little patience, some kindness for yourself, and a comfortable place to sit.",
    "Understanding your pain\n" +
            "Lower your stress\n" +
            "Connect better\n" +
            "Improve focus\n" +
            "Reduce brain chatter",
        "Meditation is simpler (and harder) than most people think. Read these steps, make sure you’re somewhere where you can relax into this process, set a timer, and give it a shot:\n" +
                "1) Take a seat\n" +
                "Find place to sit that feels calm and quiet to you.\n" +
                "2) Set a time limit\n" +
                "If you’re just beginning, it can help to choose a short time, such as five or 10 minutes.\n" +
                "3) Notice your body\n" +
                "You can sit in a chair with your feet on the floor, you can sit loosely cross-legged, you can kneel—all are fine. Just make sure you are stable and in a position you can stay in for a while.\n" +
                "4) Feel your breath\n" +
                "Follow the sensation of your breath as it goes in and as it goes out.\n" +
                "5) Notice when your mind has wandered\n" +
                "Inevitably, your attention will leave the breath and wander to other places. When you get around to noticing that your mind has wandered—in a few seconds, a minute, five minutes—simply return your attention to the breath.\n" +
                "6) Be kind to your wandering mind\n" +
                "Don’t judge yourself or obsess over the content of the thoughts you find yourself lost in. Just come back.\n" +
                "7) Close with kindness\n" +
                "When you’re ready, gently lift your gaze (if your eyes are closed, open them). Take a moment and notice any sounds in the environment. Notice how your body feels right now. Notice your thoughts and emotions.\n" +
                "That’s it! That’s the practice. You focus your attention, your mind wanders, you bring it back, and you try to do it as kindly as possible (as many times as you need to).",
        "Hypo tension (low blood pressure) may result from meditation. Therefore, it is not prudent to stand up suddenly after a long sitting, as one may faint. Although this has been noted rarely, it is feasible. \n" +
                "You may feel strong emotions during this meditation, this is natural as you come in contact with your true self, allow these emotions to arise and fall away naturally, accept them and do not judge them.\n" +
                "If you feel very disturbed by any emotions, please contact a professional counselor, they will help you more fully understand yourself.\n" +
                "Do not expect immediate results. The purpose of meditation is not to turn you into a Zen master overnight. Meditation works best when it is done for its own sake, without becoming attached to results.\n" +
                "If you find your mind is wandering, try not to scold or beat up on yourself about it. Wandering restlessly is the normal state of the conditioned mind. This is the first lesson many people learn in meditation and it is a valuable one. Simply, gently, invite your attention back to your breath, remembering that you have just had a small but precious \"awakening.\" Becoming aware of your wandering mind is a success, not a failure.\n" +
                "Some people find it is difficult to meditate immediately before bedtime. If you are very sleepy, you may find yourself nodding off. Conversely, meditating may energize your mind, making it more difficult to fall asleep.\n" +
                "As you meet other people who meditate, you may encounter a few who will boast about their endurance for long meditation sessions, even hours and hours at a sitting. Do not be tempted to change your practice to \"keep up.\" Meditation is not competitive. It is a way of life.\n" +
                "If your posture is good, you will almost certainly feel a stretch on the back of your neck, and possibly in your shoulders. Just relax. If the stretch is so pronounced that it is painful, work on stretching and relaxing that area when you are not meditating.",
        "https://img.mensxp.com/media/content/2022/Oct/yoga-lotus-pose-meditation_635b7b1822f7c.jpeg",
        "https://www.youtube.com/watch?v=inpok4MKVLM"
        )

    val meditation2 = Meditation(2, "Shambhavi Mudra","Shambhavi Mahamudra is an integrative system of several breathing techniques that incorporate various limbs of traditional Raja Yoga or yoga described in sutras by Patanjali. It is a highly regarded gesture practiced in yoga and meditation. In English this gesture is called eyebrow center gazing. The name comes from Shambhavi, the name of Parvati who is the consort of Shiva, the Hindu lord. Parvati is a symbol of divine energy. The existence of human beings is supposed to be propelled from the same energy. Connecting these dots, you will realize that shambhavi mudra is way to way to give direction to the energy that properly your life. Shambhavi mudra can bring your mind into a balanced condition and a state of a high level of consciousness. There are a number of shambhavi mudra benefits that make it one of the most revered forms of yoga and meditation. If you do all the shambhavi mudra steps properly, it will help you in gazing inside of your conscious and that will eventually remove all kinds of mental distracts. Shambhavi yoga mudra is a form of shambhavi mudra developed by Sadhguru of Isha Foundation. It is actually a course that teaches you the mudra step by step. It should be noted here that Mahamudra steps are somewhat similar to shambhavi kriya steps. So, you can learn either to get the benefits of shambhavi mudra.",
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
    val meditations by lazy { mutableListOf<Meditation>(meditation1, meditation2) }

}
