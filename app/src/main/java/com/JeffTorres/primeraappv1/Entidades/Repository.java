package com.JeffTorres.primeraappv1.Entidades;

public class Repository
{

    private String NombrePoke;
    private String TipoPoke;
    private String link;
    private int latitud  ;
    private int longitud;

    public Repository() {
    }

    public Repository(String name, String body) {
        this.NombrePoke = name;
        this.TipoPoke = body;

    }

    public String getNombrePoke() {
        return NombrePoke;
    }

    public void setNombrePoke(String nombrePoke) {
        this.NombrePoke = nombrePoke;
    }

    public String getTipoPoke() {
        return TipoPoke;
    }

    public void setTipoPoke(String tipoPoke) {
        this.TipoPoke = tipoPoke;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}
