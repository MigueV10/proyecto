package com.coderhouse.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ventas")
public class Venta {
	
	@Id // Primary KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENTAL
    private Long id;
	
	@Column(nullable=false)
    private Date fecha;
	
	@Column(nullable=false)
    private int total;
    
	//Por cliente realiza varias ventas
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	//Cada venta puede incluir varios productos y cada producto puede pertener a varias ventas
	@ManyToMany(fetch = FetchType.LAZY)//cargar los datos bajo demanda
	@JoinTable(
			name = "venta_producto", 
			joinColumns = @JoinColumn(name = "venta_id"), 
			inverseJoinColumns = @JoinColumn(name = "producto_id"))
	
	private List<Producto> productos = new ArrayList<>();
	
	//encapsulamiento
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
		this.total=calcularTotal();// Actualizamos el campo total directamente
	}
	public int calcularTotal() {
	        return productos.stream().mapToInt(Producto::getPrecio).sum();
	    }
	private void actualizarTotal() {
	    this.total = productos.stream().mapToInt(Producto::getPrecio).sum();
	}

	//toString
	@Override
	public String toString() {
		return "Venta [id=" + id + ", fecha=" + fecha + ", total=" + total + ", cliente=" + cliente + "]";
	}
	//Constructor 
	public Venta() {
		super();
	}
	
	
	
	
}
