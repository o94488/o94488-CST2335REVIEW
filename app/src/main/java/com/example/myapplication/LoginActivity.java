package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class LoginActivity extends AppCompatActivity {

    protected final static String ACTIVITY_NAME = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button nextButton = (Button) findViewById(R.id.nextPageButton);

        SharedPreferences sharedPref = getSharedPreferences("LoginFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        EditText loginName = findViewById(R.id.email);
        //loginName.setHint(sharedPref.getString("savedEmail", ""));
        loginName.setText(sharedPref.getString("savedEmail",""));

        nextButton.setOnClickListener(b -> {
            //Give directions to go from this page, to SecondActivity
            Intent nextPage = new Intent(LoginActivity.this, ProfileActivity.class);

            nextPage.putExtra("savedEmail", loginName.getText().toString());
            //Now make the transition:
            startActivity(nextPage);
        });

        Log.e(ACTIVITY_NAME, "In onCreate()");
    }

    protected void onResume(){
        super.onResume();
        Log.e(ACTIVITY_NAME, "In onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences("LoginFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit =  sharedPref.edit();
        EditText loginName = findViewById(R.id.email);
        edit.putString("savedEmail",loginName.getText().toString());
        edit.commit();

        Log.e(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop(){
        super.onStop();
        Log.e(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "In onDestroy()");
    }

}