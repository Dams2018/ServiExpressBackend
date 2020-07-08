package com.serviexpress.apirest.repository;

import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.serviexpress.apirest.entity.ReporteIn;





@Repository("repositoriorportein")
public interface  ReporteInRepository extends JpaRepository<ReporteIn, Serializable>, PagingAndSortingRepository<ReporteIn, Serializable>{
        
       
}