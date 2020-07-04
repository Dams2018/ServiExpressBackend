package com.serviexpress.apirest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;



@Table(name = "reportein", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "idreporte"
    })
})
@Entity
public class ReporteIn implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idreporte;
    private Long idreserva;
    private double valortotal;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    public ReporteIn() {
    }


    public ReporteIn(Long idreporte, Long idreserva, double valortotal, Date fecha) {
        this.idreporte = idreporte;
        this.idreserva = idreserva;
        this.valortotal = valortotal;
        this.fecha = fecha;
    }



    public Long getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(Long idreserva) {
        this.idreserva = idreserva;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ReporteIn [fecha=" + fecha + ", idreserva=" + idreserva + ", idservicio=" + idreporte + ", valortotal="
                + valortotal + "]";
    }

    public Long getIdreporte() {
        return idreporte;
    }

    public void setIdreporte(Long idreporte) {
        this.idreporte = idreporte;
    }


    
}