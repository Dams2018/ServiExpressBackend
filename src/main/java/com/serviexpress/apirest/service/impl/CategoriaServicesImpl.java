package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviexpress.apirest.entity.Categoria;

import com.serviexpress.apirest.repository.CategoriaRepository;



import com.serviexpress.apirest.service.UniversalServices;

import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.data.domain.Pageable;

@Service("serviCategoria")
public class CategoriaServicesImpl extends UniversalServices<Categoria> {
	@Autowired
	@Qualifier("repositoriocategoria")
	private CategoriaRepository repositorio;
	private static final Log logger = LogFactory.getLog(UniversalServices.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError = new MensajeVO();
	String[] mensajes = new String[3];



	@Override
	public ResponseEntity<?> actualizar(Categoria generico) {
		logger.info("ACTUALIZANDO CATEGORIA");
		try {
			Categoria categoria = repositorio.findById(generico.getIdcategoria())
					.orElseThrow(() -> new IllegalStateException("Patente no existe."));
			categoria = generico;
			repositorio.save(categoria);
			logger.info("CATEGORIA ACTUALIZADA");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> crear(Categoria generico) {
		logger.info("CREANDO CATEGORIA");
		try {
//FALTAN VALIDACIONES REVISAR DESPUES
			repositorio.save(generico);
			logger.info("CATEGORIA CREADA");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	

	@Override
	public List<Categoria> obtener() {
		logger.info("OBTENIENDO TODOS LAS CATEGIRIAS");
		return repositorio.findAll();
	}

	@Override
	public List<Categoria> obtenerPorPaginacion(Pageable pageable){
		return repositorio.findAll(pageable).getContent();
	}
}