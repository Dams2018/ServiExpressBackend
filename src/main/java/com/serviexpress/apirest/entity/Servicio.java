package com.serviexpress.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name = "Servicio", uniqueConstraints = { @UniqueConstraint(columnNames = { "idservicio" }) })
@Entity
public class Servicio implements Serializable {

    /**
     *
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
    private String descripción;
    private double valorbase;
    private Long categoria;

    //si deponde mostra productos para el front esto para que no se me olvide
    private boolean depende;

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

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
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
        this.descripción = servicio.descripción;
        this.valorbase = servicio.valorbase;
        this.categoria = servicio.categoria;
        this.depende = servicio.depende;
    }

    public boolean isDepende() {
        return depende;
    }

    public void setDepende(boolean depende) {
        this.depende = depende;
    }

    public Servicio(Long idservicio, @NotBlank @Size(max = 20) String nombre,
            @NotBlank @Size(max = 255) String descripción, double valorbase, Long categoria, boolean depende) {
        this.idservicio = idservicio;
        this.nombre = nombre;
        this.descripción = descripción;
        this.valorbase = valorbase;
        this.categoria = categoria;
        this.depende = depende;
    }

}