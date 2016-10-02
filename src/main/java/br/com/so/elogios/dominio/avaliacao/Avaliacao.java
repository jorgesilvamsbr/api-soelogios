package br.com.so.elogios.dominio.avaliacao;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class Avaliacao {

	private String descricao;
	private TipoDeAvaliacao tipo;

	public Avaliacao(String descricao, TipoDeAvaliacao tipo) throws ExcecaoDeCampoObrigatorio {
		validarCamposObrigatorios(descricao);
		this.tipo = tipo;
		this.descricao = descricao;
	}

	private void validarCamposObrigatorios(String descricao) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoVazio(descricao, "Não é permitido informar uma descrição vazia")
		.entaoDispara();
	}

	public String getDescricao() {
		return descricao;
	}
	
	public TipoDeAvaliacao getTipo() {
		return tipo;
	}
	
}
