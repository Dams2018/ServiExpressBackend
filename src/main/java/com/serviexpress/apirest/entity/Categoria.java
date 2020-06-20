package com.serviexpress.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name = "Categoria", uniqueConstraints = { @UniqueConstraint(columnNames = { "idcategoria" }) })
@Entity
public class Categoria implements Serializable {

    /**
     * PRIMERO SE DEBEN GENRAR CATEGORIAS PARA PODER GENERAR PRODUCTO O SERVICIO LO SERVICIO PUEDE DEPENDER DE PRODUCTOS PERO ESO SE ASOCIA POR LA CATEGORIA
     * APLICO ESTA LOGICA YA QUE SE PODRIA VENDER PRODUCTOS SOLO IDEA EN LA WEB
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idcategoria;
    @NotBlank
    @Size(max = 50)
    private String nombre;
    @Size(max = 255)
    private String descripcion;


    public Categoria() {
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


    public Categoria(Long idcategoria, @NotBlank @Size(max = 20) String nombre, @Size(max = 255) String descripcion) {
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Categoria( Categoria categoria) {
        this.idcategoria = categoria.idcategoria;
        this.nombre = categoria.nombre;
        this.descripcion = categoria.descripcion;
    }

    public Long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
    }

    

}