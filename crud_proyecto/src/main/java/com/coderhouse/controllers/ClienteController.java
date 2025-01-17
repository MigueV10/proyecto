package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Cliente;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.services.ClienteServices;

@RestController //API REST
@RequestMapping("/api/clientes") //Mappear la solicitud
public class ClienteController { //cada vez q haga una soli va a entrar a este controlador

	
	@Autowired
	private ClienteServices clienteServices;
	
	@GetMapping
	public ResponseEntity <List<Cliente>> getAllClientes(){
		try {
			List<Cliente> clientes = clienteServices.getAllClients();
			return ResponseEntity.ok(clientes);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	//Busca a un cliente mediante su ID
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
		try {
			Cliente cliente = clienteServices.getClienteById(id);
			return ResponseEntity.ok(cliente);	
		}catch(IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//CREAR CLIENTE
		@PostMapping
		public ResponseEntity<Cliente> newCliente(@RequestBody Cliente cliente){
			try {
				Cliente clienteNuevo = clienteServices.newCliente(cliente);
				return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
			}
		}
}
