package com.serviexpress.apirest.mapper;

import java.util.List;

import com.serviexpress.apirest.payload.response.IngresoVsEgreso;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IngresoVSEgresoMapper {
    
    @Select("SELECT SUM(VALORTOTAL)total, TO_CHAR(fecha, 'mm/yyyy') fecha "+
	"FROM REPORTEIN "+	
	"GROUP BY TO_CHAR(fecha, 'mm/yyyy') order by fecha asc") 

    public List<IngresoVsEgreso> getIngreso();	
    
    @Select("SELECT SUM(sueldo)total, TO_CHAR(fecha, 'mm/yyyy') fecha "+
	"FROM SUELDO "+	
	"GROUP BY TO_CHAR(fecha, 'mm/yyyy') order by fecha asc") 

	public List<IngresoVsEgreso> getEgreso();	
}