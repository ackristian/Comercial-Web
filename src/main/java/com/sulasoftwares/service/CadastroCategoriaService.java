package com.sulasoftwares.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sulasoftwares.model.Categoria;
import com.sulasoftwares.model.Usuario;
import com.sulasoftwares.repository.Categorias;
import com.sulasoftwares.repository.Usuarios;
import com.sulasoftwares.util.jpa.Transactional;

public class CadastroCategoriaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	Categorias categorias;
	
	@Transactional
	public Categoria salvar(Categoria categoria){
		
		
		if (categoria.getDescricao() == null || categoria.getDescricao().trim().equals("")) {
			throw new NegocioException("O nome da Categoria é obrigatório");
		}
		
		
	
		/*Usuario usuarioEmail = usuarios.porEmail(usuario.getEmail());
		if(usuarioEmail !=null && !usuarioEmail.equals(usuario)){
			throw new NegocioException("Já existe um Login com o Usuario informado.");
		}*/
			return categorias.guardar(categoria);
	}
}
