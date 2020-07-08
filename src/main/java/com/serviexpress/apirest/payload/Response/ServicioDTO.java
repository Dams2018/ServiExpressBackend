package com.serviexpress.apirest.payload.response;

public class ServicioDTO {

    private Long idservicio;
    private String nombre;
    private String descripcion;
    private double valorbase;
    private Long categoria;

    public ServicioDTO() {
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

    @Override
    public String toString() {
        return "servicioDTO [categoria=" + categoria + ", descripcion=" + descripcion + ", idservicio=" + idservicio
                + ", nombre=" + nombre + ", valorbase=" + valorbase + "]";
    }
    
}