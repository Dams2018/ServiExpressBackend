package com.serviexpress.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.entity.Categoria;
import com.serviexpress.apirest.entity.Servicio;
import com.serviexpress.apirest.entity.Vehiculo;
import com.serviexpress.apirest.service.impl.CategoriaServicesImpl;
import com.serviexpress.apirest.service.impl.ServicioServicesImpl;
import com.serviexpress.apirest.service.impl.VehiculoServicesImpl;

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
@RequestMapping("/api/servicio")
public class ServicioController {

	@Autowired
	@Qualifier("serviServicio")
	ServicioServicesImpl servicioServicesImpl;


	// Cliente
	@PutMapping("/servicio")
	public ResponseEntity<?> agregarCategoria(@RequestBody @Valid Servicio servicio) {
		return ResponseEntity.ok(servicioServicesImpl.crear(servicio));

	}

	@PostMapping("/servicio")
	public ResponseEntity<?> actualizarServicio(@RequestBody @Valid Servicio servicio) {
		return ResponseEntity.ok(servicioServicesImpl.actualizar(servicio));
	}

	@GetMapping(value = "/servicios")
	public List<Servicio> obtenerServicio(Pageable pageable) {
		
		return servicioServicesImpl.obtenerPorPaginacion(pageable);
	}


	@GetMapping(value = "/allservicios")
	public List<Servicio> allServicios() {
		return servicioServicesImpl.obtener();
	}



}