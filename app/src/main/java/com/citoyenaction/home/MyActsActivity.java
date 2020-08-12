package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.citoyenaction.home.api.model.ActNonCivique;
import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyActsActivity extends AppCompatActivity {
    private ListView myActList;
    private long userId;
    private ArrayAdapter<ActNonCivique> actListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_acts);
        Intent intent= getIntent();
        Bundle bundle= getIntent().getExtras();
        userId= bundle.getLong("userId");
        myActList = (ListView)findViewById(R.id.MesActsList);
        actListAdapter = new ArrayAdapter<>(MyActsActivity.this, android.R.layout.simple_list_item_1, new LinkedList<ActNonCivique>());
        myActList.setAdapter(actListAdapter);
        ((TextView) findViewById(R.id.textViewTitre)).setText("reda");
        getActs(myActList);

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
                    ((TextView) findViewById(R.id.textViewTitre)).setText(" "+myActList.getCount());
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
