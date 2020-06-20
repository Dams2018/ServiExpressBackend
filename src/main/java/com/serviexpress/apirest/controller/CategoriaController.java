package com.serviexpress.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.entity.Categoria;
import com.serviexpress.apirest.entity.Vehiculo;
import com.serviexpress.apirest.service.impl.CategoriaServicesImpl;
import com.serviexpress.apirest.service.impl.VehiculoServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@Qualifier("serviCategoria")
	CategoriaServicesImpl categoriaServicesImpl;


	// Cliente
	@PutMapping("/categoria")
	public ResponseEntity<?> agregarCategoria(@RequestBody @Valid Categoria categoria) {
		return ResponseEntity.ok(categoriaServicesImpl.crear(categoria));

	}

	@PostMapping("/categoria")
	public ResponseEntity<?> actualizarCategoria(@RequestBody @Valid Categoria categoria) {
		return ResponseEntity.ok(categoriaServicesImpl.actualizar(categoria));
	}

	@GetMapping("/categorias/{idCategoria}")
	public Categoria show(@PathVariable Long idCategoria){
		
		return categoriaServicesImpl.findById(idCategoria);
	}

	@GetMapping(value = "/categorias")
	public List<Categoria> obtenerCategoria(Pageable pageable) {
		
		return categoriaServicesImpl.obtenerPorPaginacion(pageable);
	}




}