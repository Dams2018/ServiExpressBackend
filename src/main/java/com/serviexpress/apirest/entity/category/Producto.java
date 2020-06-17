package com.serviexpress.apirest.entity.category;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.serviexpress.apirest.entity.states.EstadoProducto;

@Table(name = "Producto")
@Entity
public class Producto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Size(max = 25)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @NotBlank
    @Size(max = 400)
    private String descripcion;


    private int precioNeto;


    private int iva;


    private int precio;


    private EstadoProducto estado;


    @ManyToOne
    @JoinColumn(name = "id_det_cat")
    private DetalleCategoria detalleCategoria;


    


 

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
     * @return String return the marca
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
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
     * @return EstadoProducto return the estado
     */
    public EstadoProducto getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    /**
     * @return DetalleCategoria return the detalleCategoria
     */
    public DetalleCategoria getDetalleCategoria() {
        return detalleCategoria;
    }

    /**
     * @param detalleCategoria the detalleCategoria to set
     */
    public void setDetalleCategoria(DetalleCategoria detalleCategoria) {
        this.detalleCategoria = detalleCategoria;
    }

    /**
     * 
     */
    public Producto() {
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

}