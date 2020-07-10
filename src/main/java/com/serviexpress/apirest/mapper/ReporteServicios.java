package com.serviexpress.apirest.mapper;

import java.util.List;

import com.serviexpress.apirest.payload.response.IngresoVsEgreso;
import com.serviexpress.apirest.payload.response.ReporteServicio;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReporteServicios {
    
    @Select("SELECT count(s.idservicio) as totalservicios, s.nombre as nombreservicio "+
	"FROM reserva r "+	
	"INNER JOIN servicio s ON r.servicios = s.idservicio "+
	"where r.estado = 6"+
	"GROUP BY  s.nombre order by 2 asc"
	) 

    public List<ReporteServicio> getReporteServicio();
    
}