
package com.serviexpress.apirest.payload.Response;



public class PagoResponse {


    private double valor;
    private String servicio;
    private Long idReserva;

    public PagoResponse() {
    }


    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    @Override
    public String toString() {
        return "PagoResponse [idReserva=" + idReserva + ", servicio=" + servicio + ", valor=" + valor + "]";
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }




    
}