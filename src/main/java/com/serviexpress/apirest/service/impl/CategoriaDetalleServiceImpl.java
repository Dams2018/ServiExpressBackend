package com.serviexpress.apirest.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.serviexpress.apirest.entity.category.Categoria;
import com.serviexpress.apirest.entity.category.DetalleCategoria;
import com.serviexpress.apirest.payload.ApiResponse;
import com.serviexpress.apirest.payload.request.CategoriaDetalleRequest;
import com.serviexpress.apirest.repository.CategoriaDetalleRepository;
import com.serviexpress.apirest.repository.CategoriaRepository;
import com.serviexpress.apirest.service.UniversalServices;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public class CategoriaDetalleServiceImpl extends UniversalServices<CategoriaDetalleRequest> {

    @Autowired
    CategoriaDetalleRepository categoriaDetalleRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public ResponseEntity<?> actualizar(CategoriaDetalleRequest generico) {
        try {

            
            DetalleCategoria detalle = categoriaDetalleRepository.findById(generico.getId())
                .orElseThrow(() -> new IllegalStateException("Categoria"));
            detalle.setNombre(generico.getNombre());
            detalle.setDescripcion(generico.getDescripcion());
            categoriaDetalleRepository.save(detalle);
            ApiResponse response = new ApiResponse(true, "Sub Categoria Actualizada");
            return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "No se pudo crear la sub categoria");
            return new ResponseEntity<ApiResponse>(response,HttpStatus.GONE);
        }
    }

    @Override
    public ResponseEntity<?> crear(CategoriaDetalleRequest generico) {
    
        try {
            Categoria categoria = categoriaRepository.findById(generico.getIdCategoria())
                    .orElseThrow(() -> new IllegalStateException("Categoria"));
            
            DetalleCategoria detalle = new DetalleCategoria();
            detalle.setNombre(generico.getNombre());
            detalle.setDescripcion(generico.getDescripcion());
            detalle.setCategoria(categoria);
            categoriaDetalleRepository.save(detalle);
            ApiResponse response = new ApiResponse(true, "Sub Categoria Creada de categoria principal: "+categoria.getNombre());
            return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "No se pudo crear la sub categoria");
            return new ResponseEntity<ApiResponse>(response,HttpStatus.GONE);
        }
    }

    @Override
    public List<CategoriaDetalleRequest> obtenerPorPaginacion(Pageable pageable) {
        List<CategoriaDetalleRequest> listCategory = new ArrayList<>();

		for (DetalleCategoria subCategoria : categoriaDetalleRepository.findAll(pageable).getContent()) {
            CategoriaDetalleRequest subCategoriaRequest = new CategoriaDetalleRequest(subCategoria.getId(), subCategoria.getNombre(), subCategoria.getDescripcion(), subCategoria.getCategoria().getId(),subCategoria.getCategoria().getNombre());
			listCategory.add(subCategoriaRequest);
		}
		
		return listCategory;
    }

    public ResponseEntity<?> obtenerPorPaginacion(Pageable pageable,CategoriaDetalleRequest request) {
        try {
            List<CategoriaDetalleRequest> listCategory = new ArrayList<>();
            Categoria categoria = categoriaRepository.findById(request.getIdCategoria())
             .orElseThrow(() -> new IllegalStateException("No Existe Categoria"));
             for (DetalleCategoria subCategoria : categoriaDetalleRepository.findAllByCategoria(categoria,pageable).getContent()) {
                 CategoriaDetalleRequest subCategoriaRequest = new CategoriaDetalleRequest(subCategoria.getId(), subCategoria.getNombre(), subCategoria.getDescripcion(), subCategoria.getCategoria().getId(),subCategoria.getCategoria().getNombre());
                 listCategory.add(subCategoriaRequest);
             }
             return new ResponseEntity<List<CategoriaDetalleRequest>>(listCategory, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse apiResponse = new ApiResponse(false, "No se pudo realizar la operacion");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

    }

    @Override
    public List<CategoriaDetalleRequest> obtener() {
        List<CategoriaDetalleRequest> listCategory = new ArrayList<>();

		for (DetalleCategoria subCategoria : categoriaDetalleRepository.findAll()) {
            CategoriaDetalleRequest subCategoriaRequest = new CategoriaDetalleRequest(subCategoria.getId(), subCategoria.getNombre(), subCategoria.getDescripcion(), subCategoria.getCategoria().getId(),subCategoria.getCategoria().getNombre());
			listCategory.add(subCategoriaRequest);
		}
		
		return listCategory;
    }

    
    



    

}