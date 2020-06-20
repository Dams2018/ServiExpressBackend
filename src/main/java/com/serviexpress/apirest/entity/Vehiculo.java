package com.serviexpress.apirest.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Table(name = "Vehiculo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "idvehiculo"
    })
})
@Entity
public class Vehiculo implements Serializable{
    

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idvehiculo;

    
    private Long idcliente;
    //id para identificar dueño del auto
    @NotBlank
    @Size(max = 8)
    private String patente;
    @NotBlank
    @Size(max = 15)
    private String marca;
    @NotBlank
    @Size(max = 25)
    private String modelo;
    @NotBlank
    @Size(max = 15)
    private String tipovehiculo;
    @NotBlank
    @Size(max = 4)
    private String anio;
    @Size(max = 40)
    private String nrochasis;
    private boolean active;

    public Vehiculo() {
    }

    public Vehiculo( Vehiculo vehiculo) {

        this.idcliente = vehiculo.idcliente;
        this.patente = vehiculo.patente;
        this.marca = vehiculo.marca;
        this.modelo = vehiculo.modelo;
        this.tipovehiculo = vehiculo.tipovehiculo;
        this.anio = vehiculo.anio;
        this.nrochasis = vehiculo.nrochasis;
        this.active = vehiculo.active;
    }

    public Long getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Long idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getTipovehiculo() {
        return tipovehiculo;
    }

    public void setTipovehiculo(String tipovehiculo) {
        this.tipovehiculo = tipovehiculo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getNrochasis() {
        return nrochasis;
    }

    public void setNrochasis(String nrochasis) {
        this.nrochasis = nrochasis;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Vehiculo(Long idvehiculo, Long idcliente, @NotBlank @Size(max = 8) String patente,
            @NotBlank @Size(max = 15) String marca,@NotBlank @Size(max = 25) String modelo, @NotBlank @Size(max = 15) String tipovehiculo,
            @NotBlank @Size(max = 4) String anio, @Size(max = 40) String nrochasis, boolean active) {
        this.idvehiculo = idvehiculo;
        this.idcliente = idcliente;
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.tipovehiculo = tipovehiculo;
        this.anio = anio;
        this.nrochasis = nrochasis;
        this.active = active;
    }

   
}