package com.serviexpress.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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
	private Long idempleado;
	private Long idproveedor;
	private Long idproducto;
	private String cantidad;
	private Date fechapedido;
	private Date fecharecibo;
	private int estado;

	public Pedido() {
	}

	public Long getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(Long idpedido) {
		this.idpedido = idpedido;
	}

	public Long getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(Long idempleado) {
		this.idempleado = idempleado;
	}

	public Long getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(Long idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Long getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Long idproducto) {
		this.idproducto = idproducto;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Pedido(Pedido pedido) {
		this.idpedido = pedido.idpedido;
		this.idempleado = pedido.idempleado;
		this.idproveedor = pedido.idproveedor;
		this.idproveedor = pedido.idproveedor;
		this.idproducto = pedido.idproducto;
		this.cantidad = pedido.cantidad;
		this.fechapedido = pedido.fechapedido;
		this.fecharecibo = pedido.fecharecibo;
		this.estado = pedido.estado;
	}

	public Pedido(Long idpedido, Long idempleado, Long idproveedor, Long idproducto, String cantidad, Date fechapedido,
			Date fecharecibo, int estado) {
		this.idpedido = idpedido;
		this.idempleado = idempleado;
		this.idproveedor = idproveedor;
		this.idproveedor = idproveedor;
		this.idproducto = idproducto;
		this.cantidad = cantidad;
		this.fechapedido = fechapedido;
		this.fecharecibo = fecharecibo;
		this.estado = estado;
	}

}