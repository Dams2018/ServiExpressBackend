package com.serviexpress.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name = "Servicio", uniqueConstraints = { @UniqueConstraint(columnNames = { "idservicio" }) })
@Entity
public class Servicio implements Serializable {

    /**
     * al momento de selecionar un servicio capturar id categoria enviarla al buscar productos con el id de categoria cargrar combocx de prodcuto esto para reserva
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idservicio;
    @NotBlank
    @Size(max = 20)
    private String nombre;
    @NotBlank
    @Size(max = 255)
    private String descripcion;
    private double valorbase;
    private Long categoria;



    public Servicio() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Long idservicio) {
        this.idservicio = idservicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public double getValorbase() {
        return valorbase;
    }

    public void setValorbase(double valorbase) {
        this.valorbase = valorbase;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

 

    public Servicio( Servicio servicio) {

        this.idservicio = servicio.idservicio;
        this.nombre = servicio.nombre;
        this.descripcion = servicio.descripcion;
        this.valorbase = servicio.valorbase;
        this.categoria = servicio.categoria;
    }



    public Servicio(Long idservicio, @NotBlank @Size(max = 20) String nombre,
            @NotBlank @Size(max = 255) String descripcion, double valorbase, Long categoria) {
        this.idservicio = idservicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorbase = valorbase;
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}