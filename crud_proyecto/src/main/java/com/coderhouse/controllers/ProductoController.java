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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Producto;
import com.coderhouse.services.ProductoServices;

@RestController //API REST
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoServices productoService;
	
	
	@GetMapping
	public ResponseEntity <List<Producto>> getAllProductos(){
		try {
			List<Producto> producto = productoService.findAll();
			return ResponseEntity.ok(producto);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	//Busca a un cliente mediante su ID
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Long id){
		try {
			Producto producto = productoService.getProductoById(id);
			return ResponseEntity.ok(producto);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//CREAR UN CLIENTE
	@PostMapping
	public ResponseEntity <Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto createProducto = productoService.createProducto(producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(createProducto);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable Long id,@RequestBody Producto productoDetails){
		try {
			Producto updateProducto = productoService.updateProducto(id, productoDetails);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletProcuto(@PathVariable Long id){
		try {
			if(productoService.existsById(id)) {
				productoService.deleteById(id);
				return ResponseEntity.noContent().build();
			}else {
				return ResponseEntity.notFound().build();
			}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
