package com.serviexpress.apirest.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.serviexpress.apirest.entity.Reserva;


@Repository
public interface  ReservaRepository extends JpaRepository<Reserva, Serializable>, PagingAndSortingRepository<Reserva, Serializable>{
    public abstract Page<Reserva> findAllByIdcliente(Pageable pageable,Long id);
    public abstract Page<Reserva> findAllByEstado(Pageable pageable, Integer estado);
    public abstract Page<Reserva> findAll(Pageable pageable);
}