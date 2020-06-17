package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviexpress.apirest.entity.category.*;
import com.serviexpress.apirest.exception.AppException;
import com.serviexpress.apirest.payload.ApiResponse;
import com.serviexpress.apirest.payload.request.GenericRequest;
import com.serviexpress.apirest.payload.request.MarcaRequest;
import com.serviexpress.apirest.payload.request.ProductoRequest;
import com.serviexpress.apirest.repository.CategoriaDetalleRepository;
import com.serviexpress.apirest.repository.MarcaRepository;
import com.serviexpress.apirest.repository.ProductoRepository;
import com.serviexpress.apirest.repository.StockRepository;
import com.serviexpress.apirest.service.UniversalServices;

import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.data.domain.Pageable;

	/**
	 * Clase que implementa proceso relacionado a productos
	 * @author Marco Astorga
	 * 
	 */
@Service("serviProducto")
public class ProductoServicesImpl extends UniversalServices<ProductoRequest> {
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private CategoriaDetalleRepository subCategoriaReppository;

	@Autowired
	private StockRepository stockRepository;

	private static final Log logger = LogFactory.getLog(UniversalServices.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError = new MensajeVO();
	String[] mensajes = new String[3];



	/**
	 * Metodo que actualiza informacion de un producto 
	 * @author Marco Astorga
	 * @return respuesta de proceso
	 * 
	 */
	@Override
	public ResponseEntity<?> actualizar(ProductoRequest generico) {
		logger.info("ACTUALIZANDO PRODUCTO");
		try {
			Producto producto = productoRepository.findById(generico.getId())
					.orElseThrow(() -> new IllegalStateException("Patente no existe."));
			producto.setDescripcion(generico.getDescripcion());
			producto.setEstado(generico.getEstadoProducto());
			producto.setNombre(generico.getNombre());
			producto.setPrecio(generico.getPrecio());
			producto.setPrecioNeto(generico.getPrecioNeto());
			producto.setIva(generico.getIva());

			productoRepository.save(producto);
			logger.info("PRODUCTO ACTUALIZADO");
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Producto actualizado"), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Producto no pudo ser actualizado"), HttpStatus.CONFLICT);
		}
	}
	
		/**
	 * Metodo que crea un producto 
	 * @author Marco Astorga
	 * @return respuesta de proceso
	 * 
	 */
	@Transactional
	@Override
	public ResponseEntity<?> crear(ProductoRequest generico) {
		logger.info("CREANDO PRODUCTO");
		try {
			Producto productoNuevo = transformProducto(generico);
			Marca marca = marcaRepository.findById(generico.getIdMarca()).orElseThrow(() -> new AppException("No existe Marca"));
			DetalleCategoria detalle = subCategoriaReppository.findById(generico.getSubCategoria()).orElseThrow(() -> new AppException("No existe Sub-Categoria"));
			productoNuevo.setMarca(marca);
			productoNuevo.setDetalleCategoria(detalle);
			productoRepository.save(productoNuevo);
			Date fechaActualizacion = new Date();
			stockRepository.save(new Stock(productoNuevo, generico.getCantidad(), fechaActualizacion));
			logger.info("PRODUCTO CREADO");
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Producto Creado"), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	/**
	 * Metodo que obtiene todos los productos existentes.
	 * @author Marco Astorga
	 * @return Listado total de productos
	 * 
	 */
	@Override
	public List<ProductoRequest> obtener() {
		logger.info("OBTENIENDO TODOS LOS PRODUCTO");
		List<ProductoRequest> listProductos = new ArrayList<>();
		for (Producto producto : productoRepository.findAll()) {
			listProductos.add(transformToRequest(producto));
		}
		return listProductos;
	}

		/**
	 * Metodo que obtiene listado definido por cantidad
	 * @author Marco Astorga
	 * @return Obtiene listado con X cantidad 
	 * 
	 */
	@Override
	public List<ProductoRequest> obtenerPorPaginacion(Pageable pageable){

		logger.info("OBTENIENDO TODOS LOS PRODUCTO");
		List<ProductoRequest> listProductos = new ArrayList<>();
		for (Producto producto : productoRepository.findAll(pageable).getContent()) {
			listProductos.add(transformToRequest(producto));
		}
		return listProductos;
	}

	/**
	 * Metodo que genera una marca nueva
	 * @author Marco Astorga
	 * @return respuesta de proceso
	 * 
	 */
	public ResponseEntity<?> crearMarca(MarcaRequest request){
		try {
			marcaRepository.save(new Marca(request.getNombre(),request.getDescripcion()));
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Marca Creada"),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Marca no pudo ser creada"),HttpStatus.OK);
		}
	}

		/**
	 * Metodo que actualiza informacion de una marca existente
	 * @author Marco Astorga
	 * @return respuesta de proceso
	 * 
	 */
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

		/**
	 * Metodo que elimina marca
	 * @author Marco Astorga
	 * @return respuesta de proceso
	 * 
	 */
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


	public ResponseEntity<?> actualizarStock(@Valid GenericRequest request){

		try {
			Producto producto = productoRepository.findById(request.getProducto().getId())
			.orElseThrow(() -> new IllegalStateException("Patente no existe."));
			Stock stock = stockRepository.findByProducto(producto).orElseThrow(() -> new AppException("No se encontro lo que buscabas"));
			stock.setCantidad(request.getProducto().getCantidad());
			stockRepository.save(stock);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Se actualizo el stock del producto: "+producto.getNombre()),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Hubo un error en procesar su solicitud"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	


	/**
	 * Transforma producto front end por backend
	 * @author Marco Astorga
	 * @return Producto
	 * 
	 */
	private Producto transformProducto(ProductoRequest request){
		Producto producto = new Producto();
		producto.setId(request.getId());
		producto.setNombre(request.getNombre());
		producto.setDescripcion(request.getDescripcion());
		producto.setMarca(new Marca());
		producto.setEstado(request.getEstadoProducto());
		producto.setPrecio(request.getPrecio());
		producto.setPrecioNeto(request.getPrecioNeto());
		producto.setIva(request.getIva());
		producto.setDetalleCategoria(new DetalleCategoria());

		return producto;
	}

	/**
	 * Transforma de un producto entity a un request
	 * @author Marco Astorga
	 * @return Producto frontend
	 * 
	 */
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