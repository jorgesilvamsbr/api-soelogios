package br.com.so.elogios.dominio.empresa;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class EmpresaBuilder {

	private String nome;
	private String ramo;

	public EmpresaBuilder() {
		this.nome = "SoElogios";
		this.ramo = "Empresa fornecedora de informacoes";
	}
	
	public static EmpresaBuilder novo() {
		return new EmpresaBuilder();
	}

	public EmpresaBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public EmpresaBuilder comRamoDeNeogocio(String ramo) {
		this.ramo = ramo;
		return this;
	}

	public Empresa criar() throws ExcecaoDeCampoObrigatorio {
		return new Empresa(nome, ramo);
	}
}
