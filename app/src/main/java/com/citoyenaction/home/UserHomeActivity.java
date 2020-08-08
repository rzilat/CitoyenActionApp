package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Intent intent= getIntent();
        Bundle bundle= getIntent().getExtras();
        final long id= bundle.getLong("userId");
        final String nom= bundle.getString("nom");

        ((TextView) findViewById(R.id.textViewId)).setText(" "+id+" "+nom);
    }
}
