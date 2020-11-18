package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.citoyenaction.home.api.model.ActNonCivique;
import com.citoyenaction.home.api.model.User;
import com.citoyenaction.home.api.service.GetDataService;
import com.citoyenaction.home.api.network.RetrofitClientInstance;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    private long userId;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Date date;
    private Button buttonUpdateUser;
    private User user;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        final EditText editTextNom=(EditText)findViewById(R.id.editTextNom);
        final EditText editTextPrenom=(EditText)findViewById(R.id.editTextPrenom);
        final EditText editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        final EditText editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        final Button buttonUpdateUser=(Button)findViewById(R.id.buttonUpdateUser);
        final TextView textDate=(TextView)findViewById(R.id.textViewDate);
        final Intent intent= getIntent();
        final Bundle bundle= getIntent().getExtras();
        userId= bundle.getLong("userId");
        final GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        Call<User> call = service.getUser(userId);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> request, Response<User> response) {
                if (response.code() == 200) {
                    user=response.body();
                    editTextNom.setText(user.getNom());
                    editTextPrenom.setText(user.getPrenom());
                    editTextEmail.setText(user.getEmail());
                    editTextPassword.setText(user.getPassword());
                    date =user.getDate();
                    textDate.setText(formatter.format(date));
                }
            }

            @Override
            public void onFailure(Call<User> request, Throwable t) {
                // ((TextView) findViewById(R.id.textViewEmail)).setText(t.toString());
            }


        });

        buttonUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom= editTextNom.getText().toString();
                prenom=editTextPrenom.getText().toString();
                email=editTextEmail.getText().toString();
                password=editTextPassword.getText().toString();
                user.setNom(nom);
                user.setPrenom(prenom);
                user.setEmail(email);
                user.setPassword(password);
                Call<User> call=service.updateUser(user);
                call.enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> request, Response<User> response) {
                        if(response.code()==200){
                            Intent intent = new Intent(ProfilActivity.this,UserHomeActivity.class);
                            Bundle bundle= new Bundle();
                            bundle.putLong("userId",userId);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> request, Throwable t) {
                        //((TextView) findViewById(R.id.textViewTitre)).setText(t.toString());
                    }
                });

            }
        });


    }
}
