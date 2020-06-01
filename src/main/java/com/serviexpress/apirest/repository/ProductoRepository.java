package com.serviexpress.apirest.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.serviexpress.apirest.entity.Producto;


@Repository("repositorioproducto")
public interface  ProductoRepository extends JpaRepository<Producto, Serializable>, PagingAndSortingRepository<Producto, Serializable>{
    public abstract Page<Producto> findAll(Pageable pageable);
    public abstract Page<Producto> findAllBycategoria(Pageable pageable,Long id);
}