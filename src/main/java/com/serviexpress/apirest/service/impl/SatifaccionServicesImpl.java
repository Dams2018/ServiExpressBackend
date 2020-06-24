package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import com.serviexpress.apirest.entity.Satifaccion;
import com.serviexpress.apirest.payload.Encuesta;
import com.serviexpress.apirest.repository.SatifaccionRepository;
import com.serviexpress.apirest.service.UniversalServices;

@Service
public class SatifaccionServicesImpl extends UniversalServices<Satifaccion> {
	@Autowired
	private SatifaccionRepository repositorio;

	private static final Log logger = LogFactory.getLog(UniversalServices.class);



	@Override
	public ResponseEntity<?> crearwithList(List<Encuesta> generico) {
		logger.info("CREANDO ENCUESTA SATIFACCION");
		try {
			for (Encuesta encuesta : generico) {

				Satifaccion satifaccion = new Satifaccion();

				satifaccion.setNombre(encuesta.getNombre());
				satifaccion.setTipo(encuesta.getTipo());
				satifaccion.setFecha(encuesta.getFecha());

				repositorio.save(satifaccion);

			}
			logger.info("ENCUESTA CREADA");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}


	@Override
	public long obtenerEncuesta() {
		logger.info("OBTENIENDO TODOS LOS PRODUCTO");
		return repositorio.countByNombre("servicio");
	}

}