package com.serviexpress.apirest.repository;

import java.util.Optional;

import com.serviexpress.apirest.entity.category.Marca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long>{
    Optional<Marca> findById(Long id);
}