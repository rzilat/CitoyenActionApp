package com.citoyenaction.home;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


import android.widget.Toast;

import com.citoyenaction.home.api.model.ActUpload;
import com.citoyenaction.home.api.network.RetrofitClientInstance;
import com.citoyenaction.home.api.service.GetDataService;
import com.ipaulpro.afilechooser.utils.FileUtils;


import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFileActivity extends AppCompatActivity  {

    private Button buttonUpload;


    private static final int PICK_IMAGE_FROM_GALERY_REQUEST = 1;
    private static final int MY_PERMISSIONS_REQUEST = 100;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_file);

        if(ContextCompat.checkSelfPermission(AddFileActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddFileActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST);
        }


        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select picture"),PICK_IMAGE_FROM_GALERY_REQUEST);
            }
        });

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_FROM_GALERY_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();
            Toast.makeText(AddFileActivity.this,uri.getPath(),Toast.LENGTH_SHORT).show();
            uploadFile(uri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else {

                }
                return;
            }
        }
    }

    private void uploadFile(Uri fileUri){
        RequestBody descriptionPart = RequestBody.create(MultipartBody.FORM,"description");
        File originalFile = FileUtils.getFile(AddFileActivity.this,fileUri);
        Toast.makeText(AddFileActivity.this,originalFile.getPath(),Toast.LENGTH_SHORT).show();
        RequestBody filePart=RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), originalFile);
        MultipartBody.Part file =MultipartBody.Part.createFormData("photo",originalFile.getName(),filePart);
        GetDataService service = RetrofitClientInstance.buildService(GetDataService.class);
        Call<ActUpload> call= service.saveActUpload(descriptionPart,file);
        call.enqueue(new Callback<ActUpload>() {
            @Override
            public void onResponse(Call<ActUpload> call, Response<ActUpload> response) {
                if (response.code() == 200) {
                    Toast.makeText(AddFileActivity.this,"yes",Toast.LENGTH_SHORT).show();
                }
                if (response.code() == 404) {
                    Toast.makeText(AddFileActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                }
                if (response.code() == 400) {

                }
            }
            @Override
            public void onFailure(Call<ActUpload> call, Throwable t)
            { Toast.makeText(AddFileActivity.this,"nooo",Toast.LENGTH_SHORT).show();}

        });
    }
}
