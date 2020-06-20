package com.serviexpress.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviexpress.apirest.payload.request.CategoriaRequest;
import com.serviexpress.apirest.service.impl.CategoriaServicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	CategoriaServicesImpl categoriaServicesImpl;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// Crear categoria
	@PutMapping("/add")
	public ResponseEntity<?> agregarCategoria(@RequestBody @Valid CategoriaRequest categoria)
			throws JsonProcessingException {

		log.info("ENVIANDO CREACION DE CATEGORIA DATOS: "+ new ObjectMapper().writeValueAsString(categoria));
		return categoriaServicesImpl.crear(categoria);

	}

	//Actualizar Categoria
	@PostMapping("/update")
	public ResponseEntity<?> actualizarCategoria(@RequestBody @Valid CategoriaRequest categoria)
			throws JsonProcessingException {
		log.info("ENVIANDO ACTUALIZACION DE CATEGORIA DATOS: "+ new ObjectMapper().writeValueAsString(categoria));
		return categoriaServicesImpl.actualizar(categoria);
	}

	//Listar Categoria
	@GetMapping(value = "/categorias")
	public List<CategoriaRequest> obtenerCategoria(Pageable pageable) {
		
		return categoriaServicesImpl.obtenerPorPaginacion(pageable);
	}





}