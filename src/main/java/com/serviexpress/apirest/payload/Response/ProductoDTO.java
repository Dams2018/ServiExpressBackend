package com.serviexpress.apirest.payload.response;

public class ProductoDTO {

    private Long idproducto;
    private String nombre;
    private String descripcion;
    private double valorbase;
    private Number stock;
    private Long categoria;

    public ProductoDTO() {
    }
    
    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
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

    public Number getStock() {
        return stock;
    }

    public void setStock(Number stock) {
        this.stock = stock;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ProductoDTO [categoria=" + categoria + ", descripcion=" + descripcion + ", idproducto=" + idproducto
                + ", nombre=" + nombre + ", stock=" + stock + ", valorbase=" + valorbase + "]";
    }


    
}