package br.com.so.elogios.aplicacao.empresa;

import br.com.so.elogios.dominio.endereco.Municipio;

public class EmpresaRequest {

	private String nome;
	private String ramo;
	private EnderecoDTO enderecoDTO;
	private Long id;

	public EmpresaRequest() {
	}
	
	public EmpresaRequest(String nome, String ramo, EnderecoDTO enderecoDTO) {
		this.nome = nome;
		this.ramo = ramo;
		this.enderecoDTO = enderecoDTO;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public String getRamo() {
		return ramo;
	}
	
	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getEnderecoCompleto() {
		return this.enderecoDTO.getEnderecoCompleto();
	}

	public String getCep() {
		return this.enderecoDTO.getCep();
	}

	public Municipio getMunicipio() {
		return this.enderecoDTO.getMunicipio();
	}
}