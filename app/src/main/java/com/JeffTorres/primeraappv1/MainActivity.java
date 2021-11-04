package com.JeffTorres.primeraappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import com.JeffTorres.primeraappv1.Adapter.RepositoryAdapter;
import com.JeffTorres.primeraappv1.Entidades.Pokemon;
import com.JeffTorres.primeraappv1.servicios.ServicioPokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
       @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ImageView r = findViewById(R.id.fondo2);
        String link ="https://image.winudf.com/v2/image/Y29tLmtoZC5wb2tlbW9ud2FsbHBhcGVyc19zY3JlZW5fMF8xNTIyMTI5Njg4XzA3NQ/screen-0.jpg?fakeurl=1&type=.jpg";
        Picasso.get().load(link).into(r);

        RecyclerView rv = findViewById(R.id.Lista);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServicioPokemon service =retrofit.create(ServicioPokemon.class);

        service.allRepos().enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response)
            {

                if(response.code() == 200)
                {
                    RepositoryAdapter adapter = new RepositoryAdapter(response.body());
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t)
            {
                Log.i("Main_App","Fallo de conexión" );
            }
        });
    }
}