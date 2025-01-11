package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Categoria;
import com.coderhouse.repositories.CategoriaRepository;


@RestController//recibo solicitudes desde cualquier URL, donde mappearemos 
@RequestMapping("/api/categorias")
public class CategoriaControllers {

	
	@Autowired
	private CategoriaRepository categoriaRepository;// me permite acceso a todo el repositorio donde los metodos sean accecibles
	
	@GetMapping
	public List<Categoria> getAllCategorias(){//metodo que tgraifga ltodos los alumnos que existan enm la base de datos
		return categoriaRepository.findAll();//si quiero conseguir todos los alumnos que estan en la base de datos, se lo pido al repositorio
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCursoById(@PathVariable Long id){
		if(categoriaRepository.existsById(id)) {//retorno algo
			Categoria categoria = categoriaRepository.findById(id).get();
			return ResponseEntity.ok(categoria);
		}else {//si no existe va a retornar un responsEntity
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping
	public Categoria createCategoria(@RequestBody Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
}
