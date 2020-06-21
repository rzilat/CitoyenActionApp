package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        Button loginButton=findViewById(R.id.loginButton);
        startActivity(intent);
    }
    public void signIn(View view) {
        Intent intent = new Intent(this, ProfilActivity.class);
        Button signInButton=findViewById(R.id.signInButton);
        startActivity(intent);
    }

}
