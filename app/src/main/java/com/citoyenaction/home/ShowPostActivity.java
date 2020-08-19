package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.citoyenaction.home.api.model.ActNonCivique;
import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;

import java.util.List;

public class ShowPostActivity extends AppCompatActivity {
    private long actNonCiviqueId;
    private long userId;
    private String titre,description;
    private Byte photo,video;
    private EditText titreInput,descriptionInput;
    private Button updateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);
        final EditText textTitre=(EditText)findViewById(R.id.textTitre);
        final EditText textDescription=(EditText)findViewById(R.id.textDescription);
        Intent intent= getIntent();
        Bundle bundle= getIntent().getExtras();
        userId= bundle.getLong("userId");
        actNonCiviqueId=bundle.getLong("actNonCiviqueId");
        GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        Call<ActNonCivique> call = service.getActNonCivique(actNonCiviqueId);
        call.enqueue(new Callback<ActNonCivique>() {

        @Override
        public void onResponse(Call<ActNonCivique> request, Response<ActNonCivique> response) {
            if (response.code() == 200) {
                textTitre.setText(response.body().getTitre());
                textDescription.setText(response.body().getDescription());


            } else {
                // ((TextView) findViewById(R.id.errorLogin)).setText("error");
            }
        }

        @Override
        public void onFailure(Call<ActNonCivique> request, Throwable t) {
            // ((TextView) findViewById(R.id.textViewEmail)).setText(t.toString());
        }

    });

    }
}
