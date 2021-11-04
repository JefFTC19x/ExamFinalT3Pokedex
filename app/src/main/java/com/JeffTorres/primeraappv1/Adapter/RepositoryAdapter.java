package com.JeffTorres.primeraappv1.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.JeffTorres.primeraappv1.DetallesPokemon;
import com.JeffTorres.primeraappv1.Entidades.Pokemon;
import com.JeffTorres.primeraappv1.MapsActivity;
import com.JeffTorres.primeraappv1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.NameViewHolder>
{

    private List<Pokemon> data;

    public RepositoryAdapter(List<Pokemon> data) {
        this.data = data;
    }

    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_name, parent, false);

        return new NameViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RepositoryAdapter.NameViewHolder holder, int position) {


        TextView NombrePoke = holder.itemView.findViewById(R.id.lnombre);
        TextView TipoPoke = holder.itemView.findViewById(R.id.lcon);
        ImageView img = holder.itemView.findViewById(R.id.careta);

        Pokemon repo = data.get(position);

        NombrePoke.setText(repo.getNombrePokemon());
        TipoPoke.setText(repo.getTipoPokemon());

        Picasso.get().load(repo.getLinkPokemon()).into(img);

        String vnombre =String.valueOf(NombrePoke.getText());
        String vtipo =String.valueOf(TipoPoke.getText());
        String vlink =String.valueOf(repo.getLinkPokemon());
        String val1 = String.valueOf(repo.getLatitud());
        String val2 = String.valueOf(repo.getLongitud());

        Button detalle = holder.itemView.findViewById(R.id.detallePoke);
        detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(holder.itemView.getContext(), DetallesPokemon.class);
                intent.putExtra("nombre",vnombre);
                intent.putExtra("tipo",vtipo);
                intent.putExtra("link",vlink);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        Button ubicación = holder.itemView.findViewById(R.id.ubicacionPoke);
        ubicación.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2)
            {
                Intent intent2 = new Intent(holder.itemView.getContext(), MapsActivity.class);
                intent2.putExtra("latitud",val1);
                intent2.putExtra("longitud",val2);
                holder.itemView.getContext().startActivity(intent2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(View itemView) {
            super(itemView);
        }
    }
}
