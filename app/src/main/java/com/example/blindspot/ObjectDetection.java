package com.example.blindspot;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class ObjectDetection extends AppCompatActivity {
    private TextToSpeech mTTS;
    private int STORAGE_PERMISSIONS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectdetection);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mTTS.speak("You are about to start detecting. Tap the screen if you wish to proceed. The settings button is on the top right and the home button is on the top left.", TextToSpeech.QUEUE_FLUSH, null,null);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_activity(view);
            }
        });
    }

    // go to home page
    public void home(View view){
        if(mTTS != null){
            mTTS.stop();
        }
        Intent intent = new Intent(this, HomeActivity.class);
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

    public void main_activity(View view){
        if(mTTS != null){
            mTTS.stop();
        }

        if(ContextCompat.checkSelfPermission(ObjectDetection.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            mTTS.speak("Camera is required for detection", TextToSpeech.QUEUE_FLUSH, null,null);
            requestCameraPermission();
        }
    }

    private void requestCameraPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){

            new AlertDialog.Builder(ObjectDetection.this).setTitle("Permission needed")
                    .setMessage("Camera is required for detection")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(ObjectDetection.this, new String[]{Manifest.permission.CAMERA}, STORAGE_PERMISSIONS);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    }).create().show();

        } else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, STORAGE_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == STORAGE_PERMISSIONS){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
