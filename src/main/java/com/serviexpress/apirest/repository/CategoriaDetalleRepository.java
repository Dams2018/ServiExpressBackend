package com.serviexpress.apirest.repository;

import java.util.Optional;

import com.serviexpress.apirest.entity.category.Categoria;
import com.serviexpress.apirest.entity.category.DetalleCategoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoriaDetalleRepository extends JpaRepository<DetalleCategoria,Long>{
    Optional<DetalleCategoria> findByCategoria(Categoria categoria);
    Optional<DetalleCategoria> findById(Long id);
    public abstract Page<DetalleCategoria> findAllByCategoria(Categoria categoria,Pageable pageable);
}