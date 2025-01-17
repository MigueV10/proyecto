package com.coderhouse.models;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="clientes")
public class Cliente {
    
    @Id // Primary KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENTAL
    private Long id;
    
    @Column(name="Nombre")
    private String nombre;
    private String apellido;
    private String email;
    @Column(unique = true, nullable = false)
//    @Schema(description = "DNI del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "30111222")
    private int ine;

    @Column(name = "num_cliente")
//    @Schema(description = "Numero de cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String numCliente;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
//    @Schema(description = "Fecha de creación del cliente", requiredMode = Schema.RequiredMode.AUTO, example = "2025-01-13T10:21:27.402135")
    private LocalDateTime createdAt;

//    // Relación OneToMany con la entidad Venta
//    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Venta> ventas; // Asegúrate de que exista la clase Venta con el mapeo adecuado
   
}