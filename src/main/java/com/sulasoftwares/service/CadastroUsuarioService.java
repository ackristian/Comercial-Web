package com.sulasoftwares.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sulasoftwares.model.Usuario;
import com.sulasoftwares.repository.Usuarios;
import com.sulasoftwares.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	Usuarios usuarios;
	
	@Transactional
	public Usuario salvar(Usuario usuario){
		
		
		if (usuario.getNome() == null || usuario.getNome().trim().equals("")) {
			throw new NegocioException("O nome do Usuario é obrigatório");
		}
		
		if (usuario.getEmail() == null || usuario.getEmail().trim().equals("")) {
			throw new NegocioException("O email do Usuario é obrigatório");
		}
		
		if (usuario.getSenha() == null || usuario.getSenha().trim().equals("")) {
			throw new NegocioException("A senha do Usuario é obrigatória");
		}
	
		
		/*Usuario usuarioEmail = usuarios.porEmail(usuario.getEmail());
		if(usuarioEmail !=null && !usuarioEmail.equals(usuario)){
			throw new NegocioException("Já existe um Login com o Usuario informado.");
		}*/
			return usuarios.guardar(usuario);
	}
}
