package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private String email, password;
    private EditText emailInput, passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        emailInput=(EditText)findViewById(R.id.inputEmail);
        passwordInput=(EditText)findViewById(R.id.inputPassword);

        loginButton = (Button)findViewById(R.id.buttonConnect);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email= emailInput.getText().toString();
                password=passwordInput.getText().toString();

                Call<String> call=service.getUserByEmailAndPassword(email,password);
                call.enqueue(new Callback<String>() {

                    @Override
                    public void onResponse(Call<String> request, Response<String> response) {
                        ((TextView) findViewById(R.id.textViewEmail)).setText(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> request, Throwable t) {
                        ((TextView) findViewById(R.id.textViewEmail)).setText(t.toString());
                    }
                });

            }
        });





    }

}
