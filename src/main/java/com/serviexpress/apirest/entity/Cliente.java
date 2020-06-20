package com.serviexpress.apirest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




@Table(name = "cliente", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "idcliente"
    }),
    @UniqueConstraint(columnNames = {
        "idusuario"
    })
})
@Entity
public class Cliente implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 8715864315878470306L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idcliente;
    private Long idusuario;
    @NotBlank
    @Size(max = 40)
    private String rut;
    @NotBlank
    @Size(max = 40)
    private String nombre;
    @NotBlank
    @Size(max = 40)
    private String apellido;
    @NotBlank
    @Size(max = 40)
    private String telefono;

    private Date fechaNacimiento;

    public Cliente() {
    }

    public Cliente(Cliente cliente) {
        this.idusuario=cliente.idusuario;
        this.nombre= cliente.nombre;
        this.apellido=cliente.apellido;
        this.rut=cliente.rut;
        this.telefono=cliente.telefono;
        this.fechaNacimiento=cliente.fechaNacimiento;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Cliente [apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", idcliente=" + idcliente
                + ", idusuario=" + idusuario + ", nombre=" + nombre + ", rut=" + rut + ", telefono=" + telefono + "]";
    }


 
}