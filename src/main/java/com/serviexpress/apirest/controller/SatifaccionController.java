package com.serviexpress.apirest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.payload.Encuesta;
import com.serviexpress.apirest.service.impl.ReservaServicesImpl;
import com.serviexpress.apirest.service.impl.SatifaccionServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/satifaccion")
public class SatifaccionController {

	@Autowired
	SatifaccionServicesImpl satifaccionServicesImpl;
	
	@PutMapping("/ingresarencuesta")
	public ResponseEntity<?> agregarEncuesta(@RequestBody @Valid final List<Encuesta> encuesta) {

		return ResponseEntity.ok(satifaccionServicesImpl.crearwithList(encuesta));
	}


	@GetMapping(value = "/encuesta")
	public ResponseEntity<?> obtener() {
		return ResponseEntity.ok(satifaccionServicesImpl.obtenerEncuesta());

	}
}