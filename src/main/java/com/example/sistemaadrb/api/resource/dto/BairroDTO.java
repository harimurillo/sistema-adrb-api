package com.example.sistemaadrb.api.resource.dto;

import com.example.sistemaadrb.api.model.Cidade;

public class BairroDTO {
	
	private Long codCidade;
	private String nomeBairro;
	private Cidade cidade;
	
	public Long getCodCidade() {
		return codCidade;
	}
	public void setCodCidade(Long codCidade) {
		this.codCidade = codCidade;
	}
	
	public String getNomeBairro() {
		return nomeBairro;
	}
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
