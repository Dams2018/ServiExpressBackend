package com.serviexpress.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import antlr.collections.List;



@Table(name = "Reserva", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "idreserva"
    }),
    @UniqueConstraint(columnNames = {
        "idvehiculo"
    })
})
@Entity
public class Reserva implements Serializable{
    

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idreserva;
    private Long idcliente;
    private Long idvehiculo;

    //guardar lista de id serv y prodcuto luego en el front mostrar los servicio por ende cuando se mande la lista al front debo sacar los id recorrerlos y devolver lista de servicio
    @NotBlank
    private String servicios;
    private String productos;
    private Date fechareserva;
    private String horareserva;
    private int estado;



    public Reserva() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(Long idreserva) {
        this.idreserva = idreserva;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }


    public Date getFechareserva() {
        return fechareserva;
    }

    public void setFechareserva(Date fechareserva) {
        this.fechareserva = fechareserva;
    }

    public String getHorareserva() {
        return horareserva;
    }

    public void setHorareserva(String horareserva) {
        this.horareserva = horareserva;
    }


    public Reserva( Reserva reserva) {

        this.idreserva = reserva.idreserva;
        this.idcliente = reserva.idcliente;
        this.servicios = reserva.servicios;
        this.fechareserva = reserva.fechareserva;
        this.horareserva = reserva.horareserva;
        this.estado = reserva.estado;
        this.productos = reserva.productos;
        this.idvehiculo = reserva.idvehiculo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }


    public Long getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Long idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public Reserva(Long idreserva, Long idcliente, Long idvehiculo, String servicios, String productos,
            Date fechareserva, String horareserva, int estado) {
        this.idreserva = idreserva;
        this.idcliente = idcliente;
        this.idvehiculo = idvehiculo;
        this.servicios = servicios;
        this.productos = productos;
        this.fechareserva = fechareserva;
        this.horareserva = horareserva;
        this.estado = estado;
    }






   
}