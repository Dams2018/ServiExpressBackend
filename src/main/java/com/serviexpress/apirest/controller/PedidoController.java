package com.serviexpress.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.entity.Pedido;
import com.serviexpress.apirest.payload.response.PedidoDTO;
import com.serviexpress.apirest.service.impl.PedidoServicesImpl;



import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

	@Autowired
	PedidoServicesImpl pedidoServicesImpl;

	// Cliente
	@PutMapping("/pedido")
	public ResponseEntity<?> agregarPedido(@RequestBody @Valid final PedidoDTO PedidoDTO) {
		Pedido pedido =new Pedido();
		System.out.println(PedidoDTO.toString());
		pedido.setCantidad(PedidoDTO.getCantidad());
		pedido.setComentariopedido(PedidoDTO.getComentariopedido());
		pedido.setEmpleado(PedidoDTO.getEmpleado());
		pedido.setEstado(PedidoDTO.getEstado());
		pedido.setFechapedido(PedidoDTO.getFechapedido());
		pedido.setFecharecibo(PedidoDTO.getFecharecibo());
		pedido.setProducto(PedidoDTO.getProducto());
		pedido.setProveedor(PedidoDTO.getProveedor());
	
		return ResponseEntity.ok(pedidoServicesImpl.crear(pedido));

	}

	@PostMapping("/pedido")
	public ResponseEntity<?> actualizarReserva(@RequestBody @Valid final PedidoDTO PedidoDTO) {
		Pedido pedido =new Pedido();
		pedido.setCantidad(PedidoDTO.getCantidad());
		pedido.setComentariopedido(PedidoDTO.getComentariopedido());
		pedido.setEmpleado(PedidoDTO.getEmpleado());
		pedido.setEstado(PedidoDTO.getEstado());
		pedido.setFechapedido(PedidoDTO.getFechapedido());
		pedido.setFecharecibo(PedidoDTO.getFecharecibo());
		pedido.setProducto(PedidoDTO.getProducto());
		pedido.setProveedor(PedidoDTO.getProveedor());
		pedido.setIdpedido(PedidoDTO.getIdpedido());
		return ResponseEntity.ok(pedidoServicesImpl.actualizar(pedido));
	}

	@GetMapping(value = "/pedidos")
	public List<Pedido> allProveedores() {
		return pedidoServicesImpl.obtener();
	}

	// para lista por estado
	@GetMapping(value = "/{estado}/estado")
	public List<Pedido> obtenerPedidoEstado(final Pageable pageable,
			@PathVariable(value = "estado") final Integer estado) {
		return pedidoServicesImpl.obtenerPorPaginacion(pageable, estado);
	}

	@GetMapping(value = "/{id}/{estado}/pedido")
	public ResponseEntity<?> actualizarEstadoReserva(@PathVariable(value = "estado") final Integer estado,
			@PathVariable(value = "id") final Long id) {

		pedidoServicesImpl.findByIdPedido(id, estado);

		return ResponseEntity.ok("Pedido Actulizada");
	}

	
}