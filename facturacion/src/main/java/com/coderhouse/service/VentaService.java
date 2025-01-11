package com.coderhouse.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.VentaDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.ProductoRepository;
import com.coderhouse.repositories.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	//GET ALL VENTAS
	public List<VentaDTO> getAllVentas(){
		return ventaRepository.findAll().stream()
	            .map(venta -> new VentaDTO(
	            ))
	            .toList();
	}
	
	//GET VENTA BY ID
	public VentaDTO getVentaById(Long id) {
		Venta venta = ventaRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Venta no encontrada"));
		
		return new VentaDTO(
				);
	}
	
	//CREAR VENTA
	@Transactional
	public VentaDTO newVenta(Long clienteId, List<Long> productosId) {
		// Validar que el cliente exista
	    Cliente cliente = clienteRepository.findById(clienteId)
	            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	    
	    //Validar que los productos existan
	    List<Producto> productos = new ArrayList<>();
	    
	    for(Long productoId : productosId) {
	    	Producto producto = productoRepository.findById(productoId)
	    			.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
	    	
	    	//Stock
	    	if(producto.getStock() <= 0) {
	    		throw new IllegalArgumentException("Stock insuficiente");
	    	}
	    	
	    	producto.setStock(producto.getStock() - 1);
	    	productoRepository.save(producto);
	    	
	    	productos.add(producto);
	    }
	    
	    // Calcular el total de la venta
	    int total = productos.stream()
	            .mapToInt(Producto::getPrecio)
	            .sum();
	    
	    Venta nuevaVenta = new Venta();
	    
	    nuevaVenta.setFecha(new Date(System.currentTimeMillis()));
	    nuevaVenta.setCliente(cliente);
	    nuevaVenta.setProductos(productos);
	    nuevaVenta.setTotal(total);
	    
		Venta ventaGuardada = ventaRepository.save(nuevaVenta);
		
		List<Long> productosIds = productos.stream()
				.map(Producto::getId)
				.toList();
		
	    return new VentaDTO();
	}
	
	//ACTUALIZAR VENTA
	@Transactional
	public Venta updateVentaById(Long id, VentaDTO dto) {
		Venta venta = ventaRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Venta no encontrada"));
		
		// Actualizar cliente
	    if (dto.getClienteId() != null) {
	        Cliente cliente = clienteRepository.findById((Long) dto.getClienteId())
	                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	        venta.setCliente(cliente);
	    }
	    
	 // Actualizar productos
	    if (dto.getProductosId() != null && !dto.getProductosId().isEmpty()) {
	    	
	    	// Restaurar el stock de los productos actuales
	        for (Producto producto : venta.getProductos()) {
	            producto.setStock(producto.getStock() + 1);
	            productoRepository.save(producto);
	        }
	        
	        List<Producto> nuevosProductos = dto.getProductosId().stream()
	        		.map(productoId -> {
	        			Producto producto = productoRepository.findById((Long)(productoId)
	    	        			.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado");
	        			
	        			if(producto.getStock() <= 0) {
	    	                throw new IllegalArgumentException("Stock insuficiente");
	    	        	}
	        			
	        			producto.setStock(producto.getStock() - 1);
	    	        	productoRepository.save(producto);
	    	        	
	    	        	return producto;
	        		})
	        		.toList();
	        
        	venta.setProductos(nuevosProductos);
	        
	        // Recalcular el total
	        int nuevoTotal = nuevosProductos.stream()
	                .mapToInt(Producto::getPrecio)
	                .sum();
	        venta.setTotal(nuevoTotal);
	    }
	    
		return ventaRepository.save(venta);
	}
	
	//ELIMINAR VENTA
	public void deleteVentaById(Long id) {
		if(!ventaRepository.existsById(id)) {
			throw new IllegalArgumentException("Venta no encontrada");
		}
		ventaRepository.deleteById(id);
	}
}