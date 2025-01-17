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

import com.coderhouse.dtos.VentaAgregarProductosDTO;
import com.coderhouse.error.AgregarCategoriaException;
import com.coderhouse.models.Producto;
import com.coderhouse.services.CategoriaServices;
import com.coderhouse.services.ProductoServices;

@RestController //API REST
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoServices productoService;
    @SuppressWarnings("unused")
	@Autowired
    private CategoriaServices categoriaService;
	
	//LISTAR PRODUCTOS
	  @GetMapping
	    public ResponseEntity<List<Producto>> getAllProductos(){
	        try {
	            List<Producto> productos = productoService.getAllProductos();
	            return ResponseEntity.ok(productos);//200
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.notFound().build();//404
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
	        }
	    }
	
	
	//Busca a un cliente mediante su ID
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Long id){
		try {
			Producto producto = productoService.getProductoById(id);
			return ResponseEntity.ok(producto);//200
		}catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();//404
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}
	
	//CREAR UN PRODUCTO
	@PostMapping
	public ResponseEntity <Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto createProducto = productoService.saveProducto(producto);
			return ResponseEntity.ok(createProducto);//201
		} catch (IllegalArgumentException e) {
			 e.printStackTrace(); // Esto imprimirá el error en la consola
            return ResponseEntity.notFound().build();//404
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
			
		}
	}
	//ACTUALIZAR PRODUCTO
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable Long id,@RequestBody Producto productoDetails){
		try {

			Producto updateProducto = productoService.updateProducto(id, productoDetails);
			return ResponseEntity.ok(updateProducto);//ACTUALIZAR ALUMNO 204
		} catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();//404
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}
	//DELETE PRODUCTO
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductoById(@PathVariable Long id){
        try {
            productoService.deleteProductoById(id);//BORRAR A UN CLIENTE
            return ResponseEntity.noContent().build();//400
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();//404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
        }
    }
	
	@PostMapping("/asignar-categoria")
    public ResponseEntity<Producto> addCategoryToProduct(@RequestBody VentaAgregarProductosDTO agregarCatetoriaProductoDTO){
        try {
            Producto updateProducto = productoService.addCategoryToProduct(
            		agregarCatetoriaProductoDTO.getProductoId(),
            		agregarCatetoriaProductoDTO.getCategoriaId()
                    );
            return ResponseEntity.ok(updateProducto);
        } catch (AgregarCategoriaException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}