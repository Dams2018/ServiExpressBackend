package com.serviexpress.apirest.mapper;

import java.util.List;

import com.serviexpress.apirest.payload.response.IngresoVsEgreso;
import com.serviexpress.apirest.payload.response.ReporteServicio;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReporteServicios {
    
    @Select("SELECT count(s.idservicio) as totalservicios, TO_CHAR(fecha, 'mm/yyyy') mes, s.nombre as nombreservicio "+
	"FROM reserva r "+	
	"INNER JOIN servicio s ON r.servicios = s.idservicio "+
	"GROUP BY TO_CHAR(fecha, 'mm/yyyy'),s.nombre order by mes asc"
	) 

    public List<ReporteServicio> getReporteServicio();	
    
}