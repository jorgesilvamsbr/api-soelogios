package br.com.so.elogios.dominio.empresa;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class EmpresaBuilder {

	private String nome;
	private String descricao;

	public EmpresaBuilder() {
		this.nome = "SoElogios";
		this.descricao = "Empresa fornecedora de informacoes";
	}
	
	public static EmpresaBuilder novo() {
		return new EmpresaBuilder();
	}

	public EmpresaBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public EmpresaBuilder comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public Empresa criar() throws ExcecaoDeCampoObrigatorio {
		return new Empresa(nome, descricao);
	}
}
