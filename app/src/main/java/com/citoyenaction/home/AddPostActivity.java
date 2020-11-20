package com.citoyenaction.home;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.citoyenaction.home.api.model.ActNonCivique;
import com.citoyenaction.home.api.model.User;
import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;


import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddPostActivity extends AppCompatActivity {
    private long userId;
    private String titre,description;
    private Byte photo,video;
    private EditText titreInput,descriptionInput;
    private Button addActButton;
    private Date date = new Date();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        Intent intent= getIntent();
        Bundle bundle= getIntent().getExtras();
        userId= bundle.getLong("userId");
        final GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        titreInput=(EditText)findViewById(R.id.textTitre);
        descriptionInput=(EditText)findViewById(R.id.textDescription);
        final User user= new User(userId);

        addActButton = (Button)findViewById(R.id.addActButton);
            addActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titre= titreInput.getText().toString();
                description=descriptionInput.getText().toString();
                ActNonCivique actNonCivique = new ActNonCivique(titre,description,date,0,0,user);

                Call<ActNonCivique> call=service.addActNonCivique(actNonCivique);
                call.enqueue(new Callback<ActNonCivique>() {

                    @Override
                    public void onResponse(Call<ActNonCivique> request, Response<ActNonCivique> response) {
                        if(response.code()==201){
                            Intent intent = new Intent(AddPostActivity.this,AddFileActivity.class);
                            Bundle bundle= new Bundle();
                            bundle.putLong("userId",userId);
                            bundle.putLong("actId",response.body().getActNonCiviqueId());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else{
                            ((TextView) findViewById(R.id.textViewTitre)).setText("error");
                        }
                    }

                    @Override
                    public void onFailure(Call<ActNonCivique> request, Throwable t) {
                        ((TextView) findViewById(R.id.textViewTitre)).setText(t.toString());
                    }
                });

            }
        });







    }


}
