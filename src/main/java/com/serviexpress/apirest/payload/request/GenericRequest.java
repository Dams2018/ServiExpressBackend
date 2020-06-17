package com.serviexpress.apirest.payload.request;

public class GenericRequest {
    
    private int tipoConsulta;
    private ProductoRequest producto;
    private MarcaRequest marca;

    

    /**
     * @return int return the tipoConsulta
     */
    public int getTipoConsulta() {
        return tipoConsulta;
    }

    /**
     * @param tipoConsulta the tipoConsulta to set
     */
    public void setTipoConsulta(int tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    /**
     * @return ProductoRequest return the producto
     */
    public ProductoRequest getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(ProductoRequest producto) {
        this.producto = producto;
    }

    /**
     * @return MarcaRequest return the marca
     */
    public MarcaRequest getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(MarcaRequest marca) {
        this.marca = marca;
    }

}