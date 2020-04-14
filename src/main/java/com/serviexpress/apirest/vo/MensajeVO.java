
package com.serviexpress.apirest.vo;

import java.io.Serializable;

public class MensajeVO implements Serializable {

	private static final long serialVersionUID = -4386829749075232981L;

	private String fechaRsp;
	private String horaRsp;
	private String mensaje;
	private String codigo;

	public MensajeVO() {

	}

	public MensajeVO(String fechaRsp, String horaRsp, String mensaje, String codigo) {
		this.setFechaRsp(fechaRsp);
		this.setHoraRsp(horaRsp);
		this.setMensaje(mensaje);
		this.setCodigo(codigo);
	}

	public String getFechaRsp() {
		return fechaRsp;
	}

	public void setFechaRsp(String fechaRsp) {
		this.fechaRsp = fechaRsp;
	}

	public String getHoraRsp() {
		return horaRsp;
	}

	public void setHoraRsp(String horaRsp) {
		this.horaRsp = horaRsp;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "MensajeVO [fechaRsp=" + fechaRsp + ", horaRsp=" + horaRsp + ", mensaje=" + mensaje + ", codigo="
				+ codigo + "]";
	}

}
