package com.porta.porta.service;
import org.springframework.http.ResponseEntity;
import java.util.List;



public abstract class PersonaServices<T> {

    public  ResponseEntity<?> crear(T generico) {
        return null;
    }

    public boolean actualizar(T generico) {
        return false;
    }

    public List<T> obtener(T generico) {
        return null;
    }
}