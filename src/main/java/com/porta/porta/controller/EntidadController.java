package com.porta.porta.controller;

import javax.validation.Valid;

import com.porta.porta.entity.Cliente;
import com.porta.porta.entity.Empleado;
import com.porta.porta.service.impl.ClienteServicesImpl;
import com.porta.porta.service.impl.EmpleadoServicesImpl;

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
	@Qualifier("servicli")
	ClienteServicesImpl servicli;
	
	@Autowired
	@Qualifier("serviemp")
    EmpleadoServicesImpl serviemp;
    
    @PutMapping("/cliente")
	public boolean agregarCliente(@RequestBody @Valid Cliente cliente) {
		return servicli.crear(cliente);
	}

	@PutMapping("/empleado")
	public boolean agregarEmpleado(@RequestBody @Valid Empleado empleado) {
		System.out.println(empleado.getNombre());
		return serviemp.crear(empleado);
	}
    
}