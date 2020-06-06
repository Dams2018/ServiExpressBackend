package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviexpress.apirest.entity.category.*;
import com.serviexpress.apirest.exception.AppException;
import com.serviexpress.apirest.payload.ApiResponse;
import com.serviexpress.apirest.payload.request.MarcaRequest;
import com.serviexpress.apirest.payload.request.ProductoRequest;
import com.serviexpress.apirest.repository.MarcaRepository;
import com.serviexpress.apirest.repository.ProductoRepository;
import com.serviexpress.apirest.service.UniversalServices;

import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.data.domain.Pageable;

@Service("serviProducto")
public class ProductoServicesImpl extends UniversalServices<ProductoRequest> {
	@Autowired
	@Qualifier("repositorioproducto")
	private ProductoRepository repositorio;

	@Autowired
	private MarcaRepository marcaRepository;

	private static final Log logger = LogFactory.getLog(UniversalServices.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError = new MensajeVO();
	String[] mensajes = new String[3];



	@Override
	public ResponseEntity<?> actualizar(ProductoRequest generico) {
		logger.info("ACTUALIZANDO PRODUCTO");
		try {
			Producto producto = repositorio.findById(generico.getId())
					.orElseThrow(() -> new IllegalStateException("Patente no existe."));
			producto.setDescripcion(generico.getDescripcion());
			producto.setEstado(generico.getEstadoProducto());
			producto.setNombre(generico.getNombre());
			producto.setPrecio(generico.getPrecio());
			producto.setPrecioNeto(generico.getPrecioNeto());
			producto.setIva(generico.getIva());

			repositorio.save(producto);
			logger.info("PRODUCTO ACTUALIZADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> crear(ProductoRequest generico) {
		logger.info("CREANDO PRODUCTO");
		try {
			repositorio.save(transformProducto(generico));
			logger.info("PRODUCTO CREADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	

	@Override
	public List<ProductoRequest> obtener() {
		logger.info("OBTENIENDO TODOS LOS PRODUCTO");
		List<ProductoRequest> listProductos = new ArrayList<>();
		for (Producto producto : repositorio.findAll()) {
			listProductos.add(transformToRequest(producto));
		}
		return listProductos;
	}



	@Override
	public List<ProductoRequest> obtenerPorPaginacion(Pageable pageable){

		logger.info("OBTENIENDO TODOS LOS PRODUCTO");
		List<ProductoRequest> listProductos = new ArrayList<>();
		for (Producto producto : repositorio.findAll(pageable).getContent()) {
			listProductos.add(transformToRequest(producto));
		}
		return listProductos;
	}

	public ResponseEntity<?> crearMarca(MarcaRequest request){
		try {
			marcaRepository.save(new Marca(request.getNombre(),request.getDescripcion()));
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Marca Creada"),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Marca no pudo ser creada"),HttpStatus.OK);
		}
	}

	public ResponseEntity<?> actualizarMarca(MarcaRequest request){
		try {
			Marca marca = marcaRepository.findById(request.getId())
			.orElseThrow(()-> new AppException("No se encontro marca"));
			marca.setDescripcion(request.getDescripcion());
			marca.setNombre(request.getNombre());
			marcaRepository.save(marca);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Marca Actualizada"),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Marca no pudo ser actualizada"),HttpStatus.OK);
		}
	}

	public ResponseEntity<?> borrarMarca(MarcaRequest request){
		try {
			Marca marca = marcaRepository.findById(request.getId())
				.orElseThrow(()-> new AppException("No se encontro marca o ya esta eliminada"));
			marcaRepository.delete(marca);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Marca eliminada"),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Marca no pudo ser eliminada"),HttpStatus.OK);
		}
	}


	private Producto transformProducto(ProductoRequest request){
		Producto producto = new Producto();
		producto.setId(request.getId());
		producto.setNombre(request.getDescripcion());
		producto.setDescripcion(request.getDescripcion());
		producto.setMarca(new Marca());
		producto.setEstado(request.getEstadoProducto());
		producto.setPrecio(request.getPrecio());
		producto.setPrecioNeto(request.getPrecioNeto());
		producto.setIva(request.getIva());
		producto.setDetalleCategoria(new DetalleCategoria());

		return producto;
	}

	private ProductoRequest transformToRequest(Producto producto){
		ProductoRequest request = new ProductoRequest();
		request.setId(producto.getId());
		request.setNombre(producto.getNombre());
		request.setDescripcion(producto.getDescripcion());
		request.setEstadoProducto(producto.getEstado());
		request.setPrecio(producto.getPrecio());
		request.setPrecioNeto(producto.getPrecioNeto());
		request.setIva(producto.getIva());
		request.setIdMarca(producto.getMarca().getId());

		return request;
	}

}