package com.example.blindspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mTTS.speak("You are now on the home page. The settings option is on the top right of the screen and the object detection is in the middle.", TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
    }

    // go to home page
    public void home(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    // go to settings page
    public void settings(View view) {
        mTTS.speak("You clicked on settings.", TextToSpeech.QUEUE_FLUSH, null, null);
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    // go to object detection page
    public void home_to_obj(View view){
        mTTS.speak("You are now going to object detection", TextToSpeech.QUEUE_FLUSH, null, null);
        Intent intent = new Intent(this, ObjectDetection.class);
        startActivity(intent);
    }

}