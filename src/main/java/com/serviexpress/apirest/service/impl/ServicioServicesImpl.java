package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviexpress.apirest.entity.Servicio;
import com.serviexpress.apirest.repository.ServicioRepository;
import com.serviexpress.apirest.service.UniversalServices;

import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.data.domain.Pageable;

@Service("serviServicio")
public class ServicioServicesImpl extends UniversalServices<Servicio> {
	@Autowired
	@Qualifier("repositorioservicio")
	private ServicioRepository repositorio;
	private static final Log logger = LogFactory.getLog(UniversalServices.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError = new MensajeVO();
	String[] mensajes = new String[3];



	@Override
	public ResponseEntity<?> actualizar(Servicio generico) {
		logger.info("ACTUALIZANDO SERVICIO");
		try {
			Servicio Servicio = repositorio.findById(generico.getIdservicio())
					.orElseThrow(() -> new IllegalStateException("Servicio no existe."));
			Servicio = generico;
			repositorio.save(Servicio);
			logger.info("SERVICIO ACTUALIZADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> crear(Servicio generico) {
		logger.info("CREANDO SERVICIO");
		try {
			repositorio.save(generico);
			logger.info("SERVICIO CREADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	

	@Override
	public List<Servicio> obtener() {
		logger.info("OBTENIENDO TODOS LOS SERVICIOS");
		return repositorio.findAll();
	}

	@Override
	public List<Servicio> obtenerPorPaginacion(Pageable pageable){
		return repositorio.findAll(pageable).getContent();
	}
}