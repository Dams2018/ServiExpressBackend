package com.serviexpress.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.entity.Reserva;
import com.serviexpress.apirest.service.impl.ReservaServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/reserva")
public class ReservaController {

	@Autowired
	ReservaServicesImpl reservaServicesImpl;


	// Cliente
	@PutMapping("/reserva")
	public ResponseEntity<?> agregarReserva(@RequestBody @Valid Reserva reserva) {
		return ResponseEntity.ok(reservaServicesImpl.crear(reserva));

	}

	@PostMapping("/reserva")
	public ResponseEntity<?> actualizarReserva(@RequestBody @Valid Reserva reserva) {
		return ResponseEntity.ok(reservaServicesImpl.actualizar(reserva));
	}


	//todas las reservas
	@GetMapping(value = "/reservas")
	public List<Reserva> obtenerReserva(Pageable pageable) {
		
		return reservaServicesImpl.obtenerPorPaginacion(pageable);
	}

	//para lista de clientes
	@GetMapping(value = "/{idCliente}/cliente")
	public List<Reserva> obtenerReservaCliente(Pageable pageable, @PathVariable(value = "idCliente") Long idCliente) {
		return reservaServicesImpl.obtenerPorPaginacion(pageable, idCliente);
	}


	//para lista por estado
	@GetMapping(value = "/{estado}/estado")
	public List<Reserva> obtenerReservaEstado(Pageable pageable, @PathVariable(value = "estado") Integer estado) {
		return reservaServicesImpl.obtenerPorPaginacion(pageable, estado);
	}





}