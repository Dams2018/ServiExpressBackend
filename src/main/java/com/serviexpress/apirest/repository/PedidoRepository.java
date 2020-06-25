package com.serviexpress.apirest.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.serviexpress.apirest.entity.Pedido;


@Repository
public interface  PedidoRepository extends JpaRepository<Pedido, Serializable>, PagingAndSortingRepository<Pedido, Serializable>{
    public abstract Page<Pedido> findAllByEstado(Pageable pageable, Integer estado);
    public abstract Page<Pedido> findAll(Pageable pageable);
}