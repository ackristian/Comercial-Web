package com.sulasoftwares.repository.filter;


import java.io.Serializable;

import com.sulasoftwares.model.Categoria;

public class CategoriaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descricao;
	private Categoria categoriaPai;

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

}
