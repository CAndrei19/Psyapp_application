package com.example.psyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {//ascunde actionbar
            getSupportActionBar().hide();}
        SharedPreferences sharedPreferences=getSharedPreferences("UserProfile",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("existingProfile",false))
        {
            Intent i=new Intent(this, MainMenuActivity.class);
            startActivity(i);
            finish();//scoate activitatea din stack - nu se poate reveni cu back
        }
    }
    public void CreateProfile(View view){
        Intent i= new Intent(this, CreateProfile.class);//first you create intent
        startActivity(i); //launch intent, opening a new activity
        finish();
    }
    //lla buton next, faci salvare in baza de date a nume + prenume sau
}