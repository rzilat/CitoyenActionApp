package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.citoyenaction.home.api.model.User;
import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {


    private String email, password, nom, prenom;
    private Date date = new Date();
    private EditText emailInput, passwordInput, nomInput, prenomInput;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        final GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        nomInput=(EditText)findViewById(R.id.inputNom);
        prenomInput=(EditText)findViewById(R.id.inputPrenom);
        emailInput=(EditText)findViewById(R.id.inputEmail);
        passwordInput=(EditText)findViewById(R.id.inputPassword);

        signInButton = (Button)findViewById(R.id.buttonregister);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom= nomInput.getText().toString();
                prenom= prenomInput.getText().toString();
                email= emailInput.getText().toString();
                password=passwordInput.getText().toString();
                User user= new User(nom,prenom,email,password,date);

                Call<User> call=service.addUser(user);
                call.enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> request, Response<User> response) {
                        if(response.code()==201){
                            ((TextView) findViewById(R.id.textViewEmail)).setText(response.body().getNom());
                            Intent intent = new Intent(SignInActivity.this,UserHomeActivity.class);
                            Bundle bundle= new Bundle();
                            bundle.putLong("userId",response.body().getUserId());
                            bundle.putString("nom",response.body().getNom());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onFailure(Call<User> request, Throwable t) {
                        ((TextView) findViewById(R.id.textViewEmail)).setText(t.toString());
                    }
                });

            }
        });







    }

}
