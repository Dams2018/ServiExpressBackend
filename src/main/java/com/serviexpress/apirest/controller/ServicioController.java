package com.serviexpress.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.entity.Servicio;
import com.serviexpress.apirest.payload.response.ServicioDTO;
import com.serviexpress.apirest.service.impl.ServicioServicesImpl;


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
@RequestMapping("/api/servicio")
public class ServicioController {

	@Autowired
	@Qualifier("serviServicio")
	ServicioServicesImpl servicioServicesImpl;


	// Cliente
	@PutMapping("/servicio")
	public ResponseEntity<?> agregarCategoria(@RequestBody @Valid ServicioDTO servicioDTO) {
		Servicio servicio=new Servicio();
		servicio.setCategoria(servicioDTO.getCategoria());
		servicio.setDescripcion(servicioDTO.getDescripcion());
		servicio.setNombre(servicioDTO.getNombre());
		servicio.setValorbase(servicioDTO.getValorbase());
		return ResponseEntity.ok(servicioServicesImpl.crear(servicio));

	}

	@PostMapping("/servicio")
	public ResponseEntity<?> actualizarServicio(@RequestBody @Valid ServicioDTO servicioDTO) {
		Servicio servicio=new Servicio();
		servicio.setCategoria(servicioDTO.getCategoria());
		servicio.setDescripcion(servicioDTO.getDescripcion());
		servicio.setNombre(servicioDTO.getNombre());
		servicio.setValorbase(servicioDTO.getValorbase());
		servicio.setIdservicio(servicioDTO.getIdservicio());
		return ResponseEntity.ok(servicioServicesImpl.actualizar(servicio));
	}

	@GetMapping(value = "/servicios")
	public List<Servicio> obtenerServicio(Pageable pageable) {
		
		return servicioServicesImpl.obtenerPorPaginacion(pageable);
	}

	@GetMapping("/servicios/{idServicio}")
	public Servicio show(@PathVariable Long idServicio){
		
		return servicioServicesImpl.findByIdServicio(idServicio);
	}

	@GetMapping(value = "/allservicios")
	public List<Servicio> allServicios() {
		return servicioServicesImpl.obtener();
	}



}