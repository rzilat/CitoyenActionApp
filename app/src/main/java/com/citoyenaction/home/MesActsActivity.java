package com.citoyenaction.home;

import android.content.Intent;

import android.os.Bundle;

import com.citoyenaction.home.api.model.ActNonCivique;

import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.view.View;

import android.widget.ArrayAdapter;


import android.widget.ListView;
import android.widget.TextView;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MesActsActivity extends AppCompatActivity {
    private ListView myActList;
    private long userId;
    private ArrayAdapter<ActNonCivique> actListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_acts);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent= getIntent();
        Bundle bundle= getIntent().getExtras();
        userId= bundle.getLong("userId");
        myActList = findViewById(R.id.MesActsList);
        actListAdapter = new ArrayAdapter<>(MesActsActivity.this, android.R.layout.simple_list_item_1, new LinkedList<ActNonCivique>());
        myActList.setAdapter(actListAdapter);




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ajouter un act", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    public void getActs(View view) {
        actListAdapter.clear();
        actListAdapter.notifyDataSetChanged();

        GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        Call<List<ActNonCivique>> call = service.getActNonCiviqueByUserId(userId);
        call.enqueue(new Callback<List<ActNonCivique>>() {
            @Override
            public void onResponse(Call<List<ActNonCivique>> call, Response<List<ActNonCivique>> response) {
                if (response.code()==200) {
                    actListAdapter.addAll(response.body());
                } else {
                    ((TextView) findViewById(R.id.textViewTitre)).setText("error");
                }

            }

            @Override
            public void onFailure(Call<List<ActNonCivique>> call, Throwable t) {
                ((TextView) findViewById(R.id.textViewTitre)).setText(t.toString());
            }


        });
    }









}
