package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Alumno;
import com.coderhouse.repositories.AlumnoRepository;

import jakarta.transaction.Transactional;

@Service 
public class AlumnoService {
	
	@Autowired
	private AlumnoRepository alumnoRepository;//le decimos al repositorio que nos devuelva todos los alumnos 
	
	public List<Alumno> getAllAlumnos(){
		return alumnoRepository.findAll();
	}
	
	public Alumno findById(Long id) {
		return alumnoRepository.findById(id).orElseThrow(()
				-> new IllegalArgumentException("Alumno no encontrado"));
	}
	@Transactional
	public Alumno saveAlumno(Alumno alumno) {
		return alumnoRepository.save(alumno);
	}
	//UPDATE
	@Transactional
	public Alumno updateAlumnoById(Long id, Alumno alumnoDetails){//retorna un alumnod actualizado 
		Alumno alumno = alumnoRepository.findById(id).orElseThrow(()
				-> new IllegalArgumentException("Alumno no encontrado"));
		alumno.setNombre(alumnoDetails.getNombre());
		alumno.setApellido(alumnoDetails.getApellido());
		
		//validaciones con if
		if(alumnoDetails.getMatricula() != 0) {
			alumno.setMatricula(alumnoDetails.getMatricula());
		}
		if(alumnoDetails.getCurp() != null && alumnoDetails.getCurp().isEmpty()) {
			alumno.setCurp(alumnoDetails.getCurp());
		}
		return alumnoRepository.save(alumno);
	}
	//DELETE
	public void deleteAlumnoById(Long id) {
		if(!alumnoRepository.existsById(id)) {//valido que no existe
			throw new IllegalArgumentException("Alumno No encontrado.! ");
			}
			alumnoRepository.deleteById(id);//lo guarda
		}
	
}