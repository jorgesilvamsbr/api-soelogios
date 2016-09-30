package br.com.so.elogios.dominio.endereco;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class Endereco {

	private String enderecoCompleto;
	private String cep;
	private Municipio municipio;

	public Endereco(String enderecoCompleto, String cep, Municipio municipio) throws ExcecaoDeCampoObrigatorio {
		validaCamposObrigatorios(enderecoCompleto, municipio);
		this.enderecoCompleto = enderecoCompleto;
		this.cep = cep;
		this.municipio = municipio;
	}

	private void validaCamposObrigatorios(String enderecoCompleto, Municipio municipio) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoNulo(municipio, "Municipio nao informado")
		.quandoVazio(enderecoCompleto, "Endereco completo esta vazio")
		.entaoDispara();
	}
	
	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public String getCep() {
		return cep;
	}
	
	public Municipio getMunicipio() {
		return municipio;
	}
}
