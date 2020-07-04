package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviexpress.apirest.entity.HistorialReserva;
import com.serviexpress.apirest.entity.Reserva;
import com.serviexpress.apirest.entity.User;
import com.serviexpress.apirest.repository.ReservaRepository;
import com.serviexpress.apirest.repository.UserRepository;
import com.serviexpress.apirest.service.UniversalServices;

import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.data.domain.Pageable;

@Service
public class ReservaServicesImpl extends UniversalServices<Reserva> {
	@Autowired
	private ReservaRepository repositorio;
	private static final Log logger = LogFactory.getLog(UniversalServices.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError = new MensajeVO();
	String[] mensajes = new String[3];
	@Autowired
	UserRepository userRepository;


	@Override
	public ResponseEntity<?> actualizar(Reserva generico) {
		logger.info("ACTUALIZANDO RESERVA");
		try {
			Reserva reserva = repositorio.findById(generico.getIdreserva())
					.orElseThrow(() -> new IllegalStateException("reserva no existe."));
			reserva = generico;
			repositorio.save(reserva);
			logger.info("RESERVA ACTUALIZADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> crear(Reserva generico) {
		logger.info("CREANDO RESERVA");
		try {
			repositorio.save(generico);
			//falta agregar el historial de reserva
			logger.info("RESERVA CREADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	

	@Override
	public List<Reserva> obtener() {
		logger.info("OBTENIENDO TODOS LAS RESERVA");
		return repositorio.findAll();
	}

	@Override
	public List<Reserva> obtenerPorPaginacion(Pageable pageable, Long id) {
		return repositorio.findAllByIdcliente(pageable, id).getContent();
	}

	@Override
	public List<Reserva> obtenerPorPaginacionReservaActiva(Pageable pageable, Long id, Boolean activo) {
		return repositorio.findAllByIdcliente(pageable, id).getContent();
	}

	@Override
	public List<Reserva> obtenerPorIdClienteAndEstado(Pageable pageable, Long id, Integer estado) {
		return repositorio.findAllByIdclienteAndEstado(pageable, id, estado).getContent();
	}

	@Override
	public List<Reserva> obtenerPorPaginacion(Pageable pageable, Integer estado) {
		return repositorio.findAllByEstado(pageable, estado).getContent();
	}


	@Override
	public List<Reserva> obtenerPorPaginacion(Pageable pageable){
		return repositorio.findAll(pageable).getContent();
	}

	@Override
	public List<Reserva> obtenerPorPaginacion(Date fini, Date ffin){
		System.out.println("lol"+fini);
		System.out.println("TEST "+repositorio.getAllBetweenFecha("01/15/2010","01/15/2021").toString());
		return repositorio.findAllByActivoAndFechaBetween(true,fini,ffin);
	}

	@Override

	public ResponseEntity<?> findByIdReserva(Long idReserva, int estado){

		Reserva reserva = repositorio.findById(idReserva)
		.orElseThrow(() -> new IllegalStateException("reserva no existe."));
		reserva.setActivo(true);
		reserva.setEstado(estado);
		
		repositorio.save(reserva);


		//ver despues
		// User user = userRepository.findById(reserva.getIdcliente())
		// .orElseThrow(() -> new IllegalStateException("UserName no existe."));
		
		// System.out.println(user.getEmail());
		logger.info("RESERVA ACTIVADA");
		return ResponseEntity.ok(reserva);

	}
}