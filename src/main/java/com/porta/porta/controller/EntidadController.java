package com.porta.porta.controller;

import javax.validation.Valid;

import com.porta.porta.entity.Cliente;
import com.porta.porta.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entidad")
public class EntidadController {

    @Autowired
	@Qualifier("servicio")
    ClienteService service;
    
    @PutMapping("/cliente")
	public boolean agregarCliente(@RequestBody @Valid Cliente cliente) {
		return service.crear(cliente);
	}
    
}