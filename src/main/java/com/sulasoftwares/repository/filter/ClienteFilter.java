package com.sulasoftwares.repository.filter;

import java.io.Serializable;

public class ClienteFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
		private String docomentoPessoal;
		private String nome;
		
		
		public String getDocomentoPessoal() {
			return docomentoPessoal;
		}
		public void setDocomentoPessoal(String docomentoPessoal) {
			this.docomentoPessoal = docomentoPessoal == null ? null: docomentoPessoal.toUpperCase();
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
}
