package com.serviexpress.apirest.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.serviexpress.apirest.entity.EstadoReserva;


@Repository
public interface  EstadoReservaRepository extends JpaRepository<EstadoReserva, Serializable>, PagingAndSortingRepository<EstadoReserva, Serializable>{

}