package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.serviexpress.apirest.entity.Reserva;
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

		Calendar c = Calendar.getInstance(); 
        c.setTime(generico.getFecha()); 
		generico.setFecha(c.getTime());
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
	public List<Reserva> obtenerPorDay(Pageable pageable){
		// Date date = Calendar.getInstance().getTime();   
		// DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
		// String strDate = dateFormat.format(date);  
		Date date1 = new Date();
        Calendar c1 = Calendar.getInstance(); 
		c1.setTime(date1); 
		c1.add(Calendar.DATE, -1);
		date1 = c1.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = dateFormat.format(date1); 


		Date date2 = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(date2); 
		date2 = c.getTime();
		DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
		String strDate2 = dateFormat2.format(date2); 
		System.out.println(strDate+" "+strDate2); 
		return repositorio.getAllDayFecha(pageable, strDate,strDate2).getContent();
	}

	@Override
	public List<Reserva> obtenerPorMonth(Pageable pageable){
		Date date = Calendar.getInstance().getTime();   
		DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");  
		String strDate = dateFormat.format(date);  
		Date date2 = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(date2); 
        c.add(Calendar.MONTH, 1);
		date2 = c.getTime();
		DateFormat dateFormat2 = new SimpleDateFormat("MM/yyyy");
		String strDate2 = dateFormat2.format(date2);  
		System.out.println(strDate+" "+strDate2);
		return repositorio.getAllMonthFecha(pageable, strDate,strDate2).getContent();
	}

	@Override

	public ResponseEntity<?> findByIdReserva(Long idReserva, int estado){

		Reserva reserva = repositorio.findById(idReserva)
		.orElseThrow(() -> new IllegalStateException("reserva no existe."));
		reserva.setActivo(true);
		System.out.println(estado);
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