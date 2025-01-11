package com.coderhouse.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name="clientes")
public class Cliente {
    
    @Id // Primary KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENTAL
    private Long id;
    
    @Column(name="Nombre")
    private String nombre;
    private String apellido;
    private String email;

    // Relación OneToMany con la entidad Venta
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venta> ventas; // Asegúrate de que exista la clase Venta con el mapeo adecuado
    
    
    //ToString
    @Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + "]";
	}
    //Constructor de la superclase
	public Cliente() {
		super();
	}

    public Cliente( String nombre, String apellido, String email) {
		this();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}
	// Getters y setters
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}