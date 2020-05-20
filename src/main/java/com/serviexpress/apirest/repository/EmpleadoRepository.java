package com.serviexpress.apirest.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.serviexpress.apirest.entity.Empleado;

@Repository("repositorioemp")
public interface  EmpleadoRepository extends JpaRepository<Empleado, Serializable>, PagingAndSortingRepository<Empleado, Serializable>{
    Optional<Empleado> findByIdusuario(Long id);
    public abstract Page<Empleado> findAll(Pageable pageable);
}