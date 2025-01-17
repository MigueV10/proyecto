package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="detalle_venta")
public class DetalleVenta {

	@Id // Primary KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENTAL
//	private int detalleVentaId;
	private Long id;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "venta_id")
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name="producto_venta_id")
	private Producto producto;
	
	@SuppressWarnings("unused")
	private int cantidad;
	@SuppressWarnings("unused")
	private double precioUnitario;
//	@Column(name="precio",nullable=false)
//	private double precio;
	
}
