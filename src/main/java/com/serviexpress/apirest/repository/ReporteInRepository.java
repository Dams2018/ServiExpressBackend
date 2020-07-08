package com.serviexpress.apirest.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.serviexpress.apirest.entity.ReporteIn;
import com.serviexpress.apirest.payload.Response.IngresoVsEgreso;




@Repository("repositoriorportein")
public interface  ReporteInRepository extends JpaRepository<ReporteIn, Serializable>, PagingAndSortingRepository<ReporteIn, Serializable>{
        
        // @Query(value = "FROM REPORTEIN t GROUP BY TO_CHAR(fecha, 'mm/yyyy') order by fecha asc")
        // List<IngresoVsEgreso> getIngreso();

        // @Query(value = "from Reserva t where FECHA >= TO_DATE(:startDate,'mm/yyyy') and FECHA < TO_DATE(:startDate2,'mm/yyyy') and ACTIVO=false order by 4 desc")
        // List<IngresoVsEgreso> getEgreso();
       
}