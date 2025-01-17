package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.coderhouse.models.Categoria;
import com.coderhouse.services.CategoriaServices;

public class CategoriaController {
	 @Autowired
	    private CategoriaServices categoriaService;

	    @GetMapping
	    public List<Categoria> getAllCategorias() {
	        return categoriaService.getAllCategorias();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
	        try {
	            Categoria categoria = categoriaService.getCategoriaById(id);
	            return ResponseEntity.ok(categoria);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.notFound().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	    @PostMapping
	    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
	        try {
	            Categoria createdCategoria = categoriaService.createCategoria(categoria);
	            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.notFound().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoriaDetails) {
	        try {
	            Categoria updateCategoria = categoriaService.updateCategoria(id, categoriaDetails);
	            return ResponseEntity.status(HttpStatus.CREATED).body(updateCategoria);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategoriaById(@PathVariable Long id) {
	        try {
	            if(categoriaService.existCategoriaById(id)) {
	                categoriaService.deleteCategoriaById(id);
	                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
}
