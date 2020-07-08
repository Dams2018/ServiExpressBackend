package com.serviexpress.apirest.payload.response;

public class ProveedorDTO {
    
    private Long idproveedor;
    private String rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private String empresa;
    private String producto;
    private String correo;
    private boolean active;

    public ProveedorDTO() {
    }

    public Long getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Long idproveedor) {
        this.idproveedor = idproveedor;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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

    @Override
    public String toString() {
        return "ProveedorDTO [active=" + active + ", apellido=" + apellido + ", correo=" + correo + ", empresa="
                + empresa + ", idproveedor=" + idproveedor + ", nombre=" + nombre + ", producto=" + producto + ", rut="
                + rut + ", telefono=" + telefono + "]";
    }
}