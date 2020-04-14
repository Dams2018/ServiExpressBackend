package com.porta.porta.vo;

import java.io.Serializable;

public class ResultadoVO implements Serializable {

	private static final long serialVersionUID = 5239785946322403062L;

	private MensajeVO peticion;

	public ResultadoVO() {

	}

	public void setPeticion(String fechaRsp, String horaRsp, String mensaje, String codigo) {
		MensajeVO peticion = new MensajeVO(fechaRsp, horaRsp, mensaje, codigo);
		this.peticion = peticion;
	}

	public void setPeticion(MensajeVO mensajeVO) {
		this.peticion = mensajeVO;
	}

	public MensajeVO getPeticion() {
		return peticion;
	}

	@Override
	public String toString() {
		return "ResultadoVO [peticion=" + peticion + "]";
	}

}
