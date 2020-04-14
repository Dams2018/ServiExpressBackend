// package com.porta.porta.service;

// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;

// import java.util.List;

// import com.porta.porta.entity.Cliente;
// import com.porta.porta.repository.ClienteRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.stereotype.Service;

// //@Service("servicio")
// public class ClienteService {
//     @Autowired
// 	@Qualifier("repositorio")
//     private ClienteRepository repositorio;
    
//     private static final Log logger = LogFactory.getLog(ClienteService.class);

//     public boolean crear(Cliente cliente){
// 		logger.info("CREANDO CLIENTE");
// 		try {
// 			repositorio.save(cliente);
// 			logger.info("CLIENTE CREADA");
// 			return true;
// 		}catch(Exception e) {
// 			logger.error("HUBO UN ERROR");
// 			return false;
// 		}
//     }
    
//     public boolean actualizar(Cliente cliente){
// 		logger.info("ACTUALIZANDO CLIENTE");
// 		try {
// 			repositorio.save(cliente);
// 			logger.info("CLIENTE ACTUALIZADA");
// 			return true;
// 		}catch(Exception e) {
// 			logger.error("HUBO UN ERROR");
// 			return false;
// 		}
//     }
    
//     public List<Cliente> obtener(){
// 		logger.info("OBTENIENDO TODOS LOS ELEMENTOS");
// 		return repositorio.findAll();
// 	}
// }