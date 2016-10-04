package br.com.so.elogios.aplicacao.empresa;

public class EmpresaResponse {

	private String nome;
	private Long id;

	public EmpresaResponse(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public EmpresaResponse() {
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
}