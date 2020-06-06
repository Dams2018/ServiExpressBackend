package com.serviexpress.apirest.service;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.data.domain.Pageable;



public abstract class UniversalServices<T> {

    public  ResponseEntity<?> crear(T generico) {
        return null;
    }

    public ResponseEntity<?> actualizar(T generico) {
        return null;
    }

    public List<T> obtener() {
        return null;
    }

    public List<T> obtenerPorPaginacion(Pageable pageable,Long id){
        return null;
    }

    //estado de reserva
    public List<?> obtenerPorPaginacion(Pageable pageable,Integer estado){
        return null;
    }

        //estado de reserva
    public ResponseEntity<?> obtenerPorPaginacion(Pageable pageable,T var){
        return null;
    }

    public List<?> obtenerPorPaginacion(Pageable pageable){
        return null;
    }
}