package com.sulasoftwares.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sulasoftwares.model.Cliente;
import com.sulasoftwares.repository.Clientes;
import com.sulasoftwares.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	Clientes clientes;
	
	@Transactional
	public Cliente  salvar(Cliente cliente){
		
		Cliente clienteExistente = clientes.porDocumento(cliente.getDocomentoPessoal());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente com o número de Ducomento informado!");
		}
		
		
		return clientes.guardar(cliente);
		
		
		
	}
	
}
