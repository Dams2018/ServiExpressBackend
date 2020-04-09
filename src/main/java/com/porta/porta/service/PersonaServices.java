package com.porta.porta.service;

import java.util.List;


public abstract class PersonaServices<T> {

    public boolean crear(T generico) {
        return false;
    }

    public boolean actualizar(T generico) {
        return false;
    }

    public List<T> obtener(T generico) {
        return null;
    }
}