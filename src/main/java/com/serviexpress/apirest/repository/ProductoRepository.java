package com.serviexpress.apirest.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviexpress.apirest.entity.category.*;


@Repository()
public interface  ProductoRepository extends JpaRepository<Producto, Long>{
    public abstract Page<Producto> findAll(Pageable pageable);
}