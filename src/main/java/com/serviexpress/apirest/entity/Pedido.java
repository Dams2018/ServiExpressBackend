package com.serviexpress.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "Pedido", uniqueConstraints = { @UniqueConstraint(columnNames = { "idpedido" }) })
@Entity
public class Pedido implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idpedido;
    private String empleado;
	@ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Proveedor proveedor;
	@ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Producto producto;
	private String cantidad;
	@Temporal(TemporalType.DATE)
	private Date fechapedido;
	@Temporal(TemporalType.DATE)
	private Date fecharecibo;
	private String comentariopedido;
	private int estado;

	public Pedido() {
	}
	@PrePersist
	public void prePersist() {
		fechapedido = new Date();}

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

	public Pedido(Pedido pedido) {
		this.idpedido = pedido.idpedido;
		this.empleado = pedido.empleado;
		this.proveedor = pedido.proveedor;
		this.proveedor = pedido.proveedor;
		this.producto = pedido.producto;
		this.cantidad = pedido.cantidad;
		this.fechapedido = pedido.fechapedido;
		this.fecharecibo = pedido.fecharecibo;
		this.comentariopedido = pedido.comentariopedido;
		this.estado = pedido.estado;
	}

	@Override
    public String toString() {
        return "Pedido [producto=" + producto + ", proveedor=" + proveedor + ", empelado=" + empleado
                + ", cantidad=" + cantidad + ", fechapedido=" + fechapedido + ", fecharecibo" + fecharecibo + ", comentariopedido" + comentariopedido + ", estado=" + estado + "]";
    }

	public Pedido(Long idpedido, String empleado, Proveedor proveedor, Producto producto, String cantidad, Date fechapedido,
			Date fecharecibo, String comentariopedido, int estado) {
		this.idpedido = idpedido;
		this.empleado = empleado;
		this.proveedor = proveedor;
		this.producto = producto;
		this.cantidad = cantidad;
		this.fechapedido = fechapedido;
		this.fecharecibo = fecharecibo;
		this.comentariopedido = comentariopedido;
		this.estado = estado;
	}



	

	

	

}