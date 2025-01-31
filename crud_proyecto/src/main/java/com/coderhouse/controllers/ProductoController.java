package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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
import com.coderhouse.models.Venta;
import com.coderhouse.services.CategoriaServices;
import com.coderhouse.services.ProductoServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController 
@RequestMapping("/api/productos")
@Tag(name = "Productos", description  = "Manejador De Productos")
public class ProductoController {

	@Autowired
	private ProductoServices productoService;
    @SuppressWarnings("unused")
	@Autowired
    private CategoriaServices categoriaService;
	
    @Operation(summary = "Listar Productos")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Producto  listados Correctamente",
    				content = {
    						@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
    				}),
    		@ApiResponse(responseCode = "404", description = "Error al listar los producto",
    				content = @Content ),
    		@ApiResponse(responseCode = "500", description = "Error interno del servidor",
			content = @Content (mediaType = "aplication/json",
    				schema = @Schema(implementation = ErrorResponse.class)))
    })
	  @GetMapping
	    public ResponseEntity<List<Producto>> getAllProductos(){
	        try {
	            List<Producto> productos = productoService.getAllProductos();
	            return ResponseEntity.ok(productos);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.notFound().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	
	  @Operation(summary = "Listar de un producto por SU ID")
	    @ApiResponses(value = {
	    		@ApiResponse(responseCode = "200", description = "Producto por su ID listado Correctamente",
	    				content = {
	    						@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
	    				}),
	    		@ApiResponse(responseCode = "404", description = "Error al listar producto",
	    				content = @Content ),
	    		@ApiResponse(responseCode = "500", description = "Error interno del servidor",
				content = @Content (mediaType = "aplication/json",
	    				schema = @Schema(implementation = ErrorResponse.class)))
	    })
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
	
	@Operation(summary = "Creacion de un producto")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "201", description = "Producto Creado Correctamente",
    				content = {
    						@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
    				}),
    		@ApiResponse(responseCode = "404", description = "Error al crear el producto",
    				content = @Content ),
    		@ApiResponse(responseCode = "500", description = "Error interno del servidor",
			content = @Content (mediaType = "aplication/json",
    				schema = @Schema(implementation = ErrorResponse.class)))
    })
	@PostMapping
	public ResponseEntity <Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto createProducto = productoService.saveProducto(producto);
			return ResponseEntity.ok(createProducto);
		} catch (IllegalArgumentException e) {
			 e.printStackTrace(); 
            return ResponseEntity.notFound().build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	 @Operation(summary = "Actualizacion de producto por ID")
	    @ApiResponses(value = {
	    		@ApiResponse(responseCode = "200", description = "Producto corregido Correctamente",
	    				content = {
	    						@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
	    				}),
	    		@ApiResponse(responseCode = "404", description = "Error al actualizar el producto",
	    				content = @Content ),
	    		@ApiResponse(responseCode = "500", description = "Error interno del servidor",
				content = @Content (mediaType = "aplication/json",
	    				schema = @Schema(implementation = ErrorResponse.class)))
	    })
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable Long id,@RequestBody Producto productoDetails){
		try {

			Producto updateProducto = productoService.updateProducto(id, productoDetails);
			return ResponseEntity.ok(updateProducto);
		} catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	 @Operation(summary = "ELiminar un producto por SU ID")
	    @ApiResponses(value = {
	    		@ApiResponse(responseCode = "204", description = "Producto eliminado Correctamente por su ID",
	    				content = {
	    						@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
	    				}),
	    		@ApiResponse(responseCode = "404", description = "Error AL eliminar producto",
	    				content = @Content ),
	    		@ApiResponse(responseCode = "500", description = "Error interno del servidor",
				content = @Content (mediaType = "aplication/json",
	    				schema = @Schema(implementation = ErrorResponse.class)))
	    })
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductoById(@PathVariable Long id){
        try {
            productoService.deleteProductoById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	  @Operation(summary = "Asignar una categoria A Un producto")
	    @ApiResponses(value = {
	    		@ApiResponse(responseCode = "200", description = "Categoiria asignada Correctamente",
	    				content = {
	    						@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
	    				}),
	    		@ApiResponse(responseCode = "404", description = "Error al asignar la categoria",
	    				content = @Content(mediaType = "aplication/json",
	    				schema = @Schema(implementation = ErrorResponse.class)) ),
	    		@ApiResponse(responseCode = "500", description = "Error interno del servidor",
				content = @Content (mediaType = "aplication/json",
	    				schema = @Schema(implementation = ErrorResponse.class)))
	    })
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