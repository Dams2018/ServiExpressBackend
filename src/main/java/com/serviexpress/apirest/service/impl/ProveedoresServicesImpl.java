package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviexpress.apirest.entity.Proveedor;
import com.serviexpress.apirest.repository.ProveedorRepository;
import com.serviexpress.apirest.service.UniversalServices;

import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;
import org.springframework.data.domain.Pageable;


@Service("serviProveedor")
public class ProveedoresServicesImpl extends UniversalServices<Proveedor> {
	@Autowired
	@Qualifier("repositorioproveedor")
	private ProveedorRepository repositorio;
	private static final Log logger = LogFactory.getLog(UniversalServices.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError = new MensajeVO();
	String[] mensajes = new String[3];



	@Override
	public ResponseEntity<?> actualizar(Proveedor generico) {
		logger.info("ACTUALIZANDO CATEGORIA");
		try {
			Proveedor proveedor = repositorio.findById(generico.getIdproveedor())
					.orElseThrow(() -> new IllegalStateException("El proveedor no existe."));
			proveedor = generico;
			repositorio.save(proveedor);
			logger.info("PROVEEDOR ACTUALIZADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> crear(Proveedor generico) {
		logger.info("CREANDO Proveedor");
		try {
//FALTAN VALIDACIONES REVISAR DESPUES
			repositorio.save(generico);
			logger.info("PROVEEDOR CREADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}


	@Override
	public Proveedor findByIdProveedor(Long idProveedor) {
		logger.info("OBTENIENDO PROVEEDOR POR ID");
		return repositorio.findById(idProveedor).orElse(null);
	}
	

	@Override
	public List<Proveedor> obtener() {
		logger.info("OBTENIENDO TODOS LOS Proveedores");
		return repositorio.findAll();
	}

	@Override
	public List<Proveedor> obtenerPorPaginacion(Pageable pageable){
		return repositorio.findAll(pageable).getContent();
	}

}