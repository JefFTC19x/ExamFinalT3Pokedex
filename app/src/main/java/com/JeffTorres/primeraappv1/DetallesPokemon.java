package com.JeffTorres.primeraappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetallesPokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pokemon);

        Intent intent = getIntent();
        String nombre = getIntent().getStringExtra("nombre");
        String tipo = getIntent().getStringExtra("tipo");
        String link = getIntent().getStringExtra("link");

        TextView esnom = findViewById(R.id.esnombre);
        TextView estip = findViewById(R.id.estipo);
        ImageView grande = findViewById(R.id.foto);

        Picasso.get().load(link).into(grande);

        esnom.setText(nombre);
        estip.setText(tipo);



    }
}