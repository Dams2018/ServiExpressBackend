package com.porta.porta.repository;

import java.io.Serializable;
import java.util.List;

import com.porta.porta.entity.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("repositoriocli")
public interface  ClienteRepository extends JpaRepository<Cliente, Serializable>, PagingAndSortingRepository<Cliente, Serializable>{
    public abstract Cliente findByIdcliente(Long id);
    public abstract List<Cliente> findByNombre(String titulo);
    public abstract Page<Cliente> findAll(Pageable pageable);
}