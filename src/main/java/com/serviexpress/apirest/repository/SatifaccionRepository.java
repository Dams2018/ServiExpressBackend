package com.serviexpress.apirest.repository;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.serviexpress.apirest.entity.Satifaccion;



@Repository("repositorioSatifaccion")
public interface  SatifaccionRepository extends JpaRepository<Satifaccion, Serializable>, PagingAndSortingRepository<Satifaccion, Serializable>{

        long countByNombreAndTipo(String nombre, String tipo);
        // long countByNombreAndTipoAndFechaFieldBetweenFecha(String nombre, String tipo, String ini, String fin);
        long countByNombreAndTipoAndFechaBetween(String nombre, String tipo,Date fecha_dateStart, Date fecha_dateEnd);
        
        List<Satifaccion> findByNombre(String nombre);
}