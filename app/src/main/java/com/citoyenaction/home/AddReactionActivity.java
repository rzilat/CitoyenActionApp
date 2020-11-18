package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.citoyenaction.home.api.model.ActNonCivique;
import com.citoyenaction.home.api.model.Reaction;
import com.citoyenaction.home.api.model.User;
import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReactionActivity extends AppCompatActivity {
    private long userId,actNonCiviqueId;
    private String titre,commentaire,evaluation;
    private Date date = new Date();
    private EditText titreInput,commentaireInput,evaluationInput;
    private Button addRecButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reaction);
        Intent intent= getIntent();
        Bundle bundle= getIntent().getExtras();
        userId= bundle.getLong("userId");
        actNonCiviqueId= bundle.getLong("actNonCiviqueId");
        final GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        titreInput=(EditText)findViewById(R.id.editTextTitre);
        commentaireInput=(EditText)findViewById(R.id.editTextCommentaire);
        evaluationInput=(EditText)findViewById(R.id.editTextEvaluation);
        final User user= new User(userId);
        final ActNonCivique actNonCivique= new ActNonCivique(actNonCiviqueId);

        addRecButton = (Button)findViewById(R.id.addRecButton);
        addRecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titre= titreInput.getText().toString();
                commentaire= commentaireInput.getText().toString();
                evaluation= evaluationInput.getText().toString();
                Reaction reaction= null;
                reaction = new Reaction(titre,commentaire,evaluation,date,user,actNonCivique);

                Call<Reaction> call=service.addReaction(reaction);
                call.enqueue(new Callback<Reaction>() {

                    @Override
                    public void onResponse(Call<Reaction> request, Response<Reaction> response) {
                        if(response.code()==201){
                            Intent intent = new Intent(AddReactionActivity.this,ActsActivity.class);
                            Bundle bundle= new Bundle();
                            bundle.putLong("userId",userId);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else{
                            ((TextView) findViewById(R.id.textReactionTitre)).setText("error");
                        }
                    }

                    @Override
                    public void onFailure(Call<Reaction> request, Throwable t) {
                        ((TextView) findViewById(R.id.textReactionTitre)).setText(t.toString());
                    }
                });

            }
        });


    }
}
