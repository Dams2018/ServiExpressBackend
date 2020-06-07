package com.serviexpress.apirest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.entity.Producto;
import com.serviexpress.apirest.entity.Reserva;
import com.serviexpress.apirest.entity.Servicio;
import com.serviexpress.apirest.entity.Vehiculo;
import com.serviexpress.apirest.payload.ReservaResponse;
import com.serviexpress.apirest.service.impl.ReservaServicesImpl;

import org.jose4j.json.internal.json_simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviexpress.apirest.repository.ProductoRepository;
import com.serviexpress.apirest.repository.ServicioRepository;
import com.serviexpress.apirest.repository.VehiculoRepository;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

	@Autowired
	ReservaServicesImpl reservaServicesImpl;

	@Autowired
	@Qualifier("repositorioproducto")
	private ProductoRepository productoRepository;

	@Autowired
	@Qualifier("repositorioservicio")
	private ServicioRepository servicioRepository;

	@Autowired
	@Qualifier("repositoriovehiculo")
	private VehiculoRepository vehiculoRepository;

	// Cliente
	@PutMapping("/reserva")
	public ResponseEntity<?> agregarReserva(@RequestBody @Valid final Reserva reserva) {
		return ResponseEntity.ok(reservaServicesImpl.crear(reserva));

	}

	@PostMapping("/reserva")
	public ResponseEntity<?> actualizarReserva(@RequestBody @Valid final Reserva reserva) {
		return ResponseEntity.ok(reservaServicesImpl.actualizar(reserva));
	}

	// todas las reservas
	@GetMapping(value = "/reservas")
	public ResponseEntity<?> obtenerReserva(final Pageable pageable) {
		List<Reserva> reserva = reservaServicesImpl.obtenerPorPaginacion(pageable);
		JSONArray array = new JSONArray();

		for (Reserva reserva2 : reserva) {
			ReservaResponse reservaResponse = new ReservaResponse();
			reservaResponse.setIdreserva(reserva2.getIdreserva());

			long num = Long.parseLong(reserva2.getServicios());
			Servicio servicio = servicioRepository.findById(num).orElseThrow(() -> new IllegalStateException("Servicio no existe."));
			reservaResponse.setServicios(servicio.getNombre());

			long num2 = Long.parseLong(reserva2.getProductos());
			Producto producto = productoRepository.findById(num2).orElseThrow(() -> new IllegalStateException("Patente no existe."));
			reservaResponse.setProductos(producto.getNombre());

			reservaResponse.setHorareserva(reserva2.getHorareserva());
			reservaResponse.setFechareserva(reserva2.getFechareserva());


			Vehiculo vehiculo2 = vehiculoRepository.findById(reserva2.getIdvehiculo())
			.orElseThrow(() -> new IllegalStateException("Patente no existe."));
			
			reservaResponse.setVeichulo(vehiculo2.getTipovehiculo());
			reservaResponse.setMarca(vehiculo2.getMarca());
			reservaResponse.setPatente(vehiculo2.getPatente());
			array.add(reservaResponse);

		}

		return ResponseEntity.ok(array);
	}

	// para lista de clientes
	@GetMapping(value = "/{idCliente}/cliente")
	public ResponseEntity<?> obtenerReservaCliente(final Pageable pageable,
			@PathVariable(value = "idCliente") final Long idCliente) {
		List<Reserva> reserva = reservaServicesImpl.obtenerPorPaginacion(pageable, idCliente);
		JSONArray array = new JSONArray();

		for (Reserva reserva2 : reserva) {
			ReservaResponse reservaResponse = new ReservaResponse();
			reservaResponse.setIdreserva(reserva2.getIdreserva());

			long num = Long.parseLong(reserva2.getServicios());
			Servicio servicio = servicioRepository.findById(num).orElseThrow(() -> new IllegalStateException("Servicio no existe."));
			reservaResponse.setServicios(servicio.getNombre());

			long num2 = Long.parseLong(reserva2.getProductos());
			Producto producto = productoRepository.findById(num2).orElseThrow(() -> new IllegalStateException("Patente no existe."));
			reservaResponse.setProductos(producto.getNombre());

			reservaResponse.setHorareserva(reserva2.getHorareserva());
			reservaResponse.setFechareserva(reserva2.getFechareserva());


			Vehiculo vehiculo2 = vehiculoRepository.findById(reserva2.getIdvehiculo())
			.orElseThrow(() -> new IllegalStateException("Patente no existe."));
			
			reservaResponse.setVeichulo(vehiculo2.getTipovehiculo());
			reservaResponse.setMarca(vehiculo2.getMarca());
			reservaResponse.setPatente(vehiculo2.getPatente());
			array.add(reservaResponse);

		}

		return ResponseEntity.ok(array);

	}

	// para lista por estado
	@GetMapping(value = "/{estado}/estado")
	public List<Reserva> obtenerReservaEstado(final Pageable pageable,
			@PathVariable(value = "estado") final Integer estado) {
		return reservaServicesImpl.obtenerPorPaginacion(pageable, estado);
	}

}