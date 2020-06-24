package com.serviexpress.apirest.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.serviexpress.apirest.entity.Proveedor;


@Repository("repositorioproveedor")
public interface  ProveedorRepository extends JpaRepository<Proveedor, Serializable>, PagingAndSortingRepository<Proveedor, Serializable>{
    public abstract Proveedor findByNombre(String nombre);
    public abstract Page<Proveedor> findAll(Pageable pageable);
}