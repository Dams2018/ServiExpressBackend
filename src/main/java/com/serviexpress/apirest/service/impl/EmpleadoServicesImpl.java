package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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


@Service("serviemp")
public class EmpleadoServicesImpl  extends PersonaServices<Empleado> {
    @Autowired
	@Qualifier("repositorioemp")
    private EmpleadoRepository repositorio;

	@Autowired
	UserRepository userRepository;
	private static final Log logger = LogFactory.getLog(ClienteServicesImpl.class);
	
    @Override
    public ResponseEntity<?> actualizar(Empleado generico) {
		logger.info("ACTUALIZANDO EMPLEADO");
		try {
			repositorio.save(generico);
			logger.info("EMPLEADO ACTUALIZADO");
			return ResponseEntity.ok(generico);
		}catch(Exception e) {
			logger.error("HUBO UN ERROR");
			return ResponseEntity.ok(null);
		}
    }

    @Override
    public ResponseEntity<?> crear(Empleado generico) {
		logger.info("CREANDO EMPLEADO");
		try {

			User user = userRepository.findByUsername(Long.toString(generico.getId_usuario()))
			.orElseThrow(() -> new IllegalStateException("IdUsuario no existe."));
			repositorio.save(generico);
			user.setPassword("213213");
			logger.info("EMPLEADO CREAD0");
			return ResponseEntity.ok(user);
		}catch(Exception e) {
			logger.error("HUBO UN ERROR");
			return ResponseEntity.ok(null);
		}
    }

    @Override
    public List<Empleado> obtener() {
		logger.info("OBTENIENDO TODOS LOS ELEMENTOS");
		return repositorio.findAll();
    }


}