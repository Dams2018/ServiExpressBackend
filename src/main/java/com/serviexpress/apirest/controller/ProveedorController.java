package com.serviexpress.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviexpress.apirest.entity.Proveedor;
import com.serviexpress.apirest.payload.response.ProveedorDTO;
import com.serviexpress.apirest.service.impl.ProveedoresServicesImpl;



@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

	@Autowired
	@Qualifier("serviProveedor")
	ProveedoresServicesImpl proveedorServicesImpl;


	@PutMapping("/proveedor")
	public ResponseEntity<?> agregarProveedor(@RequestBody @Valid ProveedorDTO proveedorDTO) {
		Proveedor proveedor= new Proveedor();
		proveedor.setActive(proveedorDTO.isActive());
		proveedor.setApellido(proveedorDTO.getApellido());
		proveedor.setCorreo(proveedorDTO.getCorreo());
		proveedor.setEmpresa(proveedorDTO.getEmpresa());
		proveedor.setNombre(proveedorDTO.getNombre());
		proveedor.setProducto(proveedorDTO.getProducto());
		proveedor.setRut(proveedorDTO.getProducto());
		proveedor.setTelefono(proveedorDTO.getTelefono());
		return ResponseEntity.ok(proveedorServicesImpl.crear(proveedor));

	}

	@PostMapping("/proveedor")
	public ResponseEntity<?> actualizarProveedor(@RequestBody @Valid ProveedorDTO proveedorDTO) {
		Proveedor proveedor= new Proveedor();
		proveedor.setActive(proveedorDTO.isActive());
		proveedor.setApellido(proveedorDTO.getApellido());
		proveedor.setCorreo(proveedorDTO.getCorreo());
		proveedor.setEmpresa(proveedorDTO.getEmpresa());
		proveedor.setNombre(proveedorDTO.getNombre());
		proveedor.setProducto(proveedorDTO.getProducto());
		proveedor.setRut(proveedorDTO.getProducto());
		proveedor.setTelefono(proveedorDTO.getTelefono());
		proveedor.setIdproveedor(proveedorDTO.getIdproveedor());
		return ResponseEntity.ok(proveedorServicesImpl.actualizar(proveedor));
	}

	@GetMapping("/proveedor/{idProveedor}")
	public Proveedor show(@PathVariable Long idProveedor){
		
		return proveedorServicesImpl.findByIdProveedor(idProveedor);
	}

	@GetMapping(value = "/allproveedores")
	public List<Proveedor> allProveedores() {
		return proveedorServicesImpl.obtener();
	}

}