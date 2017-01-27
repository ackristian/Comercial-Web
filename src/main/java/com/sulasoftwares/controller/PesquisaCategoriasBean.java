package com.sulasoftwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sulasoftwares.model.Categoria;
import com.sulasoftwares.repository.Categorias;
import com.sulasoftwares.repository.filter.CategoriaFilter;
import com.sulasoftwares.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCategoriasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	private CategoriaFilter filtro;
	private List<Categoria> categoriasFiltradas;
	private Categoria categoriaSelceionada;
	private Categoria categoria; 

	
	public PesquisaCategoriasBean() {
		filtro = new CategoriaFilter();
	}
	
	public void pesquisar() {
		categoriasFiltradas = categorias.filtrados(filtro);
	}
	
	public void excluir(){
		categorias.remover(categoriaSelceionada);
		categoriasFiltradas.remove(categoriaSelceionada);
		
		FacesUtil.addInfoMessage("Categoria " + categoriaSelceionada.getDescricao() + "Excluido com sucesso.");
	}

	
	public Categoria getCategoriaSelceionada() {
		return categoriaSelceionada;
	}

	public void setCategoriaSelceionada(Categoria categoriaSelceionada) {
		this.categoriaSelceionada = categoriaSelceionada;
	}

	public CategoriaFilter getFiltro() {
		return filtro;
	}

	public List<Categoria> getCategoriasFiltradas() {
		return categoriasFiltradas;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
