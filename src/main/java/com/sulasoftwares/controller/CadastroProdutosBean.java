package com.sulasoftwares.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.sulasoftwares.model.Categoria;
import com.sulasoftwares.model.Produto;
import com.sulasoftwares.repository.Categorias;
import com.sulasoftwares.service.CadastroProdutoService;
import com.sulasoftwares.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias;
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	private Produto produto;
	private Categoria categoriaPai;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subCategoria;
	
	public CadastroProdutosBean(){
		limpar();
	}
	
	public void inicializar(){
		System.out.println("inicializar");
		
		if(FacesUtil.isNotPostBack()){
			categoriasRaizes = categorias.raizes();
			
			if(this.categoriaPai != null){
				carregarSubCategorias();
			}
		}
		
	}
	public void carregarSubCategorias(){
		subCategoria = categorias.subCategoriasDe(categoriaPai);
	}

	private void limpar(){
		produto = new Produto();
		categoriaPai = null;
		subCategoria = new ArrayList<>();
	}
	
	public void salvar(){
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}

	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if(this.produto !=null){
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}
	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categriaPai) {
		this.categoriaPai = categriaPai;
	}
	
	public List<Categoria> getSubCategoria() {
		return subCategoria;
	}
	public boolean isEditando(){
		return this.produto.getId() != null;
	}
}
