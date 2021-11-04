package com.JeffTorres.primeraappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Bitmap;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.JeffTorres.primeraappv1.Entidades.Entrenador;
import com.JeffTorres.primeraappv1.Entidades.Pokemon;
import com.JeffTorres.primeraappv1.servicios.EntrenadorPokemon;
import com.JeffTorres.primeraappv1.servicios.ServicioPokemon;
import com.google.gson.Gson;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FotosActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView Fotoparamostrar;
    private TextView base64;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);



        Button guardar = findViewById(R.id.guardarEntrenador);
        guardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v2)
            {
                EditText nombre = findViewById(R.id.nomEntre);
                EditText pueblo = findViewById(R.id.puebloEntre);
                TextView codimg = findViewById(R.id.baseimg);

                String vnombre = String.valueOf(nombre.getText());
                String vpueblo = String.valueOf(pueblo.getText());
                String vimagen = String.valueOf(codimg.getText());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://upn.lumenes.tk/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                EntrenadorPokemon servicio = retrofit.create(EntrenadorPokemon.class);
                Entrenador agregarEntrenador = new Entrenador();
                agregarEntrenador.setNombres(vnombre);
                agregarEntrenador.setPueblo(vpueblo);
                agregarEntrenador.setImagen(vimagen);


                servicio.create(agregarEntrenador).enqueue(new Callback<Entrenador>() {
                    @Override
                    public void onResponse(Call<Entrenador> call, Response<Entrenador> response) {
                        Log.i("MAIN_APP", new Gson().toJson(response.body()));
                    }

                    @Override
                    public void onFailure(Call<Entrenador> call, Throwable t) {
                        Log.i("MAIN_APP", "No hay conexion");
                    }
                });

            }
        });


        base64 = findViewById(R.id.baseimg);

        Fotoparamostrar = findViewById(R.id.FotoCapturada);
        Button Camara = findViewById(R.id.tomarfotocamara);
        Camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FotosActivity.this.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    FotosActivity.this.requestPermissions(new String[]{Manifest.permission.CAMERA},10001);
                }
                else
                {
                    dispatchTakePictureIntent();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Fotoparamostrar.setImageBitmap(imageBitmap);
            String base64String = ImageUtil.convert(imageBitmap);
            base64.setText(base64String);
        }
    }


    //  static final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void siguiente()
    {
    }
}