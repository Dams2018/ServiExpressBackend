package com.serviexpress.apirest.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.serviexpress.apirest.entity.Vehiculo;

@Repository("repositoriovehiculo")
public interface  VehiculoRepository extends JpaRepository<Vehiculo, Serializable>, PagingAndSortingRepository<Vehiculo, Serializable>{
    public abstract Vehiculo findByIdvehiculo(Long id);
    public abstract Vehiculo findByIdcliente(Long id);
    Optional<Vehiculo> findByPatenteAndIdcliente(String patente,Long id);
    public abstract Page<Vehiculo> findAll(Pageable pageable);
    public abstract Page<Vehiculo> findAllByIdcliente(Pageable pageable,Long id);
}