package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviexpress.apirest.entity.Cliente;
import com.serviexpress.apirest.entity.User;
import com.serviexpress.apirest.repository.ClienteRepository;
import com.serviexpress.apirest.repository.UserRepository;
import com.serviexpress.apirest.service.PersonaServices;
import com.serviexpress.apirest.util.Util;
import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.data.domain.Pageable;


@Service("servicli")
public class ClienteServicesImpl extends PersonaServices<Cliente> {
	@Autowired
	@Qualifier("repositoriocli")
	private ClienteRepository repositorio;
	private static final Log logger = LogFactory.getLog(ClienteServicesImpl.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError =new MensajeVO();
	String[] mensajes = new String[3];
	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseEntity<?> actualizar(Cliente generico) {
		logger.info("ACTUALIZANDO CLIENTE");
		try {
			Cliente cliente = repositorio.findById(generico.getIdcliente())
					.orElseThrow(() -> new IllegalStateException("IdCliente no existe."));
			cliente = generico;
			repositorio.save(cliente);
			logger.info("CLIENTE ACTUALIZADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> crear(Cliente generico) {
		logger.info("CREANDO CLIENTE " + generico.getId_usuario());
		String[] timestampError = Util.getCurrentTimeStamp().split(";");
		try {
			if (generico.getIdcliente() != null) {
				if (generico.getIdcliente() <= 0 || generico.getIdcliente() >= 0) {
					mensajes = Util.Codigos.MALPARAMETROS.split(";");
					mensajeError = new MensajeVO(timestampError[0], timestampError[1], mensajes[1],mensajes[0]);
					salida.setPeticion(mensajeError);
					return new ResponseEntity<ResultadoVO>(salida, HttpStatus.NOT_ACCEPTABLE);
				}
			}
			// Comprabar usuario si existe en la base de datos
			User user = userRepository.findById(generico.getId_usuario()).orElseThrow(() -> new IllegalStateException("IdUsuario no existe."));
			user.setActive(true);
			try {
				repositorio.save(generico);
				userRepository.save(user);
				logger.info("CLIENTE CREADA");
				return ResponseEntity.ok(generico);
			} catch (Exception e) {
				return new ResponseEntity<>("El usuario, ya cuenta con cliente registrado", HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@Override
	public List<Cliente> obtenerPorPaginacion(Pageable pageable){
		return repositorio.findAll(pageable).getContent();
	}

	@Override
	public List<Cliente> obtener() {
		logger.info("OBTENIENDO TODOS LOS CLIENTES");
		return repositorio.findAll();
	}

}