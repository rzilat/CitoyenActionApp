package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.citoyenaction.home.api.adapter.ReactionsAdapter;
import com.citoyenaction.home.api.model.Reaction;
import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReactionsActivity extends AppCompatActivity {
    private ListView myReactionList;
    private long userId;
    private ArrayAdapter<Reaction> reactionListAdapter ;
    private long actNonCiviqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reactions);
        Intent intent= getIntent();
        Bundle bundle= getIntent().getExtras();
        userId= bundle.getLong("userId");
        actNonCiviqueId= bundle.getLong("actNonCiviqueId");
        populateReactionsList();
    }

    public void populateReactionsList() {
        final ArrayList<Reaction> arrayOfReactions = new ArrayList<Reaction>();
        final ReactionsAdapter reactionsAdapter = new ReactionsAdapter(MyReactionsActivity.this, arrayOfReactions);

        // Attach the adapter to a ListView
        myReactionList = (ListView) findViewById(R.id.MesReactionsList);
        myReactionList.setAdapter(reactionsAdapter);
        // reactionsAdapter.clear();
        // reactionsAdapter.notifyDataSetChanged();

        GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        Call<List<Reaction>> call = service.getReactionsByActNonCiviqueId(actNonCiviqueId);
        call.enqueue(new Callback<List<Reaction>>() {
            @Override
            public void onResponse(Call<List<Reaction>> call, Response<List<Reaction>> response) {
                if (response.code() == 200) {
                    reactionsAdapter.addAll(response.body());
                    //((TextView) findViewById(R.id.textViewTitre)).setText(" "+myActList.getCount());
                } else {
                    //((TextView) findViewById(R.id.textViewTitre)).setText("error");
                }

            }

            @Override
            public void onFailure(Call<List<Reaction>> call, Throwable t) {
                //((TextView) findViewById(R.id.textViewTitre)).setText(t.toString());
            }


        });
    }
}
