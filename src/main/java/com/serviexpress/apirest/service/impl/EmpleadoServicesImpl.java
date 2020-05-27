package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jose4j.json.internal.json_simple.JSONObject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviexpress.apirest.entity.Empleado;
import com.serviexpress.apirest.entity.User;
import com.serviexpress.apirest.repository.EmpleadoRepository;
import com.serviexpress.apirest.repository.UserRepository;
import com.serviexpress.apirest.service.PersonaServices;

import org.springframework.data.domain.Pageable;
import com.serviexpress.apirest.util.Util;
import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.http.HttpStatus;

@Service("serviemp")
public class EmpleadoServicesImpl  extends PersonaServices<Empleado> {
    @Autowired
	@Qualifier("repositorioemp")
    private EmpleadoRepository repositorio;

	@Autowired
	UserRepository userRepository;
	private static final Log logger = LogFactory.getLog(EmpleadoServicesImpl.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError =new MensajeVO();
	String[] mensajes = new String[3];
	
    @Override
    public ResponseEntity<?> actualizar(Empleado generico) {
		logger.info("ACTUALIZANDO EMPLEADO");
		try {
			Empleado empleado = repositorio.findById(generico.getIdempleado())
					.orElseThrow(() -> new IllegalStateException("IdEmpleado no existe."));
					empleado = generico;
			repositorio.save(empleado);
			JSONObject lista = new JSONObject();
			lista.put("idempleado", empleado.getIdempleado());
			lista.put("rut", empleado.getRut());
			lista.put("name", empleado.getNombre());
			lista.put("apellido", empleado.getApellido());
			lista.put("fechaNacimiento", empleado.getFechaNacimiento());
			lista.put("telefono", empleado.getTelefono());
			logger.info("EMPLEADO ACTUALIZADO");
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
    }

    @Override
    public ResponseEntity<?> crear(Empleado generico) {
		logger.info("CREANDO EMPLEADO");
		String[] timestampError = Util.getCurrentTimeStamp().split(";");
		try {
			if (generico.getIdempleado() != null) {
				if (generico.getIdempleado() <= 0 || generico.getIdempleado() >= 0) {
					mensajes = Util.Codigos.MALPARAMETROS.split(";");
					mensajeError = new MensajeVO(timestampError[0], timestampError[1], mensajes[1],mensajes[0]);
					salida.setPeticion(mensajeError);
					return new ResponseEntity<ResultadoVO>(salida, HttpStatus.NOT_ACCEPTABLE);
				}
			}
			// Comprabar usuario si existe en la base de datos
			User user = userRepository.findById(generico.getIdusuario()).orElseThrow(() -> new IllegalStateException("IdUsuario no existe."));
			user.setActive(true);
			try {
				repositorio.save(generico);
				userRepository.save(user);
				JSONObject lista = new JSONObject();
				lista.put("idempleado", generico.getIdempleado());
				lista.put("rut", generico.getRut());
				lista.put("name", generico.getNombre());
				lista.put("apellido", generico.getApellido());
				lista.put("fechaNacimiento", generico.getFechaNacimiento());
				lista.put("telefono", generico.getTelefono());
				logger.info("EMPLEADO CREADO");
				return ResponseEntity.ok(lista);
			} catch (Exception e) {
				return new ResponseEntity<>("El usuario, ya cuenta con empleado registrado", HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
    }

	@Override
	public List<Empleado> obtenerPorPaginacion(Pageable pageable){
		return repositorio.findAll(pageable).getContent();
	}

	@Override
	public List<Empleado> obtener() {
		logger.info("OBTENIENDO TODOS LOS CLIENTES");
		return repositorio.findAll();
	}

}