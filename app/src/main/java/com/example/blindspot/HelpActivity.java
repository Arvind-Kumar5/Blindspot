package com.example.blindspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    TextView faqs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        String questions = "What is currently here?:\n" +
                "I am going to type these questions" +
                "to see how good they look when there is more text." +
                "I think that this is a reasonable amount.\n\n" +
                "Why are you doing this?:\nWe are taking CS 407 at UW Madison " +
                "in the fall of 2021. This is our final project\n\n" +
                "Why are you doing this?:\nWe are taking CS 407 at UW Madison " +
                "in the fall of 2021. This is our final project\n\n";
        faqs = (TextView) findViewById(R.id.faqs);
        faqs.setText(questions);
    }

    // go to home page
    public void home(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    // go to tutorials page
    public void tutorial(View view) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }

    // go to settings page
    public void settings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}