package com.serviexpress.apirest.entity;

import java.io.Serializable;


import javax.persistence.*;



@Table(name = "EstadoReserva", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "idreserva"
    })
})
@Entity
public class EstadoReserva implements Serializable{
    

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private Long idreserva;
    private String nombre;



    public EstadoReserva() {
    }

    public Long getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(Long idreserva) {
        this.idreserva = idreserva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoReserva(Long idreserva, String nombre) {
        this.idreserva = idreserva;
        this.nombre = nombre;
    }

  

}