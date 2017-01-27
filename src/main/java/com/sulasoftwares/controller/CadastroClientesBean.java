package com.sulasoftwares.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sulasoftwares.model.Cliente;
import com.sulasoftwares.model.Endereco;
import com.sulasoftwares.service.CadastroClienteService;
import com.sulasoftwares.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClientesBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroClienteService cadastroClienteService;
	@Inject
	private Endereco endereco;
	
	private Cliente cliente;
	private Endereco enderecoSelecionado;
	private List<Endereco> enderecos;
	
	public CadastroClientesBean(){
		limpar();
	}
	
	public void inicializar() {
		this.limpar();
		
	}
	
	private void limpar(){
		this.cliente = new Cliente();
		this.enderecos = new ArrayList<>();
		this.endereco = new Endereco();
		
	}
	
	public void salvar(){
		this.cliente = cadastroClienteService.salvar(this.cliente);
		limpar();
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
	}
	
	public void incluirEndereco(){
		
			//if (endereco != null) {
				this.endereco.setCliente(this.cliente);
				this.cliente.getEnderecos().add(this.endereco);
				this.endereco = new Endereco();
				
				//endereco.setCliente(cliente);
				//endereco = null;
			//}
		}
	
	public void removerEndereco() {
		if (endereco != null) {
			this.endereco.setCliente(this.cliente);
			cliente.getEnderecos().remove(enderecoSelecionado);
		
			endereco = null;
		}
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isEditando(){
		return this.cliente.getId() != null;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}
	
}
