package com.serviexpress.apirest.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.serviexpress.apirest.entity.Reserva;


@Repository("repositorioReserva")
public interface  ReservaRepository extends JpaRepository<Reserva, Serializable>, PagingAndSortingRepository<Reserva, Serializable>{
    public abstract Page<Reserva> findAllByIdcliente(Pageable pageable,Long id);
    public abstract Page<Reserva> findAllByIdclienteAndActivo(Pageable pageable,Long id, Boolean activo);
    public abstract Page<Reserva> findAllByIdclienteAndEstado(Pageable pageable,Long id, Integer estado);
    public abstract Page<Reserva> findAllByEstado(Pageable pageable, Integer estado);
    public abstract Page<Reserva> findAll(Pageable pageable);
    // List<Reserva> findAllByActivoAndFechaBetween(boolean estado ,@Param("fecha_dateStart") Date fecha_dateStart,@Param("fecha_dateEnd") Date fecha_dateEnd);
    @Query(value = "from Reserva t where FECHA >= TO_DATE(:startDate,'mm/dd/yyyy') and FECHA < TO_DATE(:startDate2,'mm/dd/yyyy') " )
                                 //WHERE FECHA >= TO_DATE('07/04/2020','mm/dd/yyyy') and FECHA < TO_DATE('07/05/2020','mm/dd/yyyy')
    public abstract Page<Reserva> getAllDayFecha(Pageable pageable, String startDate, String startDate2);
    @Query(value = "from Reserva t where FECHA >= TO_DATE(:startDate,'mm/yyyy') and FECHA < TO_DATE(:startDate2,'mm/yyyy') and ACTIVO=false")
    public abstract Page<Reserva>  getAllMonthFecha(Pageable pageable, String startDate, String startDate2);

}