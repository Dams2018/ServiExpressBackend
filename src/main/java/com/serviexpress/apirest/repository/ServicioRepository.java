package com.serviexpress.apirest.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.serviexpress.apirest.entity.Servicio;


@Repository("repositorioservicio")
public interface  ServicioRepository extends JpaRepository<Servicio, Serializable>, PagingAndSortingRepository<Servicio, Serializable>{
    public abstract Page<Servicio> findAll(Pageable pageable);
    Optional<Servicio> findByIdservicio(String id);
}