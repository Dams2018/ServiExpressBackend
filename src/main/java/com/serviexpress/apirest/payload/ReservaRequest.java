package com.serviexpress.apirest.payload;

import java.io.Serializable;
import java.util.Date;




public class ReservaRequest implements Serializable {


    private static final long serialVersionUID = 1L;
    private Long idreserva;
    private Long idcliente;
    private Long idvehiculo;
    private String servicios;
    private String productos;
	private Date fecha;
    private String horareserva;
    private int estado;
    private double totalreserva;
    private Boolean activo;

    public ReservaRequest() {
    }

    public Long getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(Long idreserva) {
        this.idreserva = idreserva;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public Long getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Long idvehiculo) {
        this.idvehiculo = idvehiculo;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }


    public double getTotalreserva() {
        return totalreserva;
    }

    public void setTotalreserva(double totalreserva) {
        this.totalreserva = totalreserva;
    }

    @Override
    public String toString() {
        return "ReservaRequest [activo=" + activo + ", estado=" + estado + ", fecha=" + fecha + ", horareserva="
                + horareserva + ", idcliente=" + idcliente + ", idreserva=" + idreserva + ", idvehiculo=" + idvehiculo
                + ", productos=" + productos + ", servicios=" + servicios + ", totalreserva=" + totalreserva + "]";
    }

}