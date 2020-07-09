package com.serviexpress.apirest.payload.response;

import java.io.Serializable;

public class ReporteServicio implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6046265631296318320L;
    String idreporteservicio;
    String totalservicios;
    String nombreservicio;
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

	public String getNombreservicio() {
		return nombreservicio;
	}

	public void setNombreservicio(String nombreservicio) {
		this.nombreservicio = nombreservicio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	@Override
	public String toString() {
		return "ReporteServicio [idreporteservicio=" + idreporteservicio + ", totalservicios=" + totalservicios
				+ ", nombreservicio=" + nombreservicio + ", mes=" + mes + "]";
	}

   

    
    

    

    
    
}