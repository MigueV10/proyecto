package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.error.AgregarCategoriaException;
import com.coderhouse.models.Categoria;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.CategoriaRepository;
import com.coderhouse.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoServices {
	
	@Autowired
	private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    
	public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

	public Producto getProductoById(Long id) {
		return productoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

	}

	 @Transactional
	    public Producto saveProducto(Producto producto){
	        return productoRepository.save(producto);
	    }

	@Transactional
	public Producto createProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Transactional
    public Producto updateProducto(Long id, Producto productoDetails){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encuentra producto con ID: " + id));

        producto.setTitulo(productoDetails.getTitulo());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setStock(productoDetails.getStock());
        producto.setClientes(productoDetails.getClientes());
        producto.setCategoria(productoDetails.getCategoria());

        return productoRepository.save(producto);
    }

	 public void deleteProductoById(Long id){
	        if(productoRepository.existsById(id)){
	            productoRepository.deleteById(id);
	        } else {
	            throw new IllegalArgumentException("No se encuentra producto con ID: " + id);
	        }
	    }
	 
	 @Transactional
	    public Producto addCategoryToProduct(Long productoId, Long categoriaId) throws AgregarCategoriaException {
	        Categoria categoria = categoriaRepository.findById(categoriaId)
	                .orElseThrow(() -> new AgregarCategoriaException("No se encuentra categoria con ID: " + categoriaId));
	        Producto producto = productoRepository.findById(productoId)
	                .orElseThrow(() -> new AgregarCategoriaException("No se encuentra producto con ID: " + productoId));

	        producto.setCategoria(categoria);

	        return productoRepository.save(producto);
	    }
}