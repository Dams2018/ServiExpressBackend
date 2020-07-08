package com.serviexpress.apirest.payload.response;

public class VehiculoDTO {

    private Long idvehiculo;
    private Long idcliente;
    private String patente;
    private String marca;
    private String modelo;
    private String tipovehiculo;
    private String anio;
    private String nrochasis;
    private boolean active;

    public VehiculoDTO() {
    }

    public Long getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Long idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
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

    public String getTipovehiculo() {
        return tipovehiculo;
    }

    public void setTipovehiculo(String tipovehiculo) {
        this.tipovehiculo = tipovehiculo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getNrochasis() {
        return nrochasis;
    }

    public void setNrochasis(String nrochasis) {
        this.nrochasis = nrochasis;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "VehiculoDTO [active=" + active + ", anio=" + anio + ", idcliente=" + idcliente + ", idvehiculo="
                + idvehiculo + ", marca=" + marca + ", modelo=" + modelo + ", nrochasis=" + nrochasis + ", patente="
                + patente + ", tipovehiculo=" + tipovehiculo + "]";
    }
    
}