package com.serviexpress.apirest.payload;

import java.io.Serializable;
import java.util.Date;




public class ReservaResponse implements Serializable{
    

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private Long idreserva;
    private String Veichulo;
    private String Marca;
    private String servicios;
    private String productos;
    private Date fechareserva;
    private String horareserva;
    private int estado;
    private String patente;

    public ReservaResponse() {
    }

    public ReservaResponse(ReservaResponse reserva) {
	}

	public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(Long idreserva) {
        this.idreserva = idreserva;
    }

    public String getVeichulo() {
        return Veichulo;
    }

    public void setVeichulo(String veichulo) {
        Veichulo = veichulo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public Date getFechareserva() {
        return fechareserva;
    }

    public void setFechareserva(Date fechareserva) {
        this.fechareserva = fechareserva;
    }

    public String getHorareserva() {
        return horareserva;
    }

    public void setHorareserva(String horareserva) {
        this.horareserva = horareserva;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }


    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    @Override
    public String toString() {
        return "ReservaResponse [Marca=" + Marca + ", Veichulo=" + Veichulo + ", estado=" + estado + ", fechareserva="
                + fechareserva + ", horareserva=" + horareserva + ", idreserva=" + idreserva + ", patente=" + patente
                + ", productos=" + productos + ", servicios=" + servicios + "]";
    }





   
}