package com.sulasoftwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sulasoftwares.model.Produto;
import com.sulasoftwares.repository.Produtos;
import com.sulasoftwares.repository.filter.ProdutoFilter;
import com.sulasoftwares.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;

	private ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;
	private Produto produtoSelceionado;

	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}
	
	public void excluir(){
		produtos.remover(produtoSelceionado);
		produtosFiltrados.remove(produtoSelceionado);
		
		FacesUtil.addInfoMessage("Produto " + produtoSelceionado.getSku() + "Excluido com sucesso.");
	}

	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);

	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public Produto getProdutoSelceionado() {
		return produtoSelceionado;
	}

	public void setProdutoSelceionado(Produto produtoSelceionado) {
		this.produtoSelceionado = produtoSelceionado;
	}

}
