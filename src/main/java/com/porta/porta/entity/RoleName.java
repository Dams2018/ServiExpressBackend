package com.porta.porta.entity;

public enum RoleName {

    ROLE_ADMIN("1","Administrador"), 
    ROLE_CLIENT("2","Cliente"), 
    ROLE_EMPLOYE("3","Empleado"),
    ROLE_COMPANY("4","Empresa");

    private final String id;
    private final String name;

    private RoleName(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }



}

// ROLE_ADMIN,
// ROLE_USER,
// ROLE_EMPLOYE,
// ROLE_COMPANY