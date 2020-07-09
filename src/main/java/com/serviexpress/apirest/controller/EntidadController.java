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
import com.serviexpress.apirest.payload.response.EntidadDTO;
import com.serviexpress.apirest.payload.response.EntidadEmpDTO;
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
	public ResponseEntity<?> agregarCliente(@RequestBody @Valid EntidadDTO entidadDTO) {
		Cliente cliente = new Cliente();
		cliente.setNombre(entidadDTO.getNombre());
		cliente.setApellido(entidadDTO.getApellido());
		cliente.setFechaNacimiento(entidadDTO.getFechaNacimiento());
		cliente.setIdusuario(entidadDTO.getIdusuario());
		cliente.setTelefono(entidadDTO.getTelefono());
		cliente.setRut(entidadDTO.getRut());
		return ResponseEntity.ok(servicli.crear(cliente));

	}

	@PostMapping("/cliente")
	public ResponseEntity<?> actualizarCliente(@RequestBody @Valid EntidadDTO entidadDTO) {
		Cliente cliente = new Cliente();
		cliente.setNombre(entidadDTO.getNombre());
		cliente.setApellido(entidadDTO.getApellido());
		cliente.setFechaNacimiento(entidadDTO.getFechaNacimiento());
		cliente.setIdusuario(entidadDTO.getIdusuario());
		cliente.setIdcliente(entidadDTO.getIdcliente());
		cliente.setTelefono(entidadDTO.getTelefono());
		cliente.setRut(entidadDTO.getRut());
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
	public ResponseEntity<?> agregarEmpleado(@RequestBody @Valid EntidadEmpDTO entidadEmpDTO) {
		Empleado empleado = new Empleado();
		empleado.setNombre(entidadEmpDTO.getNombre());
		empleado.setApellido(entidadEmpDTO.getApellido());
		empleado.setFechaNacimiento(entidadEmpDTO.getFechaNacimiento());
		empleado.setIdusuario(entidadEmpDTO.getIdusuario());
		empleado.setTelefono(entidadEmpDTO.getTelefono());
		empleado.setRut(entidadEmpDTO.getRut());
		return ResponseEntity.ok(serviemp.crear(empleado));
	}

	@PostMapping("/empleado")
	public ResponseEntity<?> actualizarEmpleado(@RequestBody @Valid EntidadEmpDTO entidadEmpDTO) {
		Empleado empleado = new Empleado();
		empleado.setNombre(entidadEmpDTO.getNombre());
		empleado.setApellido(entidadEmpDTO.getApellido());
		empleado.setFechaNacimiento(entidadEmpDTO.getFechaNacimiento());
		empleado.setIdusuario(entidadEmpDTO.getIdusuario());
		empleado.setTelefono(entidadEmpDTO.getTelefono());
		empleado.setRut(entidadEmpDTO.getRut());
		empleado.setIdempleado(entidadEmpDTO.getIdempleado());
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