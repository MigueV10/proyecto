package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Venta;
import com.coderhouse.repositories.VentaRepository;
import com.coderhouse.services.VentaServices;

@RestController //API REST
@RequestMapping("/api/ventas") 
public class VentaController {

	@Autowired
	private VentaServices ventaService;
	
	@GetMapping
	public ResponseEntity <List<Venta>> getAllVentas(){
		try {
			List<Venta> ventas = ventaService.getAllVentas();
			return ResponseEntity.ok(ventas);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	//Busca a un cliente mediante su ID
	@GetMapping("/{id}")
	public ResponseEntity<Venta> getVentaById(@PathVariable Long id){
		try {
			Venta venta = ventaService.getVentaById(id);
			return ResponseEntity.ok(venta);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//CREAR UN CLIENTE
	@PostMapping
	public ResponseEntity <Venta> createVenta(@RequestBody Venta venta) {
		try {
			Venta createVenta = ventaService.createVenta(venta);
			return ResponseEntity.status(HttpStatus.CREATED).body(createVenta);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVenta(@PathVariable Long id){
		try {
			ventaService.deleteVenta(id);
			return ResponseEntity.noContent().build();
		}catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
