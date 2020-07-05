package com.serviexpress.apirest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.payload.Encuesta;
import com.serviexpress.apirest.payload.RangoFecha;
import com.serviexpress.apirest.service.MyBatisService;
import com.serviexpress.apirest.service.impl.ReporteInServicesImpl;
import com.serviexpress.apirest.service.impl.SatifaccionServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/satifaccion")
public class SatifaccionController {


	@Autowired
	MyBatisService myBatisService;

	@Autowired
	SatifaccionServicesImpl satifaccionServicesImpl;

	@Autowired
	@Qualifier("servireportein")
	ReporteInServicesImpl reporteInServicesImpl;

	@PutMapping("/ingresarencuesta")
	public ResponseEntity<?> agregarEncuesta(@RequestBody @Valid final List<Encuesta> encuesta) {

		
		return ResponseEntity.ok(satifaccionServicesImpl.crearwithList(encuesta));
	}

	@PostMapping(value = "/encuesta")
	public ResponseEntity<?> obtener(@RequestBody @Valid final RangoFecha rangofecha) {
		System.out.println(rangofecha.getFechaini());

		System.out.println(rangofecha.getFechafin());
		// String date = fecha;
		// LocalDateTime localdatetime = LocalDateTime.parse(date);

		// System.out.println("origional date as string: " + date);
		// System.out.println("generated LocalDateTime: " + localdatetime);
		// //LocalTime lt = LocalTime.parse( fecha ) ;

		return ResponseEntity.ok(satifaccionServicesImpl.obtenerEncuesta(rangofecha.getFechaini(), rangofecha.getFechafin()));

	}

	@GetMapping(value = "/reportein")
	public ResponseEntity<?> obtenerReporteIn() {

		myBatisService.getEgreso();
		myBatisService.getIngreso();
		return ResponseEntity.ok(myBatisService.getIngreso());
	}
}