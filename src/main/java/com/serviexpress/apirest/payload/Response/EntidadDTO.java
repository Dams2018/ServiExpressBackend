package com.serviexpress.apirest.payload.response;

import java.util.Date;

public class EntidadDTO {
    
    private Long idcliente;
    private Long idusuario;
    private String rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private Date fechaNacimiento;

    public EntidadDTO() {
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
        return "ClienteDTO [apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", idcliente=" + idcliente
                + ", idusuario=" + idusuario + ", nombre=" + nombre + ", rut=" + rut + ", telefono=" + telefono + "]";
    }
}