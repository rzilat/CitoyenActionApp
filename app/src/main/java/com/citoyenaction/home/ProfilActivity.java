package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;

import android.widget.TextView;



import com.citoyenaction.home.api.service.GetDataService;
import com.citoyenaction.home.api.network.RetrofitClientInstance;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        Call<String> call=service.getMessage();
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> request, Response<String> response) {
                ((TextView) findViewById(R.id.messages)).setText(response.body());
            }

            @Override
            public void onFailure(Call<String> request, Throwable t) {
                ((TextView) findViewById(R.id.messages)).setText(t.toString());
            }
        });

    }
}
