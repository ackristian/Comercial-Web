package com.sulasoftwares.security;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sulasoftwares.model.Usuario;
import com.sulasoftwares.repository.Usuarios;
import com.sulasoftwares.repository.filter.UsuarioFilter;
import com.sulasoftwares.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	private UsuarioFilter filtro;
	private List<Usuario> usuariosFiltrados;
	private Usuario usuarioSelceionado;

	
	public PesquisaUsuariosBean() {
		filtro = new UsuarioFilter();
	}
	
	public void pesquisar() {
		usuariosFiltrados = usuarios.filtrados(filtro);
	}
	
	public void excluir(){
		usuarios.remover(usuarioSelceionado);
		usuariosFiltrados.remove(usuarioSelceionado);
		
		FacesUtil.addInfoMessage("Usuario " + usuarioSelceionado.getNome() + "Excluido com sucesso.");
	}
	
	

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public Usuario getUsuarioSelceionado() {
		return usuarioSelceionado;
	}

	public void setUsuarioSelceionado(Usuario usuarioSelceionado) {
		this.usuarioSelceionado = usuarioSelceionado;
	}

}
