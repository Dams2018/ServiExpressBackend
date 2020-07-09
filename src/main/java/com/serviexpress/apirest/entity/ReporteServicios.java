package com.serviexpress.apirest.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "reporteservicios", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "idreporteservicio"
    })
})
@Entity
public class ReporteServicios implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idreporteservicio;
    private int totalservicios;
    private String nombreservicio;
    private Date mes;

    public ReporteServicios() {
    }

	public ReporteServicios(Long idreporteservicio, int totalservicios, String nombreservicio, Date mes) {
		this.idreporteservicio = idreporteservicio;
		this.totalservicios = totalservicios;
		this.nombreservicio = nombreservicio;
		this.mes = mes;
	}

	public Long getIdreporteservicio() {
		return idreporteservicio;
	}

	public void setIdreporteservicio(Long idreporteservicio) {
		this.idreporteservicio = idreporteservicio;
	}

	public int getTotalservicios() {
		return totalservicios;
	}

	public void setTotalservicios(int totalservicios) {
		this.totalservicios = totalservicios;
	}

	public String getNombreservicio() {
		return nombreservicio;
	}

	public void setNombreservicio(String nombreservicio) {
		this.nombreservicio = nombreservicio;
	}

	public Date getMes() {
		return mes;
	}

	public void setMes(Date mes) {
		this.mes = mes;
	}

	@Override
	public String toString() {
		return "ReporteServicios [idreporteservicio=" + idreporteservicio + ", totalservicios=" + totalservicios
				+ ", nombreservicio=" + nombreservicio + ", mes=" + mes + "]";
	}
	
	

    

    

    
}

