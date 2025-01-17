package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Venta;
import com.coderhouse.repositories.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaServices {

	@Autowired
	private VentaRepository ventaRepository;
	
	public List<Venta> getAllVentas(){
		return ventaRepository.findAll();
	}
	
	public Venta getVentaById(Long id) {
		return ventaRepository.findById(id).orElseThrow(() 
				-> new IllegalArgumentException("Venta No encontrada"));
	}
	
	@Transactional
	public Venta createVenta(Venta venta) {
		return ventaRepository.save(venta);
	}
	@Transactional
	public Venta updateVenta(Long id, Venta ventaDetails) {
		Venta venta = ventaRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Venta no encontrada "));
		venta.setCliente(ventaDetails.getCliente());
		return ventaRepository.save(venta);
	}
	public void deleteVenta(Long id) {
		if (ventaRepository.existsById(id)) {
			ventaRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Venta no encontrado");
		}
	}
}
