package com.serviexpress.apirest.payload.response;

import java.io.Serializable;

public class ReporteServicio implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6046265631296318320L;
    String idreporteservicio;
    String totalservicios;
    String mes;

    public ReporteServicio() {
    }

    public String getIdreporteservicio() {
        return idreporteservicio;
    }

    public void setIdreporteservicio(String idreporteservicio) {
        this.idreporteservicio = idreporteservicio;
    }

    public String getTotalservicios() {
        return totalservicios;
    }

    public void setTotalservicios(String totalservicios) {
        this.totalservicios = totalservicios;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "ReporteServicio [idreporteservicio=" + idreporteservicio + ", mes=" + mes
                + ", totalservicios=" + totalservicios + "]";
    }

    
    

    

    
    
}