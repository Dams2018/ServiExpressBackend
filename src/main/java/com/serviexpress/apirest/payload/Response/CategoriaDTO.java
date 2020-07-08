package com.serviexpress.apirest.payload.response;

public class CategoriaDTO {
    

    private Long idcategoria;
    private String nombre;
    private String descripcion;

    public CategoriaDTO() {
    }

    public Long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
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

    @Override
    public String toString() {
        return "CategoriaDTO [descripcion=" + descripcion + ", idcategoria=" + idcategoria + ", nombre=" + nombre + "]";
    }
}