package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Cliente;
import com.coderhouse.repositories.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	//GET ALL CLIENTES
	public List<Cliente> getAllClientes(){
		return clienteRepository.findAll();
	}
	
	//GET CLIENTE BY ID
	public Cliente getClienteById(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Cliente no encontrado"));
	}
	//CREAR CLIENTE
		@Transactional
		public Cliente newCliente(Cliente clienteInfo) {
			return clienteRepository.save(clienteInfo);
		}
		
		//ACTUALIZAR CLIENTE
		@Transactional
		public Cliente updateClienteById(Long id, Cliente clienteInfo) {
			Cliente cliente = clienteRepository.findById(id)
					.orElseThrow(()-> new IllegalArgumentException("Cliente no encontrado"));
			
			if(clienteInfo.getNombre() != null && !clienteInfo.getNombre().isEmpty()) {
				cliente.setNombre(clienteInfo.getNombre());
			}
			
			if(clienteInfo.getApellido() != null && !clienteInfo.getApellido().isEmpty()) {
				cliente.setApellido(clienteInfo.getApellido());
			}
			
			if(clienteInfo.getEmail() != null && !clienteInfo.getEmail().isEmpty()) {
				cliente.setEmail(clienteInfo.getApellido());
			}
			
			return clienteRepository.save(cliente);
		}
	//DELETE
	public void deleteClienteById(Long id) {
		if(!clienteRepository.existsById(id)) {//valido que no existe
			throw new IllegalArgumentException("Cliente No encontrado.! ");
			}
			clienteRepository.deleteById(id);//lo guarda
		}
	
}



































//public Cliente finById(Long id) {
//return clienteRepository.findById(id).orElseThrow(()
//		-> new IllegalArgumentException("Cliente no encontrado"));
//}
//@Transactional 
//public Cliente saveCliente(Cliente cliente) {
//return clienteRepository.save(cliente);
//}
//UPDATE
//@Transactional
//public Cliente updateClienteById(Long id, Cliente clienteDetails) {
//Cliente cliente = clienteRepository.findById(id).orElseThrow(()
//		-> new IllegalArgumentException("Cliente no encontrado"));
//cliente.setNombre(clienteDetails.getNombre());
//cliente.setApellido(clienteDetails.getApellido());
//
////VALIDACION
//if (clienteDetails.getId()!=0) {
//	cliente.setId(clienteDetails.getId());
//}
//return clienteRepository.save(cliente);
//}