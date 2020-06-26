

package com.serviexpress.apirest.entity;



import java.util.Date;

import javax.persistence.*;


@Table(name = "Satifaccion", uniqueConstraints = { @UniqueConstraint(columnNames = { "idsatifaccion" }) })
@Entity
public class Satifaccion {

    /**
     * al momento de selecionar un servicio capturar id categoria enviarla al buscar productos con el id de categoria cargrar combocx de prodcuto esto para reserva
     */


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idsatifaccion;
    private String nombre;
    private String tipo;
	@Temporal(TemporalType.DATE)
	private Date fecha;

    

    public Satifaccion( Satifaccion satifaccion) {

        this.nombre = satifaccion.nombre;
        this.tipo = satifaccion.tipo;
        this.fecha = satifaccion.fecha;
    }

    public Long getIdsatifaccion() {
        return idsatifaccion;
    }

    public void setIdsatifaccion(Long idsatifaccion) {
        this.idsatifaccion = idsatifaccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



    public Satifaccion(Long idsatifaccion, String nombre, String tipo, Date fecha) {
        this.idsatifaccion = idsatifaccion;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Satifaccion() {
    }

  




}