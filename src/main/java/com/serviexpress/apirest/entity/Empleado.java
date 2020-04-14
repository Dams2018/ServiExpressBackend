package com.porta.porta.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Table(name = "empleado", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "idempleado"
    }),
    @UniqueConstraint(columnNames = {
        "id_usuario"
    })
})
@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idempleado;

    private Long id_usuario;

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
    @NotBlank
    @Size(max = 40)
    private String fechaNacimiento;

    public Empleado() {
    }

    public Long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}