package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviexpress.apirest.entity.Producto;
import com.serviexpress.apirest.repository.ProductoRepository;
import com.serviexpress.apirest.service.UniversalServices;

import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.data.domain.Pageable;

@Service("serviProducto")
public class ProductoServicesImpl extends UniversalServices<Producto> {
	@Autowired
	@Qualifier("repositorioproducto")
	private ProductoRepository repositorio;
	private static final Log logger = LogFactory.getLog(UniversalServices.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError = new MensajeVO();
	String[] mensajes = new String[3];



	@Override
	public ResponseEntity<?> actualizar(Producto generico) {
		logger.info("ACTUALIZANDO PRODUCTO");
		try {
			Producto producto = repositorio.findById(generico.getIdproducto())
					.orElseThrow(() -> new IllegalStateException("Patente no existe."));
			producto = generico;
			repositorio.save(producto);
			logger.info("PRODUCTO ACTUALIZADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> crear(Producto generico) {
		logger.info("CREANDO PRODUCTO");
		try {
//FALTAN VALIDACIONES REVISAR DESPUES
			repositorio.save(generico);
			logger.info("PRODUCTO CREADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	

	@Override
	public List<Producto> obtener() {
		logger.info("OBTENIENDO TODOS LOS PRODUCTO");
		return repositorio.findAll();
	}

	@Override
	public List<Producto> obtenerPorPaginacion(Pageable pageable, Long id) {
		return repositorio.findAllBycategoria(pageable, id).getContent();
	}

	@Override
	public List<Producto> obtenerPorPaginacion(Pageable pageable){
		return repositorio.findAll(pageable).getContent();
	}
}