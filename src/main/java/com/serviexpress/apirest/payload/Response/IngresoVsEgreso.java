package com.serviexpress.apirest.payload.response;

import java.io.Serializable;

public class IngresoVsEgreso implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6046265631296318320L;
    String fecha;
    String total;

    public IngresoVsEgreso() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "IngresoVsEgreso [fecha=" + fecha + ", total=" + total + "]";
    }

    
    
}