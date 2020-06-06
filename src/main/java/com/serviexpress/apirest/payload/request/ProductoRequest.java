package com.serviexpress.apirest.payload.request;

import com.serviexpress.apirest.entity.states.EstadoProducto;

public class ProductoRequest {
    
    private long id;

    private String nombre;

    private long idMarca;

    private String descripcion;

    private int precioNeto;

    private int iva;

    private int precio;

    private EstadoProducto estadoProducto;

    /**
     * 
     */
    public ProductoRequest() {
    }

    



    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return long return the idMarca
     */
    public long getIdMarca() {
        return idMarca;
    }

    /**
     * @param idMarca the idMarca to set
     */
    public void setIdMarca(long idMarca) {
        this.idMarca = idMarca;
    }

    /**
     * @return String return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return int return the precioNeto
     */
    public int getPrecioNeto() {
        return precioNeto;
    }

    /**
     * @param precioNeto the precioNeto to set
     */
    public void setPrecioNeto(int precioNeto) {
        this.precioNeto = precioNeto;
    }

    /**
     * @return int return the iva
     */
    public int getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(int iva) {
        this.iva = iva;
    }

    /**
     * @return int return the precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * @return EstadoProducto return the estadoProducto
     */
    public EstadoProducto getEstadoProducto() {
        return estadoProducto;
    }

    /**
     * @param estadoProducto the estadoProducto to set
     */
    public void setEstadoProducto(EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

}