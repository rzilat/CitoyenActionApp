package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;

public class UserHomeActivity extends AppCompatActivity {
    private long userId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Intent intent= getIntent();
        Bundle bundle= getIntent().getExtras();
        userId= bundle.getLong("userId");
        final String nom= bundle.getString("nom");

        ((TextView) findViewById(R.id.userName)).setText(" "+ userId + " "+ nom);


    }
    public void ajoutAct(View view) {
        Intent intent = new Intent(UserHomeActivity.this, AddPostActivity.class);
        ImageButton addPost=findViewById(R.id.addPost);
        Bundle bundle= new Bundle();
        bundle.putLong("userId",userId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void mesActs(View view) {
        Intent intent = new Intent(UserHomeActivity.this, MyActsActivity.class);
        ImageButton mesPost=findViewById(R.id.mesPost);
        Bundle bundle= new Bundle();
        bundle.putLong("userId",userId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void Acts(View view) {
        Intent intent = new Intent(UserHomeActivity.this, ActsActivity.class);
        ImageButton listPost=findViewById(R.id.listPost);
        Bundle bundle= new Bundle();
        bundle.putLong("userId",userId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

