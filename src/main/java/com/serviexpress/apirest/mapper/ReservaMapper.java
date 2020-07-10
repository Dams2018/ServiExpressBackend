package com.serviexpress.apirest.mapper;

import java.util.List;


import com.serviexpress.apirest.payload.response.ResponseReservaPago;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReservaMapper {
    
    @Select("SELECT c.nombre as nombre,c.apellido as apellido,c.rut as rut,v.marca as marca,v.modelo as modelo,v.patente as patente, "+
	"r.estado as estadoreserva,p.valorbase + s.valorbase as total, r.idreserva as idreserva, p.nombre as nombreproducto, s.nombre as nombreservicio "+
	"FROM reserva r "+
	"INNER JOIN vehiculo v "+
	"ON r.idvehiculo = v.idvehiculo "+
	"INNER JOIN cliente c "+
	"ON r.idcliente = c.idcliente "+
	"INNER JOIN producto p "+
	"ON r.productos = p.idproducto "+
	"INNER JOIN servicio s "+
	"ON r.servicios = s.idservicio "+
	"WHERE "+
	"v.patente = #{patente} and r.estado = 4 ") 

    public List<ResponseReservaPago> getReservaCliente(@Param("patente") String patente);	
    
}