package com.JeffTorres.primeraappv1.Entidades;

import com.google.gson.annotations.SerializedName;

public class Entrenador {
    @SerializedName("nombres")
    private String nombres;
    @SerializedName("pueblo")
    private String pueblo;
    @SerializedName("imagen")
    private String imagen;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPueblo() {
        return pueblo;
    }

    public void setPueblo(String pueblo) {
        this.pueblo = pueblo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Entrenador() {
    }
}
