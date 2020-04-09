package com.porta.porta.repository;

import java.io.Serializable;


import com.porta.porta.entity.Empleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("repositorioemp")
public interface  EmpleadoRepository extends JpaRepository<Empleado, Serializable>, PagingAndSortingRepository<Empleado, Serializable>{
    public abstract Empleado findByIdempleado(Long id);
    public abstract Page<Empleado> findAll(Pageable pageable);
}