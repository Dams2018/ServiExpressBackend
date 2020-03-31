package com.porta.porta.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import java.util.HashSet;
import java.util.Set;

@Table(name = "empleado")
@Entity
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_empleado;

    
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private User id_usuario;

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



    /**
     * @return Long return the id_empleado
     */
    public Long getId_empleado() {
        return id_empleado;
    }

    /**
     * @param id_empleado the id_empleado to set
     */
    public void setId_empleado(Long id_empleado) {
        this.id_empleado = id_empleado;
    }

    /**
     * @return User return the id_usuario
     */
    public User getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(User id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return String return the rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(String rut) {
        this.rut = rut;
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
     * @return String return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return String return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return String return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}