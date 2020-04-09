package com.porta.porta.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

import com.porta.porta.entity.Cliente;
import com.porta.porta.entity.User;
import com.porta.porta.repository.ClienteRepository;
import com.porta.porta.repository.UserRepository;
import com.porta.porta.service.PersonaServices;
import com.porta.porta.util.Util;
import com.porta.porta.vo.MensajeVO;
import com.porta.porta.vo.ResultadoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("servicli")
public class ClienteServicesImpl extends PersonaServices<Cliente> {
	@Autowired
	@Qualifier("repositoriocli")
	private ClienteRepository repositorio;
	private static final Log logger = LogFactory.getLog(ClienteServicesImpl.class);
	ResultadoVO salida = new ResultadoVO();
	String[] mensajes = new String[3];
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean actualizar(Cliente generico) {
		logger.info("ACTUALIZANDO CLIENTE");
		try {
			Cliente cliente = repositorio.findById(generico.getIdcliente())
					.orElseThrow(() -> new IllegalStateException("IdUsuario no existe."));
			cliente = generico;
			repositorio.save(cliente);
			logger.info("CLIENTE ACTUALIZADO");
			return true;
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return false;
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
					MensajeVO mensajeError = new MensajeVO(timestampError[0], timestampError[1], mensajes[1],
							mensajes[0]);
					salida.setPeticion(mensajeError);
					return new ResponseEntity<ResultadoVO>(salida, HttpStatus.NOT_ACCEPTABLE);
				}
			}

			// Comprabar usuario si existe en la base de datos
			User user = userRepository.findById(generico.getId_usuario())
					.orElseThrow(() -> new IllegalStateException("IdUsuario no existe."));
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
			logger.error("HUBO UN ERROR " + e.getMessage());
			mensajes = Util.Codigos.PASSWORDSNOCOINCIDENTES.split(";");
			MensajeVO mensajeError = new MensajeVO(timestampError[0], timestampError[1], mensajes[1], mensajes[0]);
			salida.setPeticion(mensajeError);
			e.printStackTrace();
			return new ResponseEntity<ResultadoVO>(salida, HttpStatus.CONFLICT);

		}

	}

	@Override
	public List<Cliente> obtener(Cliente generico) {
		logger.info("OBTENIENDO TODOS LOS ELEMENTOS");
		return repositorio.findAll();
	}

}