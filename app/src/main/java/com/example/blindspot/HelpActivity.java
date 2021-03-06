package com.example.blindspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class HelpActivity extends AppCompatActivity {

    TextView faqs;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mTTS.speak("The help page has an FAQ and a button to go back to the tutorial.", TextToSpeech.QUEUE_FLUSH, null, null);
                        faqs.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        String questions = "This is a sample question we will use for now:\n" +
                "Sample text " +
                "to see how good they look when there is more text." +
                "I think that this is a reasonable amount.\n\n" +
                "Why are you doing this?:\nWe are taking CS 407 at UW Madison " +
                "in the fall of 2021. This is our final project\n\n" +
                "Why are you doing this?:\nWe are taking CS 407 at UW Madison " +
                "in the fall of 2021. This is our final project\n\n";
        faqs = (TextView) findViewById(R.id.faqs);
        faqs.setText(questions);
        faqs.setMovementMethod(new ScrollingMovementMethod());
        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faq();
            }
        });

    }

    private void faq(){

        if(mTTS != null){
            mTTS.stop();
        }

        String text = faqs.getText().toString();

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    // go to home page
    public void home(View view){

        if(mTTS != null){
            mTTS.stop();
        }

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void google_form(View view){
        if (mTTS != null){
            mTTS.stop();
        }

        mTTS.speak("Ask a question", TextToSpeech.QUEUE_FLUSH, null,null);

        Intent intent = new Intent(this, GoogleForm.class);
        startActivity(intent);
    }

    // go to tutorials page
    public void tutorial(View view) {

        if(mTTS != null){
            mTTS.stop();
        }

        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }

    // go to settings page
    public void settings(View view) {

        if(mTTS != null){
            mTTS.stop();
        }

        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}