package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviexpress.apirest.entity.Pedido;
import com.serviexpress.apirest.repository.PedidoRepository;
import com.serviexpress.apirest.service.UniversalServices;

import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.springframework.data.domain.Pageable;

@Service
public class PedidoServicesImpl extends UniversalServices<Pedido> {
	@Autowired
	private PedidoRepository repositorio;
	private static final Log logger = LogFactory.getLog(UniversalServices.class);
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError = new MensajeVO();
	String[] mensajes = new String[3];



	@Override
	public ResponseEntity<?> actualizar(Pedido generico) {
		logger.info("ACTUALIZANDO Pedido");
		try {
			Pedido pedido = repositorio.findById(generico.getIdpedido())
					.orElseThrow(() -> new IllegalStateException("El pedido no existe."));
			pedido = generico;
			repositorio.save(pedido);
			logger.info("PEDIDO ACTUALIZADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> crear(Pedido generico) {
		logger.info("CREANDO PEDIDO");
		try {
			repositorio.save(generico);
			//falta agregar el historial de reserva
			logger.info("RESERVA CREADO");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	

	@Override
	public List<Pedido> obtener() {
		logger.info("OBTENIENDO TODOS LOS PEDIDOS");
		return repositorio.findAll();
	}


	@Override
	public List<Pedido> obtenerPorPaginacion(Pageable pageable, Integer estado) {
		return repositorio.findAllByEstado(pageable, estado).getContent();
	}


	@Override
	public List<Pedido> obtenerPorPaginacion(Pageable pageable){
		return repositorio.findAll(pageable).getContent();
	}
	@Override

	public ResponseEntity<?> findByIdPedido(Long idPedido, int estado){

		Pedido pedido = repositorio.findById(idPedido)
		.orElseThrow(() -> new IllegalStateException("Pedido no existe."));
		pedido.setEstado(estado);
		repositorio.save(pedido);
		return ResponseEntity.ok(pedido);

	}
}