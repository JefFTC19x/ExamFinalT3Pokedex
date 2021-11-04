package com.JeffTorres.primeraappv1.Entidades;

import com.google.gson.annotations.SerializedName;

public class Pokemon {
    @SerializedName("nombre")
    private String nombrePokemon;
    @SerializedName("tipo")
    private String tipoPokemon;
    @SerializedName("url_imagen")
    private String linkPokemon;
    @SerializedName("latitude")
    private float latitud  ;
    @SerializedName("longitude")
    private float longitud;

    public Pokemon() {
    }

    public Pokemon(String nombrePokemon, String tipoPokemon, String linkPokemon, float latitud, float longitud) {
        this.nombrePokemon = nombrePokemon;
        this.tipoPokemon = tipoPokemon;
        this.linkPokemon = linkPokemon;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombrePokemon() {
        return nombrePokemon;
    }

    public void setNombrePokemon(String nombrePokemon) {
        this.nombrePokemon = nombrePokemon;
    }

    public String getTipoPokemon() {
        return tipoPokemon;
    }

    public void setTipoPokemon(String tipoPokemon) {
        this.tipoPokemon = tipoPokemon;
    }

    public String getLinkPokemon() {
        return linkPokemon;
    }

    public void setLinkPokemon(String linkPokemon) {
        this.linkPokemon = linkPokemon;
    }

    public Number getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public Number getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
