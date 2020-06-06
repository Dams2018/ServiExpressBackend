package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviexpress.apirest.entity.category.*;
import com.serviexpress.apirest.payload.ApiResponse;
import com.serviexpress.apirest.payload.request.CategoriaRequest;
import com.serviexpress.apirest.repository.CategoriaRepository;



import com.serviexpress.apirest.service.UniversalServices;

import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.data.domain.Pageable;

@Service
public class CategoriaServicesImpl extends UniversalServices<CategoriaRequest> {
	@Autowired
	@Qualifier("repositoriocategoria")
	private CategoriaRepository repositorio;
	private static final Log logger = LogFactory.getLog(UniversalServices.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError = new MensajeVO();
	String[] mensajes = new String[3];



	@Override
	public ResponseEntity<?> actualizar(CategoriaRequest generico) {
		logger.info("ACTUALIZANDO CATEGORIA");
		try {
			Categoria categoria = repositorio.findById(generico.getId())
					.orElseThrow(() -> new IllegalStateException("Categoria"));
			categoria.setNombre(generico.getName());
			categoria.setDescripcion(generico.getDescription());
			repositorio.save(categoria);
			logger.info("CATEGORIA ACTUALIZADA");
			ApiResponse response = new ApiResponse(true, "Categoria Actualizada");
			return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> crear(CategoriaRequest generico) {

		try {
			logger.info("CREANDO CATEGORIA: "+new ObjectMapper().writeValueAsString(generico));
		//FALTAN VALIDACIONES REVISAR DESPUES
			Categoria newCategoria = new Categoria(generico.getId(), generico.getName(), generico.getDescription());
			repositorio.save(newCategoria);
			logger.info("CATEGORIA CREADA");
			ApiResponse response = new ApiResponse(true, "Categoria Creada");
			return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	

	@Override
	public List<CategoriaRequest> obtener() {
		logger.info("OBTENIENDO TODOS LAS CATEGIRIAS");
		List<CategoriaRequest> listCategory = new ArrayList<>();

		for (Categoria categoria : repositorio.findAll()) {
			CategoriaRequest frontCategory = new CategoriaRequest(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
			listCategory.add(frontCategory);
		}

		return listCategory;
	}

	@Override
	public List<CategoriaRequest> obtenerPorPaginacion(Pageable pageable){
		List<CategoriaRequest> listCategory = new ArrayList<>();

		for (Categoria categoria : repositorio.findAll(pageable).getContent()) {
			CategoriaRequest frontCategory = new CategoriaRequest(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
			listCategory.add(frontCategory);
		}
		
		return listCategory;
	}


}