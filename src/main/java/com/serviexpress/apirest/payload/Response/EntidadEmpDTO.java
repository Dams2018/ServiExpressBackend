package com.serviexpress.apirest.payload.response;

import java.util.Date;

public class EntidadEmpDTO {
    
    private Long idempleado;
    private Long idusuario;
    private String rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private Date fechaNacimiento;

    public EntidadEmpDTO() {
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

    public Long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    @Override
    public String toString() {
        return "EntidadEmpDTO [apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", idempleado="
                + idempleado + ", idusuario=" + idusuario + ", nombre=" + nombre + ", rut=" + rut + ", telefono="
                + telefono + "]";
    }

 
}