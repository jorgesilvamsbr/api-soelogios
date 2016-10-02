package br.com.so.elogios.dominio.avaliacao;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class AvaliacaoBuilder {
	private TipoDeAvaliacao tipo;
	private String descricao;

	public AvaliacaoBuilder() {
		this.tipo = TipoDeAvaliacao.ELOGIO;
		this.descricao = "descricao";
	}
	
	public static AvaliacaoBuilder novo() {
		return new AvaliacaoBuilder();
	}

	public AvaliacaoBuilder comTipo(TipoDeAvaliacao critica) {
		this.tipo = critica;
		return this;
	}

	public AvaliacaoBuilder comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public Avaliacao criar() throws ExcecaoDeCampoObrigatorio {
		return new Avaliacao(descricao, tipo);
	}
}
