package com.serviexpress.apirest.payload;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class RangoFecha implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
    private Date fechaini;
    @Temporal(TemporalType.DATE)
    private Date fechafin;

    public RangoFecha() {
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    @Override
    public String toString() {
        return "RangoFecha [fechafin=" + fechafin + ", fechaini=" + fechaini + "]";
    }

    
}