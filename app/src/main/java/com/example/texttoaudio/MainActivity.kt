package com.example.texttoaudio

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.texttoaudio.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {

    var textToSpeech: TextToSpeech? = null
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textToSpeech = TextToSpeech(this, this)

        // Adding OnClickListener
        binding.btnText.setOnClickListener {
            speakOut()
        }
    }

    override fun onInit(status: Int) {
        val locSpanish = Locale("es", "ES")
        if (status == TextToSpeech.SUCCESS) {

            val result =  textToSpeech!!.setLanguage(locSpanish)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!!")
            } else {
                binding.btnText.isEnabled = true
            }
        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private fun speakOut() {
        val text =  binding.mlText.text.toString()
        textToSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }
}
