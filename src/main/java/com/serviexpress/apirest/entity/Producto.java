package com.serviexpress.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name = "Producto", uniqueConstraints = { @UniqueConstraint(columnNames = { "idproducto" }) })
@Entity
public class Producto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idproducto;
    @NotBlank
    @Size(max = 20)
    private String nombre;
    @NotBlank
    @Size(max = 255)
    private String descripción;
    private double valorbase;
    private Long categoria;

    public Producto() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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



    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public Producto(Long idproducto, @NotBlank @Size(max = 20) String nombre,
            @NotBlank @Size(max = 255) String descripción, double valorbase, Long categoria) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.descripción = descripción;
        this.valorbase = valorbase;
        this.categoria = categoria;
    }

    
    public Producto( Producto producto) {
        this.idproducto = producto.idproducto;
        this.nombre = producto.nombre;
        this.descripción = producto.descripción;
        this.valorbase = producto.valorbase;
        this.categoria = producto.categoria;
    }

}