package com.serviexpress.apirest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.entity.Reserva;
import com.serviexpress.apirest.payload.ReservaResponse;
import com.serviexpress.apirest.service.impl.ReservaServicesImpl;

import org.jose4j.json.internal.json_simple.JSONArray;
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
	public ResponseEntity<?> agregarReserva(@RequestBody @Valid final Reserva reserva) {
		return ResponseEntity.ok(reservaServicesImpl.crear(reserva));

	}

	@PostMapping("/reserva")
	public ResponseEntity<?> actualizarReserva(@RequestBody @Valid final Reserva reserva) {
		return ResponseEntity.ok(reservaServicesImpl.actualizar(reserva));
	}


	//todas las reservas
	@GetMapping(value = "/reservas")
	public List<Reserva> obtenerReserva(final Pageable pageable) {
		
		return reservaServicesImpl.obtenerPorPaginacion(pageable);
	}

	//para lista de clientes
	@GetMapping(value = "/{idCliente}/cliente")
	public ResponseEntity<?> obtenerReservaCliente(final Pageable pageable, @PathVariable(value = "idCliente") final Long idCliente) {
		final List<Reserva> reserva = reservaServicesImpl.obtenerPorPaginacion(pageable, idCliente);
		ReservaResponse reservaResponse = new ReservaResponse();
		JSONArray array = new JSONArray(); 
		for (final Reserva reserva2 : reserva) {
			reservaResponse.setIdreserva(reserva2.getIdreserva());
			array.add(reservaResponse);
		}
		return ResponseEntity.ok(array);
	}


	//para lista por estado
	@GetMapping(value = "/{estado}/estado")
	public List<Reserva> obtenerReservaEstado(final Pageable pageable, @PathVariable(value = "estado") final Integer estado) {
		return reservaServicesImpl.obtenerPorPaginacion(pageable, estado);
	}





}