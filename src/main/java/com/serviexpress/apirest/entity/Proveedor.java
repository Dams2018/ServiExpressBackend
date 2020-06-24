package com.serviexpress.apirest.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name = "Proveedor", uniqueConstraints = { @UniqueConstraint(columnNames = { "idproveedor" }) })
@Entity
public class Proveedor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idproveedor;

    @NotBlank
    @Size(max = 10)
    private String rut;
    @NotBlank
    @Size(max = 20)
    private String nombre;
    @NotBlank
    @Size(max = 40)
    private String apellido;
    @NotBlank
    @Size(max = 10)
    private String telefono;
    @NotBlank
    @Size(max = 60)
    private String correo;
    private boolean active;

    public Proveedor() {
    }

    public Proveedor(Proveedor proveedor) {

        this.idproveedor = proveedor.idproveedor;
        this.rut = proveedor.rut;
        this.nombre = proveedor.nombre;
        this.apellido = proveedor.apellido;
        this.telefono = proveedor.telefono;
        this.correo = proveedor.correo;
        this.active = proveedor.active;
    }

    public Long getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Long idvehiculo) {
        this.idproveedor = idvehiculo;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Proveedor(Long idproveedor, @NotBlank @Size(max = 10) String rut,
            @NotBlank @Size(max = 20) String nombre,@NotBlank @Size(max = 40) String apellido, @NotBlank @Size(max = 10) String telefono,
            @NotBlank @Size(max = 60) String correo, boolean active) {
		this.idproveedor = idproveedor;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.active = active;
    }

}