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

import com.JeffTorres.primeraappv1.Entidades.Pokemon;
import com.JeffTorres.primeraappv1.servicios.ServicioPokemon;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NuevoPokemon extends AppCompatActivity
{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView Fotoparamostrar;
    private TextView base64;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_pokemon);

         base64 = findViewById(R.id.imagenpokemon);

         Fotoparamostrar = findViewById(R.id.fotopokemon);
        Button tomarfotopoke = findViewById(R.id.tomarfoto);
        tomarfotopoke.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(NuevoPokemon.this.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    NuevoPokemon.this.requestPermissions(new String[]{Manifest.permission.CAMERA},10001);
                }
                else
                {
                    dispatchTakePictureIntent();
                }

            }
        });

        Button agregarPokemon = findViewById(R.id.crearpokemon);
        agregarPokemon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText nombrePoke =findViewById(R.id.nombrepokemon);
                EditText tipoPoke =findViewById(R.id.tipopokemon);
                TextView imagenpoke =findViewById(R.id.imagenpokemon);
                EditText latitudPoke =findViewById(R.id.latitudpokemon);
                EditText longitudPoke =findViewById(R.id.longitudpokemon);

                String vnombre =String.valueOf(nombrePoke.getText());
                String vtipo =String.valueOf(tipoPoke.getText());
                String vimagen = String.valueOf(imagenpoke);
                String val1 = String.valueOf(latitudPoke.getText());
                String val2 = String.valueOf(longitudPoke.getText());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://upn.lumenes.tk/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServicioPokemon servicio =retrofit.create(ServicioPokemon.class);
                Pokemon ap = new Pokemon();
                ap.setNombrePokemon(vnombre);
                ap.setTipoPokemon(vtipo);
                ap.setLinkPokemon(vimagen);
                ap.setLatitud(Float.parseFloat(val1));
                ap.setLongitud(Float.parseFloat(val2));


                servicio.create(ap).enqueue(new Callback<Pokemon>()
                {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response)
                    {
                        Log.i("MAIN_APP",new Gson().toJson(response.body()));
                        Log.i("MAIN_APP",new Gson().toJson(response.code()));
                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t)
                    {
                        Log.i("MAIN_APP","No hay conexion");
                    }
                });
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
}