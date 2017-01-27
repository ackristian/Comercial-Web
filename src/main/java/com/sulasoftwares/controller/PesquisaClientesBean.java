package com.sulasoftwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sulasoftwares.model.Cliente;
import com.sulasoftwares.repository.Clientes;
import com.sulasoftwares.repository.filter.ClienteFilter;
import com.sulasoftwares.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	private ClienteFilter filtro;
	private List<Cliente> clientesFiltrados;
	private Cliente clienteSelceionado;

	public PesquisaClientesBean() {
		filtro = new ClienteFilter();
	}
	
	public void pesquisar() {
		clientesFiltrados = clientes.filtrados(filtro);

	}
	
	public void excluir(){
		clientes.remover(clienteSelceionado);
		clientesFiltrados.remove(clienteSelceionado);
		
		FacesUtil.addInfoMessage("Cliente " + clienteSelceionado.getNome() + " Excluido com sucesso!");
	}

	
	

	public Cliente getClienteSelceionado() {
		return clienteSelceionado;
	}

	public void setClienteSelceionado(Cliente clienteSelceionado) {
		this.clienteSelceionado = clienteSelceionado;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}
}
