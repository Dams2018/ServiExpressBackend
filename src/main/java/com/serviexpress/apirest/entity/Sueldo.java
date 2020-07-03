package com.serviexpress.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Table(name = "sueldo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "idsueldo"
    })
})
@Entity
public class Sueldo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idsueldo;

    private Long idempleado;

    @NotBlank
    private String sueldo;

    @Temporal(TemporalType.DATE)
	private Date fecha;

    public Sueldo() {
    }

    public Sueldo(Sueldo sueldo) {
		this.idsueldo = sueldo.idsueldo;
		this.idempleado = sueldo.idempleado;
        this.sueldo = sueldo.sueldo;
        this.fecha = sueldo.fecha;
    }

	public Long getIdsueldo() {
		return idsueldo;
	}

	public void setIdsueldo(Long idsueldo) {
		this.idsueldo = idsueldo;
	}

	public Long getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(Long idempleado) {
		this.idempleado = idempleado;
	}

	public String getSueldo() {
		return sueldo;
	}

	public void setSueldo(String sueldo) {
		this.sueldo = sueldo;
	}

	@Override
    public String toString() {
        return "Sueldo [fecha=" + fecha + ", idempleado=" + idempleado + ", idsueldo=" + idsueldo + ", sueldo=" + sueldo
                + "]";
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Sueldo(Long idsueldo, Long idempleado, @NotBlank String sueldo, Date fecha) {
        this.idsueldo = idsueldo;
        this.idempleado = idempleado;
        this.sueldo = sueldo;
        this.fecha = fecha;
    }



 

}