package br.com.so.elogios.aplicacao.empresa;

import br.com.so.elogios.dominio.endereco.Municipio;

public class EnderecoDTO {

	private String enderecoCompleto;
	private String cep;
	private Municipio municipio;

	public EnderecoDTO() {
	}
	
	public EnderecoDTO(String enderecoCompleto, String cep, Municipio municipio) {
		this.enderecoCompleto = enderecoCompleto;
		this.cep = cep;
		this.municipio = municipio;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}
	
	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}

	public Municipio getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
}