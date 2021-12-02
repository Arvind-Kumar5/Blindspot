package com.example.blindspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // go to home page
    public void home(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    // go to settings page
    public void settings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    // go to object detection page
    public void home_to_obj(View view){
        Intent intent = new Intent(this, ObjectDetection.class);
        startActivity(intent);
    }

}