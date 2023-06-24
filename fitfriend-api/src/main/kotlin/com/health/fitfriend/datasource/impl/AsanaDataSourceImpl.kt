package com.health.fitfriend.datasource.impl

import com.health.fitfriend.datasource.AsanaDataSource
import com.health.fitfriend.model.Asana
import org.springframework.stereotype.Repository

@Repository
class AsanaDataSourceImpl : AsanaDataSource {

    override fun retrieveAsanas(): Collection<Asana> = asanas

    override fun retrieveAsanaById(id: Int): Asana =
        asanas.firstOrNull{ it.id == id }
            ?: throw NoSuchElementException("No Asana exists with id $id")

    override fun retrieveAsanaByName(name: String): Asana =
        asanas.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: throw NoSuchElementException("Cound not find asana with name $name")

    override fun createAsana(asana: Asana): Asana {
        if (asanas.any { it.id == asana.id }) throw IllegalArgumentException("Asana with same id already exists.")
        asanas.add(asana)
        return asana
    }

    override fun updateAsana(asana: Asana): Asana {
        val currentAsana = asanas.firstOrNull{ it.id == asana.id } ?:
        throw NoSuchElementException("No asana exists with id ${asana.id}")
        asanas.remove(currentAsana)
        asanas.add(asana)
        return asana
    }

    override fun deleteAsana(id: Int) {
        val asanaToDelete = asanas.firstOrNull{ it.id == id }
            ?: throw NoSuchElementException("No Such asana exists with id $id")
        asanas.remove(asanaToDelete)
    }

    val asana1 = Asana(
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

    val asana2 = Asana(
        2,
        "Uthanpadasana",
        "We all know the benefits of exercise; however, finding the discipline to start training and sticking with it is often challenging. Whether you are at the beginning of your fitness journey or somewhere in the middle of your health transformation, yoga might help you get started. In this modern world, yoga has taken its firm place in society. Many healthcare professionals have begun recommending yoga as a holistic way to improve various health conditions. It is cost-effective, and when combined with modern medicine, yoga may become a vital means of providing complete healthcare solutions for the public health system. Yoga consists of several asanas and postures that might benefit different areas of the body.1 In this blog, we bring one such Yoga pose, the Uttanpadasana pose. Let us learn more about Uttanpadasana benefits and more. ",
        "At a spiritual level, Uttanpadasana might encourage peace and ease irritation as it opens up the Muladhara (root) chakra.3 \n" +
                "Uttanpadasana pose is believed to activate the throat or Visuddha chakra. In throat chakra, your neck might get lengthened.3 \n" +
                "Uttanpadasana might open Manipura chakra (solar plexus) associated with the willpower, energy and accomplishment.  \n" +
                "Strengthens the abdominal muscles: Uthana Padasana primarily targets the abdominal muscles, including the rectus abdominis and the obliques. Regular practice of this pose helps to strengthen and tone the abdominal muscles, leading to improved core stability and better posture.\n" +
                "Builds leg strength: This asana engages the muscles of the legs, including the quadriceps, hamstrings, and calves. As you lift your legs off the ground and hold the pose, it helps to strengthen and tone these muscle groups, enhancing overall leg strength.\n" +
                "Stimulates the digestive system: Uthana Padasana exerts pressure on the abdominal region, which can help improve digestion and stimulate the digestive organs. It can help relieve constipation and promote a healthy digestive system.\n" +
                "Enhances hip flexibility: This pose requires lifting the legs upward, which stretches and strengthens the hip flexor muscles. Regular practice of Uthana Padasana can lead to improved hip flexibility and mobility.\n" +
                "Improves blood circulation: Inverted poses like Uthana Padasana encourage blood flow to the upper body, including the head and brain. This increased blood circulation can improve oxygen supply to the brain, enhance mental clarity, and boost overall energy levels.\n" +
                "Tones the pelvic floor muscles: Uthana Padasana engages the muscles of the pelvic floor, including the muscles responsible for urinary and reproductive function. Strengthening these muscles can help prevent or alleviate issues such as urinary incontinence and improve overall pelvic floor health.\n" +
                "Calms the mind and reduces stress: Like many yoga poses, Uthana Padasana promotes deep breathing and relaxation. It helps calm the mind, relieve stress, and induce a state of mental tranquility. Regular practice of this pose can contribute to overall mental well-being.",
        "Lie down straight on your back and focus on your breathing.  \n" +
                "Keep your hands on the sides with the palms facing downward. Keep your upper body relaxed.  \n" +
                "To start, inhale and slowly raise your legs at a 45° angle off the ground.  \n" +
                "Hold your legs for five to ten seconds to feel the pressure in your lower abdomen and legs.   \n" +
                "Now raise your legs at a 60° angle and hold for a few seconds. This targets the abdominal muscles. Do not put pressure on your arms and lower back. \n" +
                "Finally, to complete the Uttanpadasana pose, lift both legs and keep them straight and steady at a 90° angle. This is Dwi pad Uttanpadasana pose. \n" +
                "To return to starting position, slowly lower your legs back to the ground while exhaling. Breathing normally while gradually lowering your legs might help relax your posture.  \n" +
                "In the beginning, you may hold the raised leg pose for five seconds, and if you are comfortable, increase it to 30 seconds for better results, do it three or four times a day.  \n" +
                "To practise Uttanpadasana variations, try the Ek pad Uttanpadasana pose by raising either one leg at a 90° angle. \n" +
                "If you experience difficulty raising the legs or maintaining them at 90°, slightly bend or lower your legs to release the pressure on the back.12 ",
        "Individuals with severe hypertension and insulin-dependent diabetes patients must not practise Uttanpadasana yoga as it may worsen the condition.1 \n" +
                "People with hip replacement or recent abdominal surgery should not practise raised leg pose as it puts a lot of pressure on the abdomen. Therefore, doctors’ advice is essential.2  \n" +
                "People with arthritis should avoid doing the Uttanpadasana pose as it may increase joint pain.1 \n" +
                "Women during menstruation and pregnancy should practise Uttanpadasana under the guidance of a yoga teacher to avoid further complications. ",
        "https://www.yogicwayoflife.com/wp-content/uploads/2015/09/Uttanpadasana_Raised_Leg_Pose_Yoga.jpg",
        "https://www.youtube.com/watch?v=vNjX6uZEKU4"
    )
    val asana3 = Asana(
        3,
        "Halasana",
        "This yoga pose gets its name from the plow – a popular farming tool commonly used in Indian agriculture to prepare the soil for sowing crops. Like its namesake, this pose prepares the ‘field’ of the body and mind for deep rejuvenation. Halasana is pronounced as hah-LAHS-uh-nuh.",
        "Strengthens and opens up the neck, shoulders, abs and back muscles.\n" +
                "Calms the nervous system, reduces stress and fatigue.\n" +
                "Tones the legs and improves leg flexibility.\n" +
                "Stimulates the thyroid gland and strengthens the immune system.\n" +
                "Helps women during menopause.",
        "Lie on your back with your arms beside you, palms downwards.\n" +
                "As you inhale, use your abdominal muscles to lift your feet off the floor, raising your legs vertically at a 90-degree angle.\n" +
                "Continue to breathe normally and supporting your hips and back with your hands, lift them off the ground.\n" +
                "Allow your legs to sweep in a 180-degree angle over your head till your toes touch the floor. Your back should be perpendicular to the floor. This may be difficult initially, but make an attempt for a few seconds.\n" +
                "Hold this pose and let your body relax more and more with each steady breath.\n" +
                "After about a minute (a few seconds for beginners) of resting in this pose, you may gently bring your legs down on exhalation.",
        "Do this asana slowly and gently. Ensure that you do not strain your neck or push it into the ground.\n" +
                "Support your back on the tops of your shoulders, lifting your shoulders a little towards your ears.\n" +
                "Avoid jerking your body, while bringing the legs down.",
        "https://www.healthshots.com/wp-content/uploads/2020/10/yoga-for-weight-loss-halasana.jpg",
        "https://www.youtube.com/watch?v=zOZumZ_lD1c"
    )
    val asana4 = Asana(
        4,
        "Karnapidasana",
        "Karnapidasana is a yoga pose which illustrates the principle of pratyahara - the withdrawal of the senses, when external stimuli are cut off, so that our focus can turn inwards. The ability to do this mentally takes practice, but as this pose cuts out sounds, sights and other distractions, it can help you practice mindfulness more easily.\n" +
                "Do you remember that game you played as a child, when you listened to the sound of the sea in a shell placed against your ear? Karnapidasana has the same effect; because the knees cover the ears they block out sound so all you can hear is your own breath. Nothing to do in this yoga pose but stretch out your shoulders and spine and sink into a meditative space.",
        "Like all yoga poses, Karnapidasana has many health benefits. Use it to help with the following health conditions:\n" +
                "Stress\n" +
                "Diabetes\n" +
                "Fatigue\n" +
                "Back pain\n" +
                "Headache/Migraine\n" +
                "Infertility\n" +
                "Insomnia\n" +
                "Sinusitis\n" +
                "Axiety\n" +
                "Thyroid",
        "Step 1:\n" +
                "Start in Plough Pose (Halasana).\n" +
                "Step 2:\n" +
                "Exhale and bend the legs. Take the knees to the ground beside the ears, close to the shoulders.\n" +
                "Step 3:\n" +
                "Be on the centres of the shins and stretch the feet away from you.\n" +
                "Step 4:\n" +
                "Bring the sternum forward and lift the upper body.\n" +
                "Step 5:\n" +
                "To deepen the pose, extend your arms along your lower legs, wrapping the forearms behind the thighs to clasp the fingers together.\n" +
                "Step 6:\n" +
                "Stay in the pose for approximately 5 to 10 breaths, breathing deeply.\n" +
                "Step 7:\n" +
                "To come out, release the hands and slowly bring the bent legs back to Plough. Stay here a moment, then roll down vertebrae by verterbrae until your back is flat. Bring your knees into your chest and give them a squeeze.",
        "Don’t practice Karnapidasana if you have any of the following health conditions:\n" +
                "Diarrhoea\n" +
                "Menstruation\n" +
                "Neck injury\n" +
                "Asthma\n" +
                "High blood pressure",
        "https://www.theayurveda.org/wp-content/uploads/2016/07/Karnapidasana-yoga-pose.jpg",
        "https://www.youtube.com/watch?v=hdO-aQuNnHc"
    )

    val asana5 = Asana(
        5, "Naukasana", "Nauka = Boat, Asana = Posture or Pose\n" +
                "This yoga posture has been named after the shape it takes – that of a boat. Naukasana is prounced as NAUK-AAHS-uh-nuh.",
        "Benefits of the Boat Pose (Naukasana)\n" +
                "Strengthens the back and abdominal muscles\n" +
                "Tones the leg and arm muscles\n" +
                "Useful for people with hernia",
        "Lie on your back with your feet together and arms beside your body.\n" +
                "Take a deep breath in and as you exhale, lift your chest and feet off the ground, stretching your arms towards your feet.\n" +
                "Your eyes, fingers and toes should be in a line.\n" +
                "Feel the tension in your navel area as the abdominal muscles contract.\n" +
                "Keep breathing deeply and easily while maintaining the pose.\n" +
                "As you exhale, come back to the ground slowly and relax.",
        "Do not practice this yoga pose if you have low blood pressure, severe headache, migraine, or if you have suffered from some chronic diseases or spinal disorders in the recent past.\n" +
                "Asthma and heart patients are advised to avoid this pose.\n" +
                "Women should avoid doing Boat pose (Naukasana) during pregnancy and during the first two days of the menstrual cycle.",
        "https://www.yogicwayoflife.com/wp-content/uploads/2015/09/Naukasana_Boat_Pose_Yoga_Asana.jpg",
        "https://www.youtube.com/watch?v=AV7VHk5qlHg"
    )

    val asana6 = Asana(
        6,
        "Pawanmuktasana",
        "There is a very old saying in India, that if the brain and belly are healthy then a man is wealthy; meaning, when the mind is at peace and there is nothing ailing the stomach, then that person is fit and calm. The two, in fact, are connected hence to have a peaceful mind it is absolutely essential that our digestive system remains strong and Wind-Relieving Pose (Pawanmuktasana) yoga pose is a perfect cure for gas and constipation, two niggling problems of the stomach.",
        "Strengthens the back and abdominal muscles\n" +
                "Tones the leg and arm muscles\n" +
                "Massages the intestines and other organs in the abdomen\n" +
                "Helps in digestion and release of gas\n" +
                "Enhances blood circulation in the hip joints and eases tension in the lower back",
        "Lie on your back with your feet together and arms beside your body.\n" +
                "Breathe in and as you exhale, bring your right knee towards your chest and press the thigh on your abdomen with clasped hands.\n" +
                "Breathe in again and as you exhale, lift your head and chest off the floor and touch your chin to your right knee.\n" +
                "Hold it there, as you take deep, long breaths in and out.\n" +
                "Checkpoint: As you exhale, tighten the grip of the hands on the knee and increase the pressure on the chest. As you\n" +
                "inhale, loosen the grip. \n" +
                "As you exhale, come back to the ground and relax.\n" +
                "Repeat this pose with the left leg and then with both the legs together.\n" +
                "You may rock up and down or roll from side to side 3-5 times and then relax. ",
        "Avoid practicing Wind-Relieving Pose (Pawanmuktasana) if you are facing the following health issues: high blood pressure, heart problem, hyperacidity, hernia, slip disc, testicle disorder, menstruation, neck and back problems.\n" +
                "And after the second trimester of pregnancy.",
        "https://www.healthshots.com/wp-content/uploads/2020/10/pawanmuktasana.jpg",
        "https://www.youtube.com/watch?v=B6uIHl5zJz0"
    )

    val asana7 = Asana(
        7,
        "Kandharasana",
        "he Shoulder Pose is excellent for strengthening the female reproductive organs and for correcting many vertebral disc problems. Kandharasana relieves menstrual problems and disorders related to the uterus. However, Kandharasana should not be practiced during pregnancy. Not only does it increase both a man’s and woman’s sexual desire, it also helps in strengthening the functioning of a man’s sperms and a woman’s ovaries — helping couples overcome infertility. Apart from that it also remedies disorders of the thyroid gland, strengthens the back and shoulder, massages the spine and fights bad posture. The best part about this pose is that it helps improve the functioning of the lungs, reproductive organs and digestive system. Apart from helping women with menstrual disorders, it also helps beat vaginal discharge due to infections and increases lubrication within the vagina. So if you want to really steam things up between the sheets this asana is for you ",
        "It can prevent miscarriage by improving the health of the uterus.\n" +
                "Kandharasana strengthens the female reproductive organs. It should be practiced before pregnancy. But during pregnancy avoid practicing this asana.\n" +
                "It can correct some vertebral disc problems and remove back ache.\n" +
                "It strengthens the shoulders.\n" +
                "Kandharasana tones all the organs in the abdomen.",
        "Lie down flat on the back on the floor on your yoga mat.\n" +
                "Bend the legs at the knees and bring the feet close to the buttocks.\n" +
                "Grab the ankles with the hands and pull it backwards till the feet touches the buttocks.\n" +
                "Retain the breath inside and raise the buttocks. Also arch the back upwards. The feet and the shoulder portion should remain on the ground.\n" +
                "In this position, the body weight is supported by the feet, the shoulders, the arms, the neck and the head.\n" +
                "This is the final pose. Retain it for as long as you are comfortable.\n" +
                "To release the asana, bring down the buttocks back to the ground. The knees can remain in the bend position.\n" +
                "Repeat this for 5 to 10 times depending on strength and convenience.\n" +
                "After performing the asana it is good to do a forward bending counter pose like Paschimottansana.",
        "f you suffer from high blood pressure, lower back pain or any other spinal disorders do this pose while being supervised by a trained professional.\n" +
                "Kandharasana should not be practiced during pregnancy",
        "https://www.finessyoga.com/wp-content/uploads/2017/12/yoga-Bridge-Pose.jpg",
        "https://www.youtube.com/watch?v=krxntp8rTHQ"
    )

    val asana8 = Asana(
        8,
        "Padangusthasana",
        "The Big Toe Pose, also known as Padangusthasana in Sanskrit, is a standing forward bend pose in yoga that focuses on stretching and lengthening the hamstrings, calves, and back muscles. It is a beginner-friendly pose that offers various benefits for the body and mind.",
        "Calms the brain and helps relieve stress, anxiety and mild\n" +
                "Stimulates the liver and kidneys\n" +
                "Stretches the hamstrings and calves\n" +
                "Strengthens the thighs\n" +
                "Improves digestion\n" +
                "Helps relieve the symptoms of menopause\n" +
                "Helps relieve headache and insomnia",
        "Stand upright with your inner feet parallel and about six inches apart. Contract your front thigh muscles to lift your kneecaps. Keeping your legs completely straight, exhale and bend forward from your hip joints, moving your torso and head as one unit.\n" +
                "Slide the index and middle fingers of each hand between the big toes and the second toes. Then curl those fingers under and grip the big toes firmly, wrapping the thumbs around the other two fingers to secure the wrap. Press your toes down against your fingers. (If you can’t reach your toes without overly rounding your back, pass a strap under the ball of each foot and hold the straps.)\n" +
                "With an inhalation, lift your torso as if you were going to stand up again, straightening your elbows. Lengthen your front torso, and on the next exhale, lift your sitting bones. Depending on your flexibility, your lower back will hollow to a greater or lesser degree. As you do this, release your hamstrings and hollow your lower belly (below your navel) as well, lightly lifting it toward the back of your pelvis.\n" +
                "Lift the top of your sternum as high as you can, but take care not to lift your head so far that you compress the back of your neck. Keep your forehead relaxed.\n" +
                "For the next few inhalations, lift your torso strongly as you continue to actively contract your front thighs; on each successive exhalation, strongly lift your sitting bones as you consciously relax your hamstrings. As you do this, deepen the hollow in your lower back.\n" +
                "Finally exhale, bend your elbows out to the sides, pull up on your toes, lengthen the front and sides of your torso, and gently lower into the forward bend.\n" +
                "If you have very long hamstrings, you can draw your forehead toward your shins. But if your hamstrings are short, it’s better to focus on keeping the front torso long. Hunching into a forward bend isn’t safe for your lower back and does nothing to lengthen your hamstrings.\n" +
                "Hold the final position for one minute. Then release your toes, bring your hands to your hips, and re-lengthen your front torso. With an inhale, swing your torso and head as a single unit back to upright.",
        "Avoid this pose with lower back or neck injuries\n",
        "https://cdn.yogajournal.com/wp-content/uploads/2007/08/big-toe-pose-1.jpg?crop=535:301&width=1070",
        "https://www.youtube.com/watch?v=_CEL5TX15Lg"
    )

    val asana9 = Asana(
        9, "Matsyasana", "Matsya – Fish; Asana – Pose\n" +
                "This asana is pronounced as mut-see-ahs-ana\n" +
                "The fish pose, if carried out in water, allows the body to float quite easily like that of a fish; hence the name.\n",
        "Stretches the chest and neck\n" +
                "Helps relieve tension in the neck and shoulders\n" +
                "Provides relief from respiratory disorders by encouraging deep breathing\n" +
                "Tones the parathyroid, pituitary and pineal glands",
        "Lie on your back. Your feet are together and hands relaxed alongside the body.\n" +
                "Place the hands underneath the hips, palms facing down. Bring the elbows closer toward each other.\n" +
                "Breathing in, lift the head and chest up.\n" +
                "Keeping the chest elevated, lower the head backward and touch the top of the head to the floor.\n" +
                "With the head lightly touching the floor, press the elbows firmly into the ground, placing the weight on the elbow and not on the head. Lift your chest up from in-between the shoulder blades. Press the thighs and legs to the floor.\n" +
                "Hold the pose for as long as you comfortably can, taking gentle long breaths in and out. Relax in the posture with every exhalation.\n" +
                "Now lift the head up, lowering the chest and head to the floor. Bring the hands back along the sides of the body. Relax.\n",
        "Avoid this posture if you have high or low blood pressure. Migraine and insomnia patients should also refrain from doing the Fish Pose. Those who have had serious lower-back or neck injuries are strongly recommended not to practice this pose.",
        "https://img.mensxp.com/media/content/2019/Apr/header-shutterstock-1556174295.jpg",
        "https://www.youtube.com/watch?v=XncL86onXHk"
    )
    val asana10 = Asana(
        10,
        "Bhujangasana",
        "Bhujangasana (Cobra Stretch) comes from the word bhujanga meaning cobra or snake and asana meaning pose. Bhujangasana is also known as Cobra Stretch. This pose is included in Suryanamaskar (Sun Salutations Pose) as well as Padma Sadhana. \n" +
                "Do you want to tone your abdomen but just can’t find the time to go to the gym? Are you feeling tired or stressed due to excessive workload? \n" +
                "Bhujangasana or Cobra Stretch is a solution to solve these and many other problems, just sitting (or lying down) at home! Bhujangasana, the Cobra Pose, is a pose that you do while lying down on your stomach. It gives your body (especially the back), a good stretch that melts your stress away almost instantly!",
        "Opens up the shoulders and neck to relieve pain\n" +
                "Tones the abdomen\n" +
                "Strengthens the entire back and shoulders\n" +
                "Improves flexibility of the upper and middle back\n" +
                "Expands the chest\n" +
                "Improves blood circulation\n" +
                "Reduces fatigue and stress\n" +
                "Useful for people with respiratory disorders such as asthma",
        "Lie down on your stomach with your toes flat on the floor, soles facing upwards; rest your forehead on the ground.\n" +
                "Keep your legs close together, with your feet and heels lightly touching each other.\n" +
                "Place both hands in such a way that palms are touching ground under your shoulders, elbows should be parallel and close to your torso. \n" +
                "Taking a deep breath in, slowly lift your head, chest and abdomen. Keep your navel on the floor.\n" +
                "Pull your torso back and off the floor with the support of your hands. Make sure that you are putting equal pressure on both the palms.\n" +
                "Keep breathing with awareness, as you curve your spine, vertebra by vertebra. If possible, straighten your arms by arching your back as much as possible; tilt your head back and look up \n" +
                "Maintain the pose while breathing evenly for 4-5 breaths. \n" +
                "Now, breathe out, and gently bring your abdomen, chest, and head back to the floor and relax.\n" +
                "Repeat 4-5 times.",
        "Make sure you perform this asana 4-5 hours after having your main meal. This will ensure that you are not uncomfortable lying on your stomach.\n" +
                "Ensure that you have done some basic warm-up and stretching exercises to loosen your arms, shoulders, neck, and back.\n" +
                "It is always best to practice yoga asanas in the morning. However, if you are unable to, make time in the evening.",
        "https://imgeng.jagran.com/images/2023/jan/cobrapose1672980888540.jpg",
        "https://www.youtube.com/watch?v=fOdrW7nf9gw"
    )

    val asana11 = Asana(
        11,
        "Dhanurasana",
        "Dhanurasana has been named after the shape the body takes while performing it – that of a bow. Dhanu means bow and asana means posture or pose. Just as a well-strung bow is an asset to a warrior, a well-stretched body helps keep you flexible with a good posture.",
        "Strengthens the back and abdominal muscles\n" +
                "Stimulates the reproductive organs\n" +
                "Opens up the chest, neck, and shoulders\n" +
                "Tones the leg and arm muscles\n" +
                "Adds greater flexibility to the back\n" +
                "Alleviates stress and fatigue \n" +
                "Relieves menstrual discomfort and constipation\n" +
                "Helps people with renal (kidney) disorders\n" +
                "Full body sequences like Padma Sadhana also include Dhanurasana (Bow Pose).",
        "Lie on your stomach with your feet apart, in line with your hips, and your arms by the side of your body.\n" +
                "Fold your knees, take your hands backward, and hold your ankles.\n" +
                "Breathe in, and lift your chest off the ground and pull your legs up and towards the back. \n" +
                "Look straight ahead with a smile on your face.\n" +
                "Keep the pose stable while paying attention to your breath. Your body is now curved and as taut as a bow.\n" +
                "Continue to take long, deep breaths as you relax in this pose. But, bend only as far as your body permits you to. Do not overdo the stretch.\n" +
                "After 15 -20 seconds, as you exhale, gently bring your legs and chest to the ground. Release the ankles and relax.",
        "Ladies should avoid practicing this yoga pose during pregnancy. Also, do not practice Dhanurasana (Bow Pose) if you have\n" +
                "High or low blood pressure\n" +
                "Hernia\n" +
                "Neck injury\n" +
                "Pain in the lower back\n" +
                "Headache or migraine\n" +
                "Recent abdominal surgery\n",
        "https://www.keralatourism.org/images/yoga/static-banner/large/Dhanurasana_-_The_Bow_Pose-07032020150325.jpg",
        "https://www.youtube.com/watch?v=xm00XMmBbto"
    )

    val asana12 = Asana(
        12,
        "Ustrasana",
        "Ustrasana is an intermediate level back-bending yoga posture known to open Anahata (Heart chakra). This yoga posture adds flexibility and strength to the body and also helps in improving digestion.\n",
        "Improves digestion.\n" +
                "Stretches and opens the front of the body. It also strengthens the back and shoulders.\n" +
                "Relieves the body of lower back ache.\n" +
                "Improves flexibility of the spine and also improves posture.\n" +
                "Helps overcome menstrual discomfort.",
        "Kneel on the yoga mat and place your hands on the hips.\n" +
                "Your knees should be in line with the shoulders and the sole of your feet should be facing the ceiling.\n" +
                "As you inhale, draw in your tail-bone towards the pubis as if being pulled from the navel.\n" +
                "Simultaneously, arch your back and slide your palms over your feet till the arms are straight.\n" +
                "Do not strain or flex your neck but keep it in a neutral position.\n" +
                "Stay in this posture for a couple of breaths.\n" +
                "Breathe out and slowly come back to the initial pose. Withdraw your hands and bring them back to your hips as you straighten up.",
        "Back injury or neck injury, high or low blood pressure: Perform this pose only with the supervision of an experienced teacher.",
        "https://femina.wwmindia.com/content/2021/sep/thyoga1632838160.jpg",
        "https://www.youtube.com/watch?v=SHi7k92QJlg"
    )

    val asana13 = Asana(
        13, "Padmasana",
        "Padmasana or Lotus position is a cross-legged yoga posture which helps deepen meditation by calming the mind and alleviating various physical ailments. A regular practice of this posture aids in overall blossoming of the practitioner, just like a lotus; and hence the name Padmasana. In Chinese and Tibetan Buddhism, the Lotus pose is also known as Vajra position.\n",
        "Improves digestion\n" +
                "Reduces muscular tension and brings blood pressure under control\n" +
                "Relaxes the mind\n" +
                "Helps pregnant ladies during childbirth\n" +
                "Reduces menstrual discomfort",
        "Sit on the floor or on a mat with legs stretched out in front of you while keeping the spine erect.\n" +
                "Bend the right knee and place it on the left thigh. Make sure that the sole of the feet point upward and the heel is close to the abdomen.\n" +
                "Now, repeat the same step with the other leg.\n" +
                "With both the legs crossed and feet placed on opposite thighs, place your hands on the knees in mudra position.\n" +
                "Keep the head straight and spine erect.\n" +
                "Hold and continue with gentle long breaths in and out.",
        "There are no precautions for padmasana, Happy Yoga :).",
        "https://images.onlymyhealth.com/imported/images/2022/October/05_Oct_2022/main-padmasanabenefits.jpg",
        "https://www.youtube.com/watch?v=w_j4lnfRC38"
    )
    val asana14 = Asana(
        14, "Shavasana",
        "Shavasana is the pronunciation of the Sanskrit word “savasana.” It’s a resting and restorative pose, or asana, typically used at the end of a yoga session. The Sanskrit word actually means “corpse pose,” because students practicing this pose lie face-up on the ground, arms and legs comfortably spread, eyes closed. The purpose of the pose is to calm the mind and body, releasing stress and grounding the body. Sounds a lot like meditation, doesn’t it?",
        "Perhaps the most important benefit of Shavasana and meditation is promoting relaxation. When we’re relaxed, our parasympathetic nervous system is activated, causing a lowered heart rate, a sense of calm, and a decreased release of stress hormones like adrenaline and cortisol. And the benefits of reducing stress are widely known: it can diminish the symptoms of many health conditions including inflammation, anxiety, and insomnia. Plus, reducing stress makes us feel good if not really good.\n" +
                "Shavasana and generalized meditation have other science-backed benefits, too, including:\n" +
                "Reducing fatigue\n" +
                "Increasing focus and concentration\n" +
                "Boosting energy, mood, self-confidence, and compassion\n" +
                "Improving relationships.",
        "A good introduction to Shavasana meditation is the body scan technique. To begin, lie flat on your back, arms and legs comfortably extended, eyes closed, breathing deeply. Start at the top of the head and gradually scan down your body from head to toe, relaxing one body part at a time. Try imagining each body part getting heavier and spreading out a little more.\n" +

                "As you scan down the body, notice which parts feel relaxed or tense, comfortable or uncomfortable, light or heavy. If thoughts arise, simply notice them and trust that the breath will carry you back to a place of stillness. Through regular practice, you’ll be able to access this place of quiet and ease more easily and deeply. You may even begin to notice that your breath sounds quieter — if you notice it at all.",
        "There are not precautions to be followed while doing shavasana, Happy Yoga :)",
        "https://images.squarespace-cdn.com/content/v1/594a696c20099ea9e1fcb37e/1518703887913-DLPS0S5DDH19HBU2KBOE/shavasana?format=1000w",
        "https://www.youtube.com/watch?v=SfAoPVt64LE"
    )

    val asana15 = Asana(
        15,
        "Tadasana",
        "Tadasana yoga or the Mountain Pose is suitable for all levels of yoga practitioners and is considered to be the foundation of all standing yoga postures. This pose requires the engagement of the entire body while relaxing the mind.\n",
        "It improves posture, opens up the chest and lengthens the spine\n" +
                "Gently strengthens the thighs, buttock and leg muscles\n" +
                "It is also beneficial in increasing awareness and concentration\n" +
                "Reduces flat feet and relieves sciatica\n" +
                "Releases tension from the face",
        "Stand with your feet slightly apart and make sure that your weight is balanced equally on both feet.\n" +
                "Inhale, raise your arms above your head, interlock your fingers with palms facing upwards.\n" +
                "Raise your shoulders up towards your ears and on an exhale, roll your shoulders back and down your spine, opening your chest and straightening your posture.\n" +
                "Relax all muscles in your face, including your tongue.\n" +
                "Relax your eyes and maintain a steady gaze.\n" +
                "Come back to normal position and relax.",
        "There are not precautions to be followed while doing tadasana, Happy Yoga :)",
        "https://cdn.cdnparenting.com/articles/2019/04/09113309/747285775-H-1024x700.webp",
        "https://www.youtube.com/watch?v=rPJJIacKWsk"
    )

    val asana16 = Asana(
        16,
        "Vrikshasana",
        "This pose leaves you in a state of rejuvenation. It stretches the legs, back and    arms, and invigorates you.\n",
        "This pose leaves you in a state of rejuvenation. It stretches the legs, back and    arms, and invigorates you.\n" +
                "It brings balance and equilibrium to your mind.\n" +
                "It helps improve concentration.\n" +
                "This posture has been found to relieve some cases of sciatica.\n" +
                "It makes the legs strong, improves balance, and opens the hips.\n" +
                "Helps those who are suffering from sciatica.",
        "Stand tall and straight with arms by the side of your body.\n" +
                "Bend your right knee and place the right foot high up on your left thigh. The sole of the foot should be placed flat and firmly near the root of the thigh.\n" +
                "Make sure that your left leg is straight. Find your balance.\n" +
                "Once you are well balanced, take a deep breath in, gracefully raise your arms over your head from the side, and bring your palms together in ‘Namaste’ mudra (hands-folded position). \n" +
                "Look straight ahead in front of you, at a distant object. A steady gaze helps maintain a steady balance.\n" +
                "Ensure that your spine is straight. Your entire body should be taut, like a stretched elastic band. Keep taking in long deep breaths. With each exhalation, relax the body more and more. Just be with the body and the breath with a gentle smile on your face.\n" +
                "With slow exhalation, gently bring down your hands from the sides. You may gently release the right leg.\n" +
                "Stand tall and straight as you did at the beginning of the posture. Repeat this pose with the left leg off the ground on the right thigh.",
        "Avoid doing this posture if you are suffering from migraine, insomnia, low or high blood pressure (those with high blood pressure may do this pose but without raising their hands overhead, as this may further raise their blood pressure).",
        "https://cdn2.stylecraze.com/wp-content/uploads/2013/09/2339-How-To-Do-The-Vrikshasana-And-What-Are-Its-Benefits-ss.jpg",
        "https://www.youtube.com/watch?v=SfLrMwcxw1g"
    )

    val asana17 = Asana(
        17,
        "Vajrasana",
        "‘Vajra’ means diamond-shaped or thunderbolt; ‘asana’ means posture or pose. Vajrasana has been named after the shape it takes – a diamond or thunderbolt. \n" +
                "This asana is pronounced as vahj-RAH-sah-na.\n" +
                "If there is one holistic pose that you can slip into easily, and still gain a variety of benefits, it is the Vajrasana, the Adamantine Pose.",
        "Enhances blood circulation in the lower abdomen, improving digestion.\n" +
                "Relieves excessive gas and pain in the stomach region.\n" +
                "Strengthens the nerves of legs and thighs. \n" +
                "Makes knees and ankle joints flexible, preventing certain rheumatic diseases.\n" +
                "Keeps the neck and spine aligned and erect, without much effort, enabling easy energy flow in the nadis (energy channels) in the back.\n" +
                "Relaxes the waist and hip region, providing relief during menstrual pain.\n" +
                "Acts as a base posture for the practice of pranayamas, as well as a preparatory pose for meditation.\n" +
                "Helps in relieving back pain",
        "Sit with your legs stretched straight in front of you.\n" +
                "Now, fold both the legs and sit in a kneeling position. Keep the hips on the heels; the toes should point out behind you, and your big toes should touch each other at the back.\n" +
                "If you are a beginner, you may want to keep a cushion under your feet for comfort to prevent ankle pain.\n" +
                "You can also choose to keep a cushion or blanket above the feet and below the knees, in case of knee pain. Don’t forget to consult your doctor in case of some special medical conditions.\n" +
                "Sit comfortably on the pit formed by the parted heels.\n" +
                "Keep your head, neck, and spine in a straight line. Place your palms on your thighs, facing upwards.\n" +
                "If you are an advanced yoga practitioner, hold this pose for about 15 minutes, while taking long and deep breaths. Beginners may start with about 30 seconds, according to their comfort level. \n" +
                "Exhale and relax. \n" +
                "Straighten your legs.",
        "Those who have acute trouble or stiffness in foot, ankle, and knees.\n" +
                "Those with a slipped disc. \n" +
                "Those who have difficulty in the movement of limbs must take great care.",
        "https://static-bebeautiful-in.unileverservices.com/Your-Complete-Guide-to-Reaping-the-Benefits-of-Vajrasana_mobilehome.jpg",
        "https://www.youtube.com/watch?v=qLsZLHCWPlE"
    )

    val asana18 = Asana(
        18, "Paschimottanasana", "Seated Forward Bend",
        "Stretches lower back, hamstrings and hips.\n" +
                "Massages and tones the abdominal and pelvic organs.\n" +
                "Tones the shoulders.",
        "Sit up with the legs stretched out straight in front of you, keeping the spine erect and toes flexed toward you.\n" +
                "Breathing in, raise both arms above your head and stretch up.\n" +
                "Breathing out, bend forward from the hip joints, chin moving toward the toes. Keep the spine erect focusing on moving forwards towards the toes, rather than down towards the knees.\n" +
                "Place your hands on your legs, wherever they reach, without forcing. If you can, take hold of your toes and pull on them to help you go forward.\n" +
                "Paschimottanasana Yoga Pose - Seated Forward Bend Yoga Pose\n" +
                "Breathing in, lift your head slightly and lengthen your spine.\n" +
                "Breathing out, gently move the navel towards the knees.\n" +
                "Repeat this movement two or three times.\n" +
                "Drop your head down and breathe deeply for 20-60 seconds.\n" +
                "Stretch the arms out in front of you.\n" +
                "Breathing in, with the strength of your arms, come back up to the sitting position.\n" +
                "Breathe out and lower the arms.",
        "In case you have suffered from an injury to your back, arms, ankles or hip in recent times, you should not attempt to perform this yoga pose.\n" +
                "Pregnant women should not go all the way down and keep their feet apart while practising Paschimottanasana.",
        "https://www.arhantayoga.org/wp-content/uploads/2022/03/Seated-Forward-Bend-%E2%80%93-Paschimottanasana.jpg",
        "https://theyogainstitute.org/yogendra-paschimottanasana/"
    )

    val asana19 = Asana(
        19,
        "Makarasana",
        "Makarasana (Mah-car-ah-SUN-uh) — is a deeply restorative posture that is part of the Padma Sadhana sequence. Crocodile pose relaxes the entire nervous system and is an excellent posture to use in between back strengthening poses. It gets its name from the Sanskrit words, Makara, meaning crocodile, and asana, meaning pose.",
        "Provides deep relaxation\n" +
                "Releases tension in the back\n" +
                "Relieves stress",
        "Lie on your stomach.\n" +
                "Bend your right leg, creating a 45 degree angle with your thigh and calf.\n" +
                "Allow your left leg to lie straight behind you.\n" +
                "Place your left cheek on your mat and gaze to the right.\n" +
                "Place your hands underneath your left cheek, creating a pillow.\n" +
                "Rest in this pose for up to 15 minutes. Breathe deeply.",
        "Pregnancy\n",
        "https://st.depositphotos.com/1000833/3022/i/950/depositphotos_30228073-stock-photo-young-man-in-makarasana-pose.jpg",
        "https://www.youtube.com/watch?v=0kisFU80NbI"
    )

    val asana20 = Asana(
        20,
        "Balasana",
        "Balasana is composed of two Sanskrit words “bala” which translates as child, and “asana,” which means pose or seat. Thus, it is commonly known “child’s pose.” The name comes from the fact that it is a pose that resembles a child in a resting position. Relaxing and breathing deeply in Balasana can provide a sense of calm and comfort, just like when a child rests in its mother’s arms.\n",
        "Child pose calms the body, mind and spirit and stimulates the third eye point. Child pose gently stretches the low back, massages and tones the abdominal organs, and stimulates digestion and elimination. It helps relieve back and neck pain and muscular tension. Mentally, this grounding pose is a great way to let go of worries, rest, relax, and rejuvenate to provide relief from stress, anxiety, and fatigue. Emotionally, this pose is a great way to connect with your inner child and foster a sense of calm, comfort, contentment, and safety.",
        "Once you feel you have found your balance in this posture, simply spread your knees hip-width apart. Slowly and softly, inhale. This is a nice pose in yoga for good sleep.\n" +
                "Exhale and lay your torso between your thighs and legs to experience yoga for better sleep.\n" +
                "For this pose slowly settle down on your thighs by broadening your sacrum all across the back of the pelvis. Feel that your tailbone is stretching away from the back of the pelvis as you slightly lift your head away from the back of the neck.\n" +
                "Now simply extend your arms and hands in front of you and stretch them out straight. The shoulders should not be strained, let them drop on the floor.\n" +
                "Balasana Yoga for Sleep is helpful because it helps to open up the shoulder blades across your back.\n" +
                "Stay in this posture for 30 seconds or more. It is a resting posture, and so, you can simply stay in it for more than a few minutes too, if you want you can use a pillow for additional support. You will soon find out why people prefer balasana yoga pose or child pose yoga in general!\n" +
                "To come out of this sleeping asana, simply stretch the upper body slowly followed by your hands. Then simply inhale and lift from the tailbone, thus pushing down into the pelvic region. You will no longer have any doubts about the benefits of child pose yoga and about yoga for better sleep in general.",
        "When placing the head on the floor on the mat you feel some difficulty or find it uncomfortable, then you can use a pillow or a cushion for support.\n" +
                "If you are suffering from diarrhoea, then avoid yoga before sleep as it massages the digestive tract and creates pressure on the bowels.\n" +
                "If you have injuries in your legs or knee problems, then refrain from getting into this sleeping asana.\n" +
                "If you are a patient of hypertension, then this yoga before sleep must be avoided.\n" +
                "Although yoga for better sleep has been proven, you may need to see a doctor if your insomnia isn’t reduced by practising sleeping asanas.",
        "https://www.godigit.com/content/dam/godigit/directportal/en/contenthm/balasana-in-yoga.jpg",
        "https://www.youtube.com/watch?v=2MJGg-dUKh0"
    )

    val asana21 = Asana(
        21,
        "Chakrasana",
        "Chakrasana (CHAAH-kraa-SUN-ah) or Urdhva Dhanurasana — also called as Full-Wheel Pose and Upward Bow Pose, is a backbending posture that opens up the chest, tones the thighs, abdomen and arms, and engages the whole body. As a heart-opening stretch, this pose helps release sadness and depression. It gets its name from the Sanskrit words, chakra, meaning wheel, and asana, meaning posture.",
        "Increases energy and heat\n" +
                "Strengthens the arms, legs, spine, and abdomen\n" +
                "Opens the chest\n" +
                "Stretches the shoulders\n" +
                "Stretches the hip flexors and core\n" +
                "Strengthens the glutes and thighs\n" +
                "Increases flexibility in the spine",
        "To begin, lie on your back.\n" +
                "Bend your knees so that your feet are flat on the floor in a parallel position in line with your sitting bones, about a foot’s distance away from your hips. Press your feet firmly into the floor.\n" +
                "Place your hands on the floor just above your shoulders with your fingers facing your shoulders.\n" +
                "Press into your hands and lift your upper body off the mat, resting the crown of your head lightly on  your mat.\n" +
                "Press into your feet and lift your legs, pelvis and abdomen off of the mat, activating your inner thighs.\n" +
                "Push more into your feet, bringing more of your weight into your palms. This will protect your lower back.\n" +
                "Maintain strength and stability in your arms by continuing to press firmly into the mat.\n" +
                "Let your head hang in a neutral position, making sure not to strain your neck.\n" +
                "Hold for 5-10 breaths.\n" +
                "To exit the posture, slowly lower your arms and legs and bring your spine back onto the mat vertebrae by vertebrae.",
        "Back problems (especially lower)\n" +
                "Shoulder injury\n" +
                "Pregnancy\n" +
                "High or low blood pressure",
        "https://ramabaktha.site/wp-content/uploads/2023/04/KHADA-CHAKRASANA-.jpg",
        "https://www.youtube.com/watch?v=inuWWcuQek4"
    )

    val asana22 = Asana(
        22,
        "Markatasana",
        "Markatasana is also called the spinal twist pose, monkey pose, or markat asana. Markatasana is derived from Sanskrit words, “Markat”, meaning monkey, and “asana,” meaning yoga or posture. So, therefore it is also called the monkey pose.",
        "It increases the Flexibility of spine.\n" +
                "Good exercise for ribs and lungs.\n" +
                "It exercised the large intestine.\n" +
                "It is most beneficial in constipation and problems related to stomach.\n" +
                "It is very effective for relaxing mind along with body.\n" +
                "It enhances creativity.",
        "Roll out a mat and lie on your back with your feet together on the ground. \n" +
                "Extend the arms sideways at shoulder level. \n" +
                "Next, inhale, bend both knees, and turn both legs to the right side. Rotate your head to the right and gaze at the right hand. \n" +
                "This will give a spinal twist towards the right side. \n" +
                "Breathe out and repeat the steps with the other side (left).",
        "As Markatasana is a spinal twist pose, if you have back pain, you must avoid overstretching as it can make the pain more severe.  \n" +
                "Slip disc, spondylosis, or spinal injury requires rest and proper treatment as your doctor advises. The practice of Markatasana in these conditions is not recommended.  \n" +
                "There is no data regarding the safety of this asana in pregnancy, children, and the elderly. It is always advised to consult a qualified trainer if you want to adopt this yoga practice into your routine. ",
        "https://www.fitsri.com/wp-content/uploads/2020/10/markatasana-monkey-spinal-twist-pose.jpg",
        "https://www.fitsri.com/wp-content/uploads/2020/10/markatasana-monkey-spinal-twist-pose.jpg"
    )

    val asana23 = Asana(
        23, "Trikonasana", "The asana is pronounced as Tree-kone-nah -sah-nah  (Trikonasana)\n" +
                "Unlike most yoga postures, the Triangle Pose requires keeping the eyes open in order to maintain body balance.",
        "Strengthens the legs, knees, ankles, arms and chest\n" +
                "Stretches and opens the hips, groins, hamstrings, calves, shoulders, chest and spine\n" +
                "Increases mental and physical equilibrium\n" +
                "Helps improve digestion\n" +
                "Reduces anxiety, stress, back pain and sciatica",
        "Stand straight. Separate your feet comfortably wide apart (about 31/2 to 4 feet).\n" +
                "Turn your right foot out 90 degrees and left foot in by 15 degrees.\n" +
                "Now align the center of your right heel with the center of your arch of left foot.\n" +
                "Ensure that your feet are pressing the ground and the weight of your body is equally balanced on both the feet.\n" +
                "Inhale deeply and as you exhale, bend your body to the right, downward from the hips, keeping the waist straight, allowing your left hand to come up in the air while your right hand comes down towards floor. Keep both arms in straight line.\n" +
                "Rest your right hand on your shin, ankle, or the floor outside your right foot, whatever is possible without distorting the sides of the waist. Stretch your left arm toward the ceiling, in line with the tops of your shoulders. Keep your head in a neutral position or turn it to the left, eyes gazing softly at the left palm.\n" +
                "Ascertain that your body is bent sideways and not backward or forward. Pelvis and chest are wide open.\n" +
                "Stretch maximum and be steady. Keep taking in long deep breaths. With each exhalation, relax the body more and more. Just be with the body and the breath.\n" +
                "As you inhale, come up, bring your arms down to your sides, and straighten your feet.\n" +
                "Repeat the same on the other side.",
        "Avoid doing this pose if you are suffering from migraine, diarrhea, low or high blood pressure, or neck and back injuries. Those with high blood pressure may do this pose but without raising their hand overhead, as this may further raise the blood pressure.",
        "https://media.yogauonline.com/app/uploads/2021/04/06034507/fotolia_47221143_subscription_xl2-1.webp",
        "https://www.youtube.com/watch?v=S6gB0QHbWFE"
    )

    val asana24 = Asana(
        24,
        "Surya Namaskar",
        "Surya Namaskar or Sun Salutation is a sequence of 12 powerful yoga poses. Besides being a great cardiovascular workout, Surya Namaskar is also known to have an immensely positive impact on the body and mind.\n" +
                "Practicing Surya Namaskar steps is best done early morning on an empty stomach. Each round of Sun Salutation consists of two sets, and each set is composed of 12 yoga poses. You might find several versions on how to practice Sun Salutation. However, it is advisable to stick to one particular version and practice it regularly for the best results.\n" +
                "Besides good health, Surya Namaskar also provides an opportunity to express gratitude to the sun for sustaining life on this planet.",
        "Helps maintain cardiovascular health\n" +
                "Stimulates the nervous system\n" +
                "Helps in stretching, flexing, and toning the muscles\n" +
                "An excellent exercise for weight loss management\n" +
                "Strengthens the immune system\n" +
                "Enhances cognitive functions\n" +
                "Improves overall health, strengthens the body, and relaxes the mind",
        "Start in Tadasana (Mountain Pose): Stand at the front of your mat with your feet together or hip-width apart. Bring your palms together in front of your chest, in a prayer position.\n" +
                "Inhale, raise your arms: As you inhale, lift your arms up overhead, keeping your palms together. Gently arch your back and look up towards your hands.\n" +
                "Exhale, forward fold: As you exhale, hinge forward from your hips, keeping your spine long. Bring your hands down to the mat beside your feet. If your hands don't reach the mat, you can bend your knees slightly or place your hands on your shins.\n" +
                "Inhale, halfway lift: Lengthen your spine forward, bringing your fingertips to your shins or the mat. Lift your chest and look forward, keeping your back flat.\n" +
                "Exhale, high plank pose: Step or jump back into a high plank position, aligning your wrists directly under your shoulders. Keep your body in a straight line, engaging your core muscles.\n" +
                "Inhale, low plank pose: Lower your body down, bending your elbows and keeping them close to your sides. Your thighs, hips, and chest should hover above the mat, with your elbows at a 90-degree angle. This pose is also known as Chaturanga Dandasana.\n" +
                "Exhale, upward-facing dog: Inhale and push through your palms as you straighten your arms. Lift your chest and roll your shoulders back, opening up your heart. Keep your legs and pelvis lifted off the mat, with the tops of your feet pressing into the ground.\n" +
                "Exhale, downward-facing dog: Push back with your hands and lift your hips up and back, coming into an inverted V shape. Lengthen your spine, press your heels toward the mat, and relax your head and neck. This is Downward Facing Dog pose, or Adho Mukha Svanasana.\n" +
                "Inhale, step or jump forward: Step or jump your feet to the top of the mat, between your hands. Lift your chest into a halfway lift, lengthening your spine.\n" +
                "Exhale, forward fold: Fold forward, bringing your chest toward your thighs and your head toward your knees. Release any tension in your neck and shoulders.\n" +
                "Inhale, rise up to standing: Root down through your feet, engage your core, and slowly come up to standing. Lift your arms overhead, palms together, and gently arch your back, looking up.\n" +
                "Exhale, return to Tadasana: Bring your hands back to your heart center, in a prayer position. Stand tall, grounding through your feet.\n" +
                "Repeat these steps for the desired number of rounds, coordinating each movement with your breath. Typically, a complete round of Surya Namaskar involves performing these steps on the right side and then on the left side.\n" +
                "Surya Namaskar is a dynamic sequence, and it's important to listen to your body and modify the poses as needed. If you are new to Surya Namaskar or have any health concerns, it's recommended to practice under the guidance of a qualified yoga instructor.",
        "Don't eat or drink immediately before and after doing Surya Namaskar.\n" +
                "Before practicing Surya Namaskar, an adequate warm-up should be done.\n" +
                "It should be properly synchronized with breathing.\n" +
                "If you have any medical problem, please consult your doctor.",
        "https://pranayoga.co.in/asana/wp-content/uploads/surya-namaskar-hatha-yoga-sun-salutation-medium.jpg",
        "https://www.youtube.com/watch?v=AbPufvvYiSw"
    )

    val asana25 = Asana(
        25,
        "Shirshasana",
        "Headstand is also known as the 'king of asanas' because this yoga pose is considered to be a master in curing many diseases. Headstand is one of the most effective asanas for body and mind. Mastering Shirshasana requires a bit of strength, but it is mainly a matter of concentration and awareness.",
        "Stimulating the functioning of the pineal, hypothalamus, and pituitary glands. This helps in better functioning and co-ordination of all the endocrine glands.\n" +
                "Improving the body’s ability to maintain homeostasis by stimulation of the nervous system\n" +
                "Providing conditioning to the brain, eyes, and ears due to safely increased blood pressure\n" +
                "Improving memory and concentration\n" +
                "Helping to manage mental fatigue, depression, and anxiety\n" +
                "Improving the functioning of the central nervous system\n" +
                "Improving the body’s capability to regulate blood pressure by stimulation of the so-called baroreceptors\n" +
                "Giving rest to the heart by reversing blood pressure temporarily\n" +
                " Improving body posture by activating the core\n" +
                "Strengthening of muscles of the back, shoulders, and arms\n" +
                "Improving blood and lymph circulation throughout the entire body\n" +
                "Improving digestion and elimination functions.",
        "Sit on the knees and hold the elbows to measure the ideal distance.\n" +
                "Then bring the arms to the ground right under the shoulders.\n" +
                "Keeping the elbows there, bring the hands closer and interlock the fingers so that your arms form a triangle. Do not let your elbows open out.\n" +
                "Place the head on the ground with the back of the head in the cupped hands.\n" +
                "Curl your toes, straighten your knees, hips to the sky.\n" +
                "Start walking towards your shoulders.\n" +
                "Bring the right knee in your chest and then bring another knee towards the chest. This will make your spine straight.\n" +
                "As you inhale, raise your legs to the sky.  Straighten your legs upward while keeping your feet slightly in front of you.\n" +
                "Bring your focus on a steady point preferably at eye level.\n" +
                "Take easy relaxed breaths and hold the posture as long as comfortable.\n",
        "It is important to be very cautious when practising Headstand especially if you have any of the following health challenges:\n" +
                "Hypertension\n" +
                "Cardiovascular issues\n" +
                "Neck issues\n" +
                "Shoulder issues\n" +
                "Recent surgery or inflammation in your head region (for example ears, eyes, nose, etc.)\n" +
                "Arthritis or osteoporosis\n" +
                "Brain injuries\n" +
                "Lower-back and spinal issues (e.g. chronic pain, herniated disc, sciatica, SI-joint instability)\n" +
                "Acute migraine or headache\n" +
                "For asthma or other breathing disorders: holding only for short durations and skipping altogether if it causes too much discomfort, nausea, or shortness of breath",
        "https://hindi.cdn.zeenews.com/hindi/sites/default/files/2021/06/15/847777-shirshasana.jpg",
        "https://www.youtube.com/watch?v=4LvVtTZBaxY"
    )

    val asanas by lazy {
        mutableListOf(
            asana1,
            asana2,
            asana3,
            asana4,
            asana5,
            asana6,
            asana7,
            asana8,
            asana9,
            asana10,
            asana11,
            asana12,
            asana13,
            asana14,
            asana15,
            asana16,
            asana17,
            asana18,
            asana19,
            asana20,
            asana21,
            asana22,
            asana23,
            asana24,
            asana25,
        )
    }
}

