package br.com.so.elogios.dominio.empresa;

import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.endereco.EnderecoBuilder;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class EmpresaBuilder {

	private String nome;
	private String ramo;
	private Endereco endereco;

	public EmpresaBuilder() throws ExcecaoDeCampoObrigatorio {
		this.nome = "SoElogios";
		this.ramo = "Empresa fornecedora de informacoes";
		this.endereco = EnderecoBuilder.novo().criar();
	}
	
	public static EmpresaBuilder novo() throws ExcecaoDeCampoObrigatorio {
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
	
	public EmpresaBuilder comEndereco(Endereco endereco) {
		this.endereco = endereco;
		return this;
	}

	public Empresa criar() throws ExcecaoDeCampoObrigatorio {
		return new Empresa(nome, ramo, endereco);
	}
}
