package com.example.sistemaadrb.api.exceptionhandler.excecoes;

public class ExcecaoQuandoNaoExisteCidade extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String mensagem;

	public String mensagem() {
		return mensagem;
	}

	public void setMensagemLegal(String mensagem) {
		this.mensagem = mensagem;
	}

	public ExcecaoQuandoNaoExisteCidade(String mensagem) {
		super();
		this.mensagem = mensagem;
	}
	
}
