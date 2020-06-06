package com.serviexpress.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.payload.request.CategoriaDetalleRequest;
import com.serviexpress.apirest.service.impl.CategoriaDetalleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/categoria/subcategoria")
public class SubCategoriaController {


    @Autowired
    CategoriaDetalleServiceImpl subCategoriaImpl;
    
    @PutMapping("/add")
	public ResponseEntity<?> crearSubCategoria(@RequestBody @Valid CategoriaDetalleRequest subCategoria){
		return subCategoriaImpl.crear(subCategoria);
    }
    
    @PutMapping("/udp")
	public ResponseEntity<?> actualizarSubCategoria(@RequestBody @Valid CategoriaDetalleRequest subCategoria){
		return subCategoriaImpl.actualizar(subCategoria);
    }
    

    @GetMapping( value = "/list")
	public List<CategoriaDetalleRequest> obtenerSubCategoria(Pageable pageable){
        return subCategoriaImpl.obtenerPorPaginacion(pageable);
    }
    

    @PutMapping( value = "/listByCategory")
	public ResponseEntity<?> obtenerSubCategoriaByCategoria(Pageable pageable,@RequestBody @Valid CategoriaDetalleRequest subCategoria){
        return subCategoriaImpl.obtenerPorPaginacion(pageable,subCategoria);
    }
}