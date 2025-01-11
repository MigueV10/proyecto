package com.coderhouse.models;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="productos")

public class Producto {

    @Id // Primary KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENTAL
    private Long id;
    
    @Column(name="Nombre")
    private String nombre;
    private int precio;
    
    @ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
    private List<Venta> ventas; // Asegúrate de que exista la clase Venta con el mapeo adecuado

    //Constructor superclase
    public Producto() {
		super();
    }
    
	public Producto( String nombre, int precio) {
		this();
		this.nombre = nombre;
		this.precio = precio;
	}

	//Encapsulamiento
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	//ToString
	
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + "]";
	}
}
