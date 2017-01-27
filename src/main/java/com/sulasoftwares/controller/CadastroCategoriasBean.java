package com.sulasoftwares.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sulasoftwares.model.Categoria;
import com.sulasoftwares.repository.Categorias;
import com.sulasoftwares.service.CadastroCategoriaService;
import com.sulasoftwares.service.NegocioException;
import com.sulasoftwares.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias;
	@Inject
	private CadastroCategoriaService cadastroCategoriaService;
	
	private List<Categoria> raizes;
	private Categoria categoria;
	private Categoria categoriaPai;
		
	public CadastroCategoriasBean(){
		limpar();
	}
	
	public void limpar(){
		categoria = new Categoria();
		raizes = new ArrayList<>();
	}
	
	public void salvar(){
		
		try{
			this.categoria = cadastroCategoriaService.salvar(categoria);
			limpar();
		 FacesUtil.addInfoMessage("Categoria cadastrada com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addInfoMessage(e.getMessage());
		}
	}
	 
	
	public void listarRaizes(){
		this.raizes = categorias.raizes();
	}
		
	 public void inicializar() {
	        if (FacesUtil.isNotPostBack()) {
	            raizes = categorias.raizes();
	        	}
	        }
	

	public List<Categoria> getRaizes() {
		return raizes;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}
	
}
