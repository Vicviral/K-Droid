package com.els.kdroid.botDoctor

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.els.kdroid.R
import com.els.kdroid.botDoctor.Constant.OPEN_GOOGLE
import com.els.kdroid.botDoctor.Constant.OPEN_HELP
import com.els.kdroid.botDoctor.Constant.OPEN_SEARCH
import com.els.kdroid.botDoctor.Constant.RECEIVE_ID
import com.els.kdroid.botDoctor.Constant.SEND_ID
import kotlinx.android.synthetic.main.activity_bot_doctor.*
import kotlinx.coroutines.*
import java.util.*

class BotDoctor : AppCompatActivity() {

    private lateinit var adapter: MessagingAdapter
//    private val botList = listOf("Doctor Victor", "Doctor Abu", "Doctor Aliyu")

    companion object{
        private const val SPEECH_INPUT_REQUEST_CODE = 0

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bot_doctor)

        recyclerView()
        clickEvents()


//        val random = (0..3).random()
//        customMessage("Hello, today you are speaking with ${botList[random]}, how can I help you?")
        customMessage("Hello, I'm K Droid, how may I help you today?")

    }

    private fun clickEvents() {
        btn_send.setOnClickListener { sendMessage() }
        voice_message.setOnClickListener {
            Toast.makeText(applicationContext, "Long press mic to record", Toast.LENGTH_SHORT).show()
        }
        voice_message.setOnLongClickListener {
            speak()
            true
        }
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun speak() {
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to K-Droid")

        try {
            startActivityForResult(mIntent, SPEECH_INPUT_REQUEST_CODE)
        }catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            SPEECH_INPUT_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val timeStamp = Time.timeStamp()
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    adapter.insertMessage((Message(result!![0], SEND_ID, timeStamp)))

                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                    botResponse(result[0])
                }
            }
        }
    }

    private  fun recyclerView() {
        adapter  = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessage() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            et_message.setText("")

            adapter.insertMessage((Message(message, SEND_ID, timeStamp)))

            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }else {
            Toast.makeText(applicationContext, "Field empty", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponse(message)
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount - 1)

                when(response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }

                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfter("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                    OPEN_HELP -> {
                        startActivity(Intent(this@BotDoctor, BotAssistant::class.java))
                    }

                }
            }
        }

    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.exit_in, R.anim.exit_out)
    }
}