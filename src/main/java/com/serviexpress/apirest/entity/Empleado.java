package com.serviexpress.apirest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Table(name = "empleado", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "idempleado"
    }),
    @UniqueConstraint(columnNames = {
        "idusuario"
    })
})
@Entity
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idempleado;

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

    public Empleado() {
    }

    public Empleado(Empleado empleado) {
        this.idusuario=empleado.idusuario;
        this.nombre= empleado.nombre;
        this.apellido=empleado.apellido;
        this.rut=empleado.rut;
        this.telefono=empleado.telefono;
        this.fechaNacimiento=empleado.fechaNacimiento;
    }

    public Long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Long idempleado) {
        this.idempleado = idempleado;
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

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public String toString() {
        return "Empleado [apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", idempleado=" + idempleado
                + ", idusuario=" + idusuario + ", nombre=" + nombre + ", rut=" + rut + ", telefono=" + telefono + "]";
    }


 

}