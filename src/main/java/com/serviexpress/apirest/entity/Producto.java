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
    /**
     *
     */
  

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idproducto;
    @NotBlank
    @Size(max = 50)
    private String nombre;
    @NotBlank
    @Size(max = 255)
    private String descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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



    
    public Producto( Producto producto) {
        this.idproducto = producto.idproducto;
        this.nombre = producto.nombre;
        this.descripcion = producto.descripcion;
        this.valorbase = producto.valorbase;
        this.categoria = producto.categoria;
    }

    @Override
    public String toString() {
        return "Producto [categoria=" + categoria + ", descripcion=" + descripcion + ", idproducto=" + idproducto
                + ", nombre=" + nombre + ", valorbase=" + valorbase + "]";
    }

    public Producto(Long idproducto, @NotBlank @Size(max = 20) String nombre,
            @NotBlank @Size(max = 255) String descripcion, double valorbase, Long categoria) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorbase = valorbase;
        this.categoria = categoria;
    }

}