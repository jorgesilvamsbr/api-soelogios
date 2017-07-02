package br.com.so.elogios.dominio.endereco;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Embeddable
public class Endereco {

	private String enderecoCompleto;
	private String cep;
	
	@JoinColumn
	@OneToOne
	private Municipio municipio;

	Endereco() {
	}
	
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

	public void alterarEnderecoCompleto(String enderecoCompleto) throws ExcecaoDeCampoObrigatorio {
		validarSeEstaVazio(enderecoCompleto, "Endereco não informado");
		this.enderecoCompleto = enderecoCompleto;
	}

	public void alterarCep(String cep) throws ExcecaoDeCampoObrigatorio {
		validarSeEstaVazio(cep, "O cep não foi informado");
		this.cep = cep;
	}
	
	public void alterarMunicipio(Municipio municipio) throws ExcecaoDeCampoObrigatorio {
		validarSeEstaNulo(municipio, "Municpio não informado");
		this.municipio = municipio;
	}
	
	private void validarSeEstaNulo(Municipio municipio, String mensagem) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoNulo(municipio, mensagem)
		.entaoDispara();
	}

	private void validarSeEstaVazio(String campo, String mensagem) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoVazio(campo, mensagem)
		.entaoDispara();
	}
}
