package com.serviexpress.apirest.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.serviexpress.apirest.entity.Cliente;

@Repository("repositoriocli")
public interface  ClienteRepository extends JpaRepository<Cliente, Serializable>, PagingAndSortingRepository<Cliente, Serializable>{
    Optional<Cliente>  findByIdusuario(Long id);
    public abstract List<Cliente> findByNombre(String titulo);
    public abstract Page<Cliente> findAll(Pageable pageable);
}