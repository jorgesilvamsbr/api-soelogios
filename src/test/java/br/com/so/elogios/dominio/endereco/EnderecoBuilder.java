package br.com.so.elogios.dominio.endereco;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class EnderecoBuilder {

	private String cep;
	private String enderecoCompleto;
	private Municipio municipio;
	
	public EnderecoBuilder() {
		this.cep = "79116-502";
		this.enderecoCompleto = "Rua das garças, 223 - Jardim Invisivel";
		this.municipio = new Municipio("Campo Grande");
	}

	public static EnderecoBuilder novo() {
		return new EnderecoBuilder();
	}

	public EnderecoBuilder comCep(String cep) {
		this.cep = cep;
		return this;
	}
	
	public EnderecoBuilder comEnderecoCompleto(String enderecoEsperado) {
		enderecoCompleto = enderecoEsperado;
		return this;
	}
	
	public EnderecoBuilder comMunicipio(Municipio municipio) {
		this.municipio = municipio;
		return this;
	}

	public Endereco criar() throws ExcecaoDeCampoObrigatorio {
		return new Endereco(enderecoCompleto, cep, municipio);
	}
}