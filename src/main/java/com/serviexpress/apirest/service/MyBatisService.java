package com.serviexpress.apirest.service;

import java.util.List;

import com.serviexpress.apirest.payload.Response.IngresoVsEgreso;

public interface MyBatisService {

    public List<IngresoVsEgreso> getEgreso();	
    public List<IngresoVsEgreso> getIngreso();	
    
}