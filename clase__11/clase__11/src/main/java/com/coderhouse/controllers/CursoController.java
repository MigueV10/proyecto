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


import com.coderhouse.models.Curso;
import com.coderhouse.repositories.CursoRepository;

@RestController//recibo solicitudes desde cualquier URL, donde mappearemos 
@RequestMapping("/api/cursos")//cada vez que yo reciba una solicitud en esta url
public class CursoController {

	@Autowired
	private CursoRepository cursoRepository;// me permite acceso a todo el repositorio donde los metodos sean accecibles
	
	@GetMapping
	public List<Curso> getAllCursos(){//metodo que tgraifga ltodos los alumnos que existan enm la base de datos
		return cursoRepository.findAll();//si quiero conseguir todos los alumnos que estan en la base de datos, se lo pido al repositorio
	}//mapeo distinto?
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> getCursoById(@PathVariable Long id){
		if(cursoRepository.existsById(id)) {//retorno algo
			Curso curso = cursoRepository.findById(id).get();
			return ResponseEntity.ok(curso);
		}else {//si no existe va a retornar un responsEntity
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping
	public Curso createCurso(@RequestBody Curso curso) {
		return cursoRepository.save(curso);
	}
}
