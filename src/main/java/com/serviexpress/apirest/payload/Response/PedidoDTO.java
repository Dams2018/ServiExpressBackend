package com.serviexpress.apirest.payload.response;

import java.sql.Date;

import com.serviexpress.apirest.entity.Producto;
import com.serviexpress.apirest.entity.Proveedor;

public class PedidoDTO {


	private Long idpedido;
    private String empleado;
    private Proveedor proveedor;
    private Producto producto;
	private String cantidad;
	private Date fechapedido;
	private Date fecharecibo;
	private String comentariopedido;
	private int estado;

    public PedidoDTO() {
    }

    public Long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public Date getFecharecibo() {
        return fecharecibo;
    }

    public void setFecharecibo(Date fecharecibo) {
        this.fecharecibo = fecharecibo;
    }

    public String getComentariopedido() {
        return comentariopedido;
    }

    public void setComentariopedido(String comentariopedido) {
        this.comentariopedido = comentariopedido;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PedidoDTO [cantidad=" + cantidad + ", comentariopedido=" + comentariopedido + ", empleado=" + empleado
                + ", estado=" + estado + ", fechapedido=" + fechapedido + ", fecharecibo=" + fecharecibo + ", idpedido="
                + idpedido + ", producto=" + producto + ", proveedor=" + proveedor + "]";
    }


    
}