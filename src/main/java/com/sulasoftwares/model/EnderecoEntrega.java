package com.sulasoftwares.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class EnderecoEntrega implements Serializable {

	private static final long serialVersionUID = 1L;

	private String rua;
	private String numero;
	private String complemento;
	private String cidade;
	private String prov;
	private String cep;

	//@NotBlank
	@Size(max = 150)
	@Column(length = 150)
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	//@NotBlank
	@Size(max = 20)
	@Column(length = 20)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	@Size(max=150)
	@Column(length = 150)
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	//@NotBlank 
	@Size(max=60)
	@Column(length = 60)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	//@NotBlank 
	@Size(max=60)
	@Column(length = 60)
	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}
	//@NotBlank 
	@Size(max=9)
	@Column(length = 9)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
