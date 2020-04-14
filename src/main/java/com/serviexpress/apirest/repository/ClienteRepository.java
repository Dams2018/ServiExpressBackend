package com.serviexpress.apirest.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.serviexpress.apirest.entity.Cliente;

@Repository("repositoriocli")
public interface  ClienteRepository extends JpaRepository<Cliente, Serializable>, PagingAndSortingRepository<Cliente, Serializable>{
    public abstract Cliente findByIdcliente(Long id);
    public abstract List<Cliente> findByNombre(String titulo);
    public abstract Page<Cliente> findAll(Pageable pageable);
}