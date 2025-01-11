package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
@Table(name = "Alumnos")
public class Alumno{
	
	//ENTIDAD ALUMNO 
	
	@Id// Primary KEY
	@GeneratedValue(strategy = GenerationType.IDENTITY)//AUTOINCREMENTAL
	private Long id;
	
	@Column(name="Nombre")
	private String nombre;
	private String apellido;
	
	@Column(unique=true, nullable = false)//este dato sera unico y no nulo
	private int  matricula;
	@Column(unique=true, nullable = false)
	private String curp;
	
	
	@ManyToMany(mappedBy = "alumnos", fetch = FetchType.EAGER)
	private List<Curso> cursos = new ArrayList<>();
	
	private LocalDateTime createdAt;//fecha automatica del alumno
	

	public Alumno() {
		super();
	}
	

	public Alumno(String nombre, String apellido, int matricula, String curp) {
		this();
		this.nombre = nombre;
		this.apellido = apellido;
		this.matricula = matricula;
		this.curp = curp;
	}


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

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", matricula=" + matricula
				+ ", curp=" + curp + ", cursos=" + cursos + ", createdAt=" + createdAt + "]";
	}
	
	
}
