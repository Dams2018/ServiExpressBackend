package com.porta.porta.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import com.porta.porta.entity.Cliente;
import com.porta.porta.repository.ClienteRepository;
import com.porta.porta.service.PersonaServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("servicli")
public class ClienteServicesImpl extends PersonaServices<Cliente> {
    @Autowired
    @Qualifier("repositoriocli")
    private ClienteRepository repositorio;
    private static final Log logger = LogFactory.getLog(ClienteServicesImpl.class);

    @Override
    public boolean actualizar(Cliente generico) {
		logger.info("ACTUALIZANDO CLIENTE");
		try {
			repositorio.save(generico);
			logger.info("CLIENTE ACTUALIZADA");
			return true;
		}catch(Exception e) {
			logger.error("HUBO UN ERROR");
			return false;
		}
    }

    @Override
    public boolean crear(Cliente generico) {
		logger.info("CREANDO CLIENTE");
		try {
			repositorio.save(generico);
			logger.info("CLIENTE CREADA");
			return true;
		}catch(Exception e) {
			logger.error("HUBO UN ERROR");
			return false;
		}
    }

    @Override
    public List<Cliente> obtener(Cliente generico) {
		logger.info("OBTENIENDO TODOS LOS ELEMENTOS");
		return repositorio.findAll();
    }


}