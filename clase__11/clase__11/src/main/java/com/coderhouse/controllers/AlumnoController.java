package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Alumno;
import com.coderhouse.service.AlumnoService;

//API REST/REST CONTROLLER


@RestController//recibo solicitudes desde cualquier URL, donde mappearemos 
@RequestMapping("/api/alumnos")//cada vez que yo reciba una solicitud en esta url
public class AlumnoController {//entra a este controlador!!
	
	
	//solicitudes, metodos
	//mi controlador quiera que acceda a mi repositorio
	//tengo que inyectar con @autowired
	
	@Autowired
	private AlumnoService alumnoService;// me permite acceso a todo el repositorio donde los metodos sean accecibles
	
	@GetMapping
	public ResponseEntity<List<Alumno>> getAllAlumnos(){
		
		try {
			
			List <Alumno> alumnos = alumnoService.getAllAlumnos();
			return ResponseEntity.ok(alumnos);//200 OK
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}
	
	//ALUMNO NO ENCONTRADO
	@GetMapping("/{id}")
	public ResponseEntity<Alumno> getAlumnoById(@PathVariable Long id){
		try {
			
			Alumno alumno = alumnoService.findById(id);//me lo trae con el finById
			return ResponseEntity.ok(alumno);// 200//ya lo tengo resuelto
		}catch(IllegalArgumentException e) {//si no encuentra el alumno regresalo
			return ResponseEntity.notFound().build(); //404
		}catch(Exception e){
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}
	
	//CREAR ALUMNO
	@PostMapping
	public ResponseEntity <Alumno> createAlumno(@RequestBody Alumno alumno) {//agarra todo el body que puso en el postman 
		try {
			Alumno alumnoCreado = alumnoService.saveAlumno(alumno);
			return ResponseEntity.status(HttpStatus.CREATED).body(alumnoCreado);//201 CREATED
	}catch(Exception e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<Alumno>updateAlumnoById(@PathVariable Long id, @RequestBody Alumno alumnoModificado){
		try {
			Alumno updateAlumno=alumnoService.updateAlumnoById(id, alumnoModificado);
			return ResponseEntity.ok(updateAlumno);
		}catch(IllegalArgumentException e) {//si no encuentra el alumno regresalo
			return ResponseEntity.notFound().build(); //404
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAlumnoById(@PathVariable Long id){
		try {
			alumnoService.deleteAlumnoById(id);
			return ResponseEntity.noContent().build();//204
		}catch(IllegalArgumentException e) {//si no encuentra el alumno regresalo
			return ResponseEntity.notFound().build(); //404
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}
}