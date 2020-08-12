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

                Call<User> call=service.getUserByEmailAndPassword(email,password);
                call.enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> request, Response<User> response) {
                        if(response.code()==200){
                            Intent intent = new Intent(LoginActivity.this,UserHomeActivity.class);
                            Bundle bundle= new Bundle();
                            bundle.putLong("userId",response.body().getUserId());
                            bundle.putString("nom",response.body().getNom());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else{
                            ((TextView) findViewById(R.id.errorLogin)).setText("error");
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
