package com.els.kdroid.botDoctor

import com.els.kdroid.botDoctor.Constant.OPEN_GOOGLE
import com.els.kdroid.botDoctor.Constant.OPEN_HELP
import com.els.kdroid.botDoctor.Constant.OPEN_SEARCH
import java.util.*

object BotResponse {

    fun basicResponse(_message: String): String {
        val random = (0..2).random()
        val message = _message.toLowerCase(Locale.ROOT)


        return when {

////////////////////////////////////////////////////////////////GREETINGS/////////////////////////////////////////////////////////////////////////

            message.contains("hello") || message.contains("hi") || message.contains("hey") -> {
                when(random) {
                    0 -> "Hello there!"
                    1 -> "Hi there!"
                    else -> "Hope you're fine, do you need me to do anything?"
                }
            }

            message.contains("yes") -> {
                when(random) {
                    0 -> "Am always willing to help you live healthy"
                    1 -> "How may I be of help?"
                    else -> "Try saying OPEN HELP"
                }
            }

            message.contains("no") -> {
                when(random) {
                    0 -> "Have a nice day!"
                    1 -> "Have you answered the questioner today?"
                    else -> "Have you check your step tracker today?"
                }
            }

            message.contains("how are you") -> {
                when(random) {
                    0-> "Great, knowing that you are healthy!"
                    1 -> "Pretty good, how are you today?"
                    else -> "I'm doing fine, thanks for asking!"
                }
            }


////////////////////////////////////////////////////////////////QUESTIONS ABOUT K DROID/////////////////////////////////////////////////////////////////////////
            message.contains("what ") && message.contains("name") || message.contains("what's") && message.contains("name")-> {
                when(random) {
                    0-> "I am K Droid, nice to meet you"
                    1-> "Everyone calls me K Droid"
                    else-> "I go by the name K Droid"
                }
            }



            //Get current time
            message.contains("time") && message.contains("what") -> {
                Time.timeStamp()
            }

            //Open google
            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }

            //Search anything on google
            message.contains("search") -> {
                OPEN_SEARCH
            }

            message.contains("open") && message.contains("help") -> {
                OPEN_HELP
            }

//            //Flip coin
//            message.contains("flip") && message.contains("coin") -> {
//                var r = (0..1).random()
//                val result = if (r == 0) "head" else "tail"
//
//                "I flipped a coin and it landed on $result"
//            }

//            //Math calculation
//            message.contains("solve") -> {
//                val equation: String? = message.substringAfter("solve")
//
//                return try {
//                    val answer = SolveMath.solveMath(equation ?: "0")
//                    answer.toString()
//                }catch (e: Exception) {
//                    "Sorry, I can't do that for now"
//                }
//            }

            else -> {
                when(random) {
                    0 -> "Sorry, come again"
                    1 -> "You can help me understand what you want better"
                    else -> "Sorry, didn't get that! \n\n You can ask me anything you wish to know about the covid-19 pandemic"
                }
            }
        }
    }
}