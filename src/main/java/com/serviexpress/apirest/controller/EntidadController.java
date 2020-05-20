package com.serviexpress.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviexpress.apirest.entity.Cliente;
import com.serviexpress.apirest.entity.Empleado;
import com.serviexpress.apirest.service.impl.ClienteServicesImpl;
import com.serviexpress.apirest.service.impl.EmpleadoServicesImpl;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/entidad")
public class EntidadController {

	@Autowired
	@Qualifier("servicli")
	ClienteServicesImpl servicli;

	@Autowired
	@Qualifier("serviemp")
	EmpleadoServicesImpl serviemp;

	// Cliente
	@PutMapping("/cliente")
	public ResponseEntity<?> agregarCliente(@RequestBody @Valid Cliente cliente) {
		return ResponseEntity.ok(servicli.crear(cliente));

	}

	@PostMapping("/cliente")
	public ResponseEntity<?> actualizarCliente(@RequestBody @Valid Cliente cliente) {
		return ResponseEntity.ok(servicli.actualizar(cliente));
	}

	@GetMapping(value = "/clientes")
	public List<Cliente> obtenerClientes(Pageable pageable) {
		//System.out.println("empleado.getNombre()");
		return servicli.obtenerPorPaginacion(pageable);
	}

	@GetMapping(value = "/allclientes")
	public List<Cliente> allClientes() {
		return servicli.obtener();
	}

	// Empleado
	@PutMapping("/empleado")
	public ResponseEntity<?> agregarEmpleado(@RequestBody @Valid Empleado empleado) {
		return ResponseEntity.ok(serviemp.crear(empleado));
	}

	@PostMapping("/empleado")
	public ResponseEntity<?> actualizarEmpleado(@RequestBody @Valid Empleado empleado) {
		return ResponseEntity.ok(serviemp.actualizar(empleado));
	}

	@GetMapping(value = "/empleados")
	public List<Empleado> obtenerEmpleado(Pageable pageable) {
		return serviemp.obtenerPorPaginacion(pageable);
	}

	@GetMapping(value = "/allempleados")
	public List<Empleado> allEmpleado() {
		return serviemp.obtener();
	}

}