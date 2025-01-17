package com.coderhouse.models;


import java.time.LocalDateTime;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="ventas")
public class Venta {
	
	@Id // Primary KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENTAL
    private Long id;
	
//	@Column(nullable=false)
//    private int total;
    
	//Por cliente realiza varias ventas
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
//	//Cada venta puede incluir varios productos y cada producto puede pertener a varias ventas
//	@ManyToMany(fetch = FetchType.LAZY)//cargar los datos bajo demanda
//	@JoinTable(
//			name = "venta_producto", 
//			joinColumns = @JoinColumn(name = "venta_id"), 
//			inverseJoinColumns = @JoinColumn(name = "producto_id"))
//    @JsonIgnore
//	private List<Producto> productos = new ArrayList<>();
	
	 @JsonManagedReference
	    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    private List<DetalleVenta> detalles;

	    private LocalDateTime fecha;

	    private double precioTotal;

	    private int cantidadProductos;
	
	
}
