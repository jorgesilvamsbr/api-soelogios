package br.com.so.elogios.dominio.avaliacao;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class AvaliacaoBuilder {
	private TipoDeAvaliacao tipo;
	private String descricao;
	private Empresa empresa;

	public AvaliacaoBuilder() throws ExcecaoDeCampoObrigatorio {
		this.tipo = TipoDeAvaliacao.ELOGIO;
		this.descricao = "descricao";
		this.empresa = EmpresaBuilder.novo().criar();
	}
	
	public static AvaliacaoBuilder novo() throws ExcecaoDeCampoObrigatorio {
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
	
	public AvaliacaoBuilder comEmpresa(Empresa empresa) {
		this.empresa = empresa;
		return this;
	}
	
	public Avaliacao criar() throws ExcecaoDeCampoObrigatorio {
		return new Avaliacao(descricao, tipo, empresa);
	}
}
