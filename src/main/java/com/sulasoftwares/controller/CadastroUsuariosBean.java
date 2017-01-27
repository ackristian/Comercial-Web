package com.sulasoftwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sulasoftwares.model.Grupo;
import com.sulasoftwares.model.Usuario;
import com.sulasoftwares.repository.Grupos;
import com.sulasoftwares.service.CadastroUsuarioService;
import com.sulasoftwares.service.NegocioException;
import com.sulasoftwares.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuariosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Inject
    private CadastroUsuarioService cadastroUsuarioService;
    
    @Inject
    private Grupos grupos;
    
    private List<Grupo> gruposDisponiveis;
    private Usuario usuario;
    private Grupo novoGrupo;
    private Grupo removeGrupo;

    public CadastroUsuariosBean() {
        limpar();
    }

    
    private void limpar() {
        
        usuario = new Usuario();
    }

 
    public void salvar() {

        try {
            this.usuario = cadastroUsuarioService.salvar(usuario);
            limpar();
            FacesUtil.addInfoMessage("Usuário cadastrado com sucesso!");
        } catch (NegocioException e) {
            FacesUtil.addInfoMessage(e.getMessage());
        }
    }

    public void adicionarGrupo() {
        this.usuario.getGrupos().add(novoGrupo);
    	}

    public void removerGrupo() { // remove da lista o grupo do usuario
        this.usuario.getGrupos().remove(removeGrupo);
    	}

    public void inicializar() {
        if (FacesUtil.isNotPostBack()) {
            //System.out.println("inicializando o pre-render...");
            gruposDisponiveis = grupos.todos();
        }
        
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Grupo getNovoGrupo() {
        return novoGrupo;
    }

    
    public void setNovoGrupo(Grupo novoGrupo) {
        this.novoGrupo = novoGrupo;
    }

    public List<Grupo> getGruposDisponiveis() {
        return gruposDisponiveis;
    }

    public void setGruposDisponiveis(List<Grupo> gruposDisponiveis) {
        this.gruposDisponiveis = gruposDisponiveis;
    }

    public Grupo getRemoveGrupo() {
        return removeGrupo;
    }

    public void setRemoveGrupo(Grupo removeGrupo) {
        this.removeGrupo = removeGrupo;
    }
    public boolean isEditando() {
        return this.usuario.getId() != null;
    }
}