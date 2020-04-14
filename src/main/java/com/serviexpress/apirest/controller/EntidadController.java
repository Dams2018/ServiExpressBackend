package com.porta.porta.controller;

import java.util.List;

import javax.validation.Valid;

import com.porta.porta.entity.Cliente;
import com.porta.porta.entity.Empleado;
import com.porta.porta.service.impl.ClienteServicesImpl;
import com.porta.porta.service.impl.EmpleadoServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/entidad")
public class EntidadController {

    @Autowired
	@Qualifier("servicli")
	ClienteServicesImpl servicli;
	
	@Autowired
	@Qualifier("serviemp")
    EmpleadoServicesImpl serviemp;
	
	
	//obtener id user para la creacion de cliente
    @PutMapping("/cliente")
	public ResponseEntity<?> agregarCliente(@RequestBody @Valid Cliente cliente) {
		return ResponseEntity.ok(servicli.crear(cliente));
		// return servicli.crear(cliente);
	}


	@PostMapping("/cliente")
	public ResponseEntity<?> actualizarCliente(@RequestBody @Valid Cliente cliente){
		return ResponseEntity.ok(servicli.actualizar(cliente));
	}

	@GetMapping(value="/clientes")
	public List<Cliente> obtenerClientes(Pageable pageable){
		System.out.println("empleado.getNombre()");
		return servicli.obtenerPorPaginacion(pageable);
	}

	
	@GetMapping(value="/allclientes")
	public List<Cliente> allclientes(){
		System.out.println("empleado.getNombre()");
		return servicli.obtener();
	}


	@PutMapping("/empleado")
	public ResponseEntity<?>  agregarEmpleado(@RequestBody @Valid Empleado empleado) {
		
		return ResponseEntity.ok(serviemp.crear(empleado));
	}

}