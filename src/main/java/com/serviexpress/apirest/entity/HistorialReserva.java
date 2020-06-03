
package com.serviexpress.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;



@Table(name = "HistorialReserva", uniqueConstraints = {

})
@Entity
public class HistorialReserva implements Serializable{
    

    /**
     *Faltaria este crud
     */
    private static final long serialVersionUID = 1L;

    @Id
    private Long idreserva;
    private String estadoDes;
    private String comentario;
    private Date fecha;




    public HistorialReserva() {
    }

    public Long getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(Long idreserva) {
        this.idreserva = idreserva;
    }

    public String getEstadoDes() {
        return estadoDes;
    }

    public void setEstadoDes(String estadoDes) {
        this.estadoDes = estadoDes;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public HistorialReserva(Long idreserva, String estadoDes, String comentario, Date fecha) {
        this.idreserva = idreserva;
        this.estadoDes = estadoDes;
        this.comentario = comentario;
        this.fecha = fecha;
    }
}