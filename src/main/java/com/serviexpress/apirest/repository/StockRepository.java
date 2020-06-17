package com.serviexpress.apirest.repository;

import java.util.Optional;

import com.serviexpress.apirest.entity.category.Producto;
import com.serviexpress.apirest.entity.category.Stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findById(Long id);
    Optional<Stock> findByProducto(Producto producto);
}