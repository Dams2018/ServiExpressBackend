package com.serviexpress.apirest.service;

import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

import com.serviexpress.apirest.entity.Categoria;
import com.serviexpress.apirest.entity.Producto;
import com.serviexpress.apirest.entity.Proveedor;
import com.serviexpress.apirest.entity.Servicio;
import com.serviexpress.apirest.payload.Encuesta;

import org.springframework.data.domain.Pageable;



public abstract class UniversalServices<T> {

    public  ResponseEntity<?> crear(T generico) {
        return null;
    }

    public  ResponseEntity<?> crearwithList(List<Encuesta> generico) {
        return null;
    }

    public ResponseEntity<?> actualizar(T generico) {
        return null;
    }

    public List<T> obtener() {
        return null;
    }
    public Categoria findById(Long idCategoria) {
        return null;
    }
    
    public Proveedor findByIdProveedor(Long idProveedor) {
        return null;
    }


    public Producto findByIdProducto(Long idproducto) {
        return null;
    }
    public Servicio findByIdServicio(Long idServicio) {
        return null;
    }

    public List<T> obtenerPorPaginacion(Pageable pageable,Long id){
        return null;
    }



    //RESERVA
    //estado de reserva
    public List<T> obtenerPorPaginacion(Pageable pageable,Integer estado){
        return null;
    }
    //estado de reserva
    public List<T> obtenerPorPaginacionReservaActiva(Pageable pageable, Long idcliente, Boolean activo){
        return null;
    }

    public List<T> obtenerPorPaginacion(Pageable pageable){
        return null;
    }

    public ResponseEntity<?> findByIdReserva(Long idReserva, int estado) {
        return null;
    }

    public ResponseEntity<?> obtenerEncuesta() {
        return null;
    }
    
    //PEDIDO
    public ResponseEntity<?> findByIdPedido(Long idPedido, int estado) {
        return null;
    }

}