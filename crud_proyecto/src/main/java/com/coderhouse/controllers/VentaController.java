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

import com.coderhouse.dtos.VentaRequestDTO;
import com.coderhouse.models.Venta;
import com.coderhouse.services.VentaServices;

@RestController // API REST
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaServices ventaService;

    // Obtener todas las ventas
    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        try {
            List<Venta> ventas = ventaService.getAllVentas();
            return ResponseEntity.ok(ventas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity <Venta> getVentaById(@PathVariable Long id) {
        try {
            Venta venta = ventaService.getVentaById(id);
            return ResponseEntity.ok(venta);//205
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();//404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
        }
    }

    // Crear una nueva venta
    @PostMapping
	public ResponseEntity <Venta> createVenta(@RequestBody VentaRequestDTO ventaRequestDTO) {
		try {
			Venta venta = ventaService.createVenta(ventaRequestDTO);
			return ResponseEntity.ok(venta);//201
		} catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();//404
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}

    // Eliminar una venta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        try {
            ventaService.deleteVenta(id);
            return ResponseEntity.noContent().build();//400
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();//404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
        }
    }

    // Actualizar una venta existente
    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Long id, @RequestBody Venta ventaDetails) {
        try {
            Venta updatedVenta = ventaService.updateVenta(id, ventaDetails);
            return ResponseEntity.ok(updatedVenta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

// // Agregar productos a una venta existente
//    @PostMapping("/agregar-productos")
//    public ResponseEntity<Venta> agregarProductosAVenta(@RequestBody VentaAgregarProductosDTO dto) {
//        try {
//            Venta ventaActualizada = ventaService.agregarProductosAVenta(dto);
//            return ResponseEntity.ok(ventaActualizada);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}