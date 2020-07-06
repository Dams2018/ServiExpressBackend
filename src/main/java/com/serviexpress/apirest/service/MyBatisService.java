package com.serviexpress.apirest.service;

import java.util.List;

import com.serviexpress.apirest.payload.Response.IngresoVsEgreso;
import com.serviexpress.apirest.payload.Response.ResponseReservaPago;

public interface MyBatisService {

    public List<IngresoVsEgreso> getEgreso();	
    public List<IngresoVsEgreso> getIngreso();
    public List<ResponseReservaPago> getReservaCliente(String patente);	
    
}