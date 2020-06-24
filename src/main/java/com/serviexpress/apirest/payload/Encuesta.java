package com.serviexpress.apirest.payload;

import java.io.Serializable;
import java.util.Date;

public class Encuesta implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String tipo;
    private Date fecha;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Encuesta() {
    }

    @Override
    public String toString() {
        return "Encuesta [fecha=" + fecha + ", nombre=" + nombre + ", tipo=" + tipo + "]";
    }
    
}