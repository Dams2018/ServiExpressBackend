package com.serviexpress.apirest.payload.response;

import java.io.Serializable;

public class ResponseReservaPago implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6046265631296318320L;
    String idreserva;
    String nombre;
    String apellido;
    String rut;
    String marca;
    String modelo;
    String patente;
    String nombreproducto;
    String nombreservicio;
    String estadoreserva;
    String total;

    public ResponseReservaPago() {
    }

    public String getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(String idreserva) {
        this.idreserva = idreserva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getNombreservicio() {
        return nombreservicio;
    }

    public void setNombreservicio(String nombreservicio) {
        this.nombreservicio = nombreservicio;
    }

    public String getEstadoreserva() {
        return estadoreserva;
    }

    public void setEstadoreserva(String estadoreserva) {
        this.estadoreserva = estadoreserva;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ResponseReservaPago [apellido=" + apellido + ", estadoreserva=" + estadoreserva + ", idreserva="
                + idreserva + ", marca=" + marca + ", modelo=" + modelo + ", nombre=" + nombre + ", nombreproducto="
                + nombreproducto + ", nombreservicio=" + nombreservicio + ", patente=" + patente + ", rut=" + rut
                + ", total=" + total + "]";
    }

   
    
    

    
}