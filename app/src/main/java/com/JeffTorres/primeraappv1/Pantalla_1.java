package com.JeffTorres.primeraappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.JeffTorres.primeraappv1.Adapter.RepositoryAdapter;
import com.JeffTorres.primeraappv1.Entidades.Entrenador;
import com.JeffTorres.primeraappv1.Entidades.Pokemon;
import com.JeffTorres.primeraappv1.servicios.ServicioPokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pantalla_1 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_1);

        ImageView  r = findViewById(R.id.fondo1);
        String link ="https://i.pinimg.com/originals/04/2d/1d/042d1d76317f0b7bcae48435ddcc1f8c.jpg";
        Picasso.get().load(link).into(r);

        Button nuevomaestro =findViewById(R.id.nuevoMaestro);
        nuevomaestro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent registro = new Intent(getApplicationContext(),FotosActivity.class );
                startActivity(registro);
                nuevomaestro.setEnabled(false);
            }
        } );

        //Agregar pokemon
        Button capturar =findViewById(R.id.Capturar);
        capturar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent nuevoPokemon = new Intent(getApplicationContext(),NuevoPokemon.class );
                startActivity(nuevoPokemon);
            }
        } );
        //Lista Pokemons
        Button misPokemon =findViewById(R.id.MisPokemon);
        misPokemon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Pokemons = new Intent(getApplicationContext(),MainActivity.class );
                startActivity(Pokemons);
            }
        } );

    }




}