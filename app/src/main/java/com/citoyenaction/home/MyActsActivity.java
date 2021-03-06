package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.citoyenaction.home.api.adapter.PostsAdapter;
import com.citoyenaction.home.api.model.ActNonCivique;
;
import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;

import java.util.ArrayList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyActsActivity extends AppCompatActivity {
    private ListView myActList;
    private long userId;
    private ArrayAdapter<ActNonCivique> actListAdapter ;
    private long actNonCiviqueId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_acts);
        Intent intent= getIntent();
        Bundle bundle= getIntent().getExtras();
        userId= bundle.getLong("userId");
        populatePostsList();



    }


    public void populatePostsList() {
        final ArrayList<ActNonCivique> arrayOfPosts = new ArrayList<ActNonCivique>();
        final PostsAdapter postsAdapter = new PostsAdapter(MyActsActivity.this, arrayOfPosts);

        // Attach the adapter to a ListView
        myActList = (ListView) findViewById(R.id.MesActsList);
        myActList.setAdapter(postsAdapter);
        // postsAdapter.clear();
        // postsAdapter.notifyDataSetChanged();

        GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        Call<List<ActNonCivique>> call = service.getActNonCiviqueByUserId(userId);
        call.enqueue(new Callback<List<ActNonCivique>>() {
            @Override
            public void onResponse(Call<List<ActNonCivique>> call, Response<List<ActNonCivique>> response) {
                if (response.code()==200) {
                    postsAdapter.addAll(response.body());
                    //((TextView) findViewById(R.id.textViewTitre)).setText(" "+myActList.getCount());
                } else {
                    //((TextView) findViewById(R.id.textViewTitre)).setText("error");
                }

            }

            @Override
            public void onFailure(Call<List<ActNonCivique>> call, Throwable t) {
                //((TextView) findViewById(R.id.textViewTitre)).setText(t.toString());
            }



        });
        myActList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                 ActNonCivique selected = (ActNonCivique) myActList.getAdapter().getItem(position);
                actNonCiviqueId= selected.getActNonCiviqueId();
                Intent intent = new Intent(MyActsActivity.this, ShowPostActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("userId", userId);
                bundle.putLong("actNonCiviqueId", actNonCiviqueId);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

}