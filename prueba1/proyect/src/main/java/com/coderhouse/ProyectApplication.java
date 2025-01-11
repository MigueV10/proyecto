package com.coderhouse;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.dao.DaoFactory;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Venta;



@SpringBootApplication
public class ProyectApplication {
	
	@Autowired
	private DaoFactory dao;

	public static void main(String[] args) {
		SpringApplication.run(ProyectApplication.class, args);
	}
	public void run(String... args) throws Exception {
		try {
			
			//Instancia de metodos
			Cliente cliente1 = new Cliente("Miguel","Urena","miguel@email.com");
			Cliente cliente2 = new Cliente("Kendra","Nicole","kendra@gmail.com");
			Cliente cliente3 = new Cliente("Brisa","Gamarra","brisa@hotmail.com");
			Cliente cliente4 = new Cliente("Ricardo","Piedras","ricardo@outlook.com");
			
			
			Producto producto1 = new Producto();
			Producto producto2 = new Producto("Luces", 229);
			Producto producto3 = new Producto("Alexa", 359);
			Producto producto4 = new Producto("Bocina", 899);
			
			//persistencia de productos y clientes

			dao.persistirProducto(producto1);
			dao.persistirProducto(producto2);
			dao.persistirProducto(producto3);
			dao.persistirProducto(producto4);
			
			dao.persistirCliente(cliente1);
			dao.persistirCliente(cliente2);
			dao.persistirCliente(cliente3);
			dao.persistirCliente(cliente4);
			
			Venta venta1 = new Venta();
			venta1.setCliente(cliente1);
			venta1.setProductos(Arrays.asList(producto1,producto2));
			venta1.setFecha(new java.util.Date());
			venta1.setTotal(venta1.calcularTotal());
			
			Venta venta2 = new Venta();
			venta1.setCliente(cliente2);
			venta1.setProductos(Arrays.asList(producto3,producto4));
			venta1.setFecha(new java.util.Date());
			venta1.setTotal(venta1.calcularTotal());
			
			Venta venta3 = new Venta();
			venta1.setCliente(cliente3);
			venta1.setProductos(Arrays.asList(producto4,producto1));
			venta1.setFecha(new java.util.Date());
			venta1.setTotal(venta1.calcularTotal());
			
			Venta venta4 = new Venta();
			venta1.setCliente(cliente4);
			venta1.setProductos(Arrays.asList(producto2,producto3));
			venta1.setFecha(new java.util.Date());
			venta1.setTotal(venta1.calcularTotal());
			
			//persistencia de las ventas
			dao.persistirVenta(venta1);
			dao.persistirVenta(venta2);
			dao.persistirVenta(venta3);
			dao.persistirVenta(venta4);
			
			
			
			//PERSISTIRLO LA INFORMACION QUIERO QUE SE GUARDE EN LA BASE DE DATOS
		}catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	

}
