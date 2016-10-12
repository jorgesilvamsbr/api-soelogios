package br.com.so.elogios.dominio.empresa;

import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.endereco.EnderecoBuilder;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class EmpresaBuilder {

	private String nome;
	private String ramo;
	private Endereco endereco;
	private String urlDoIcone;
	private String idApiGoogle;

	public EmpresaBuilder() throws ExcecaoDeCampoObrigatorio {
		this.nome = "SoElogios";
		this.ramo = "Empresa fornecedora de informacoes";
		this.endereco = EnderecoBuilder.novo().criar();
		this.idApiGoogle = "SEM_ID";
		this.urlDoIcone = "urldoicone.com.br";
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
	
	public EmpresaBuilder comUrlDoIconeApiGoogle(String urlDoIcone) {
		this.urlDoIcone = urlDoIcone;
		return this;
	}
	
	public Empresa criar() throws ExcecaoDeCampoObrigatorio {
		Empresa empresa = new Empresa(nome, ramo, endereco);
		empresa.inserirUrlIconeApiGoogle(urlDoIcone);
		empresa.inserirIdApiGoogle(idApiGoogle);
		return empresa;
	}

	public EmpresaBuilder comIdApiGoogle(String idApiGoogle) {
		this.idApiGoogle = idApiGoogle;
		return this;
	}
}
