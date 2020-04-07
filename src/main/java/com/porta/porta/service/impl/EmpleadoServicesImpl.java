package com.porta.porta.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import com.porta.porta.entity.Empleado;
import com.porta.porta.repository.EmpleadoRepository;
import com.porta.porta.service.PersonaServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("serviemp")
public class EmpleadoServicesImpl  extends PersonaServices<Empleado> {
    @Autowired
    @Qualifier("repositorioemp")
    private EmpleadoRepository repositorio;
    private static final Log logger = LogFactory.getLog(ClienteServicesImpl.class);

    @Override
    public boolean actualizar(Empleado generico) {
		logger.info("ACTUALIZANDO EMPLEADO");
		try {
			repositorio.save(generico);
			logger.info("EMPLEADO ACTUALIZADO");
			return true;
		}catch(Exception e) {
			logger.error("HUBO UN ERROR");
			return false;
		}
    }

    @Override
    public boolean crear(Empleado generico) {
		logger.info("CREANDO EMPLEADO");
		try {
			repositorio.save(generico);
			logger.info("EMPLEADO CREAD0");
			return true;
		}catch(Exception e) {
			logger.error("HUBO UN ERROR");
			return false;
		}
    }

    @Override
    public List<Empleado> obtener(Empleado generico) {
		logger.info("OBTENIENDO TODOS LOS ELEMENTOS");
		return repositorio.findAll();
    }


}