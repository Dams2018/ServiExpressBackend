package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviexpress.apirest.entity.Vehiculo;
import com.serviexpress.apirest.repository.ClienteRepository;
import com.serviexpress.apirest.repository.VehiculoRepository;
import com.serviexpress.apirest.service.VehiculoServices;
import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.data.domain.Pageable;

@Service("serviVehiculo")
public class VehiculoServicesImpl extends VehiculoServices<Vehiculo> {
	@Autowired
	@Qualifier("repositoriovehiculo")
	private VehiculoRepository repositorio;
	private static final Log logger = LogFactory.getLog(VehiculoServicesImpl.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError = new MensajeVO();
	String[] mensajes = new String[3];
	@Autowired
	ClienteRepository clienteRepository;

	// SIRVE PARA ACTULIZAR O DESCATIVAR EL VEICHUILO SIEMPRE CUENDO SE ACTULISE EL
	// ACTIVO DEBE IR TRUE Y SI SE VA BORRAR EN LA VISTA MANDAR UN FALSE EN ACTIVO
	@Override
	public ResponseEntity<?> actualizar(Vehiculo generico) {
		logger.info("ACTUALIZANDO VEHÍCULO CLIENTE");
		try {
			Vehiculo vehiculo = repositorio.findById(generico.getIdvehiculo())
					.orElseThrow(() -> new IllegalStateException("Patente no existe."));
			vehiculo = generico;
			repositorio.save(vehiculo);
			logger.info("VEHÍCULO ACTUALIZADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> crear(Vehiculo generico) {
		boolean existe;
		logger.info("CREANDO VEHÍCULO CLIENTE " + generico.getIdcliente());
		try {

			Optional<Vehiculo> vehiculo = repositorio.findByPatenteAndIdcliente(generico.getPatente(),
					generico.getIdcliente());

			existe = vehiculo.isPresent();

			if (existe == false) {
				try {

					generico.setActive(true);
					repositorio.save(generico);
					logger.info("VEHÍCULO CREADO");
					return ResponseEntity.ok(generico);
				} catch (Exception e) {
					return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
				}
			}
			if (!vehiculo.get().isActive()) {
				try {

					Vehiculo vehiculo2 = repositorio.findById(vehiculo.get().getIdvehiculo())
							.orElseThrow(() -> new IllegalStateException("Patente no existe."));
					vehiculo2 = generico;
					vehiculo2.setActive(true);
					vehiculo2.setIdvehiculo(vehiculo.get().getIdvehiculo());
					repositorio.save(vehiculo2);
					logger.info("VEHÍCULO ACTIVO NUEVAMENTE");
					return ResponseEntity.ok(generico);
				} catch (Exception e) {
					logger.error("HUBO UN ERROR");
					return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
				}
			}

			else if (vehiculo.isPresent()) {
				return new ResponseEntity<>("!Ups¡ ya tienes registrada esta patente", HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return null;

	}

	@Override
	public List<Vehiculo> obtenerPorPaginacion(Pageable pageable, Long id) {
		return repositorio.findAllByIdcliente(pageable, id).getContent();
	}

	@Override
	public List<Vehiculo> obtenerTodosPaginacion(Pageable pageable){
		return repositorio.findAll(pageable).getContent();
	}
	@Override
	public List<Vehiculo> obtenerTodosPaginacion(Long id){
		return repositorio.findAllByIdcliente(id);
	}

}