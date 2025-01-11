package com.coderhouse.dao;

import org.springframework.stereotype.Service;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Venta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DaoFactory {
	@PersistenceContext
	private EntityManager em;// Accede con em a todos los metods
	
	@Transactional// metodo de transicion
	public void persistirCliente(Cliente cliente) {
		em.persist(cliente);//Persistencia del alumno
	}
	@Transactional// metodo de transicion
	public void persistirProducto(Producto producto) {
		em.persist(producto);//Persistencia del producto
	}
	@Transactional// metodo de transicion
	public void persistirVenta(Venta venta) {
		em.persist(venta);//Persistencia de venta
	}
	
	
}
