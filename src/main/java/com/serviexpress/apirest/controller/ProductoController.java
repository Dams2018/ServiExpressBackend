package com.serviexpress.apirest.controller;

import java.util.List;

import javax.validation.Valid;


import com.serviexpress.apirest.entity.category.*;
import com.serviexpress.apirest.payload.ApiResponse;
import com.serviexpress.apirest.payload.request.GenericRequest;
import com.serviexpress.apirest.payload.request.MarcaRequest;
import com.serviexpress.apirest.payload.request.ProductoRequest;
import com.serviexpress.apirest.service.impl.ProductoServicesImpl;
import com.serviexpress.apirest.util.ConstantesTipoConsulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

	@Autowired
	@Qualifier("serviProducto")
	ProductoServicesImpl productoServicesImpl;




	@PutMapping("/manageProduct")
	public ResponseEntity<?> agregarCategoria(@RequestBody @Valid GenericRequest request) {
		ResponseEntity response = new ResponseEntity<ApiResponse>(new ApiResponse(false, "No existe proceso solicitado"), HttpStatus.BAD_REQUEST);
		try {
			if (request.getTipoConsulta()==ConstantesTipoConsulta.PRODUCTO) {
				response = productoServicesImpl.crear(request.getProducto());
	
			} else if (request.getTipoConsulta()==ConstantesTipoConsulta.MARCA) {
				response = productoServicesImpl.crearMarca(request.getMarca());
			}
			return response;
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "No se pudo procesar la solicitud"),HttpStatus.BAD_REQUEST);
		}

	
	}

	@PostMapping("/manageProduct")
	public ResponseEntity<?> actualizarCategoria(@RequestBody @Valid GenericRequest request) {

		ResponseEntity response = new ResponseEntity<ApiResponse>(new ApiResponse(false, "No existe proceso solicitado"), HttpStatus.BAD_REQUEST);
		try {
			if (request.getTipoConsulta()==ConstantesTipoConsulta.PRODUCTO) {
				response = productoServicesImpl.actualizar(request.getProducto());
	
			} else if (request.getTipoConsulta()==ConstantesTipoConsulta.MARCA) {
				response = productoServicesImpl.actualizarMarca(request.getMarca());
			} else if(request.getTipoConsulta()==ConstantesTipoConsulta.STOCK){
				response = productoServicesImpl.actualizarStock(request);
			}
			return response;
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "No se pudo procesar la solicitud"),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{idCategoria}")
	public ResponseEntity<?> obtenerProductoByCategoria(Pageable pageable, @PathVariable(value = "idCategoria") Long idCategoria) {
		try {
			return new ResponseEntity<List<ProductoRequest>>(productoServicesImpl.obtenerPorPaginacion(pageable,idCategoria),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "No se pudo obtener el listado"),HttpStatus.BAD_REQUEST);
		}
		

	}



}