package com.serviexpress.apirest.payload.request;

public class CategoriaDetalleRequest {
    
    private long id;
    private String nombre;
    private String descripcion;
    private long idCategoria;
    private String categoria;

    /**
     * 
     */
    public CategoriaDetalleRequest() {
    }




    

    /**
     * @return int return the id
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
     * @return long return the idCategorioa
     */
    public long getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategorioa the idCategorioa to set
     */
    public void setIdCategoria(long idCategorioa) {
        this.idCategoria = idCategorioa;
    }

    /**
     * @return String return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @param id
     * @param nombre
     * @param descripcion
     * @param idCategoria
     * @param categoria
     */
    public CategoriaDetalleRequest(long id, String nombre, String descripcion, long idCategoria, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.categoria = categoria;
    }

}