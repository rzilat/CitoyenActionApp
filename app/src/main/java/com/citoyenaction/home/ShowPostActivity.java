package com.citoyenaction.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.citoyenaction.home.api.model.ActNonCivique;

import com.citoyenaction.home.api.model.ActUpload;
import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ShowPostActivity extends AppCompatActivity {
    private long actNonCiviqueId;
    private long userId;
    private String titre,description;
    private Byte photo,video;
    private Button updateActButton,addReactionButton,listReactionButton;
    private Date date;
    private ActNonCivique actNonCivique;
    private ActUpload actUpload;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);
        final EditText textTitre=(EditText)findViewById(R.id.textTitre);
        final EditText textDescription=(EditText)findViewById(R.id.textDescription);
        final ImageView contentImage=(ImageView)findViewById(R.id.imageViewBig);
        final Button updateActButton=(Button)findViewById(R.id.updateActButton);
        final Button addReactionButton=(Button)findViewById(R.id.addReactionButton);
        final Button listReactionButton=(Button)findViewById(R.id.listReactionButton);
        final TextView textDate=(TextView)findViewById(R.id.textDate);
        final Intent intent= getIntent();
        final Bundle bundle= getIntent().getExtras();
        userId= bundle.getLong("userId");
        actNonCiviqueId=bundle.getLong("actNonCiviqueId");
        final GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        Call<ActNonCivique> call = service.getActNonCivique(actNonCiviqueId);
        call.enqueue(new Callback<ActNonCivique>() {

        @Override
        public void onResponse(Call<ActNonCivique> request, Response<ActNonCivique> response) {
            if (response.code() == 200) {
                actNonCivique=response.body();
                textTitre.setText(actNonCivique.getTitre());
                textDescription.setText(actNonCivique.getDescription());
                //contentImage.setImageBitmap(actNonCivique.getPhoto());
                date =actNonCivique.getDate();
                textDate.setText(formatter.format(date));
                if(bundle.getLong("userId")==response.body().getUser().getUserId()){
                    updateActButton.setVisibility(View.VISIBLE);

                }else{
                   addReactionButton.setVisibility(View.VISIBLE);
                   textTitre.getFreezesText();
                   textDescription.getFreezesText();
                }


            }

        }

        @Override
        public void onFailure(Call<ActNonCivique> request, Throwable t) {
            // ((TextView) findViewById(R.id.textViewEmail)).setText(t.toString());
        }

    });

        final GetDataService service2 = RetrofitClientInstance.buildService(GetDataService.class);
        Call<ActUpload> call2 = service2.getActUploadByActNonCiviqueId(actNonCiviqueId);
        call2.enqueue(new Callback<ActUpload>() {

            @Override
            public void onResponse(Call<ActUpload> request, Response<ActUpload> response) {
                if (response.code() == 200) {
                    actUpload=response.body();
                    contentImage.setImageBitmap(StringToBitMap(actUpload.getFileData()));
                    Toast.makeText(ShowPostActivity.this,"yes",Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ActUpload> request, Throwable t) {
                // ((TextView) findViewById(R.id.textViewEmail)).setText(t.toString());
            }

        });


        updateActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titre= textTitre.getText().toString();
                description=textDescription.getText().toString();
                actNonCivique.setTitre(titre);
                actNonCivique.setDescription(description);
                Call<ActNonCivique> call=service.updateActNonCivique(actNonCivique);
                call.enqueue(new Callback<ActNonCivique>() {

                    @Override
                    public void onResponse(Call<ActNonCivique> request, Response<ActNonCivique> response) {
                        if(response.code()==200){
                            Intent intent = new Intent(ShowPostActivity.this,UserHomeActivity.class);
                            Bundle bundle= new Bundle();
                            bundle.putLong("userId",userId);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ActNonCivique> request, Throwable t) {
                        //((TextView) findViewById(R.id.textViewTitre)).setText(t.toString());
                    }
                });

            }
        });




    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    public void addReaction(View view) {
        Intent intent = new Intent(ShowPostActivity.this, AddReactionActivity.class);
        Button addReactionButton=findViewById(R.id.addReactionButton);
        Bundle bundle= new Bundle();
        bundle.putLong("userId",userId);
        bundle.putLong("actNonCiviqueId",actNonCiviqueId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void findReactions(View view) {
        Intent intent = new Intent(ShowPostActivity.this, MyReactionsActivity.class);
        Button listReactionButton=findViewById(R.id.listReactionButton);
        Bundle bundle= new Bundle();
        bundle.putLong("userId",userId);
        bundle.putLong("actNonCiviqueId",actNonCiviqueId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
