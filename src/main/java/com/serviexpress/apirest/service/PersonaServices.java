package com.porta.porta.service;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;



public abstract class PersonaServices<T> {

    public  ResponseEntity<?> crear(T generico) {
        return null;
    }

    public ResponseEntity<?> actualizar(T generico) {
        return null;
    }

    public List<T> obtener() {
        return null;
    }

    public List<T> obtenerPorPaginacion(Pageable pageable){
        return null;
    }
}