package br.com.so.elogios.dominio.empresa;

import javax.persistence.Entity;

import br.com.so.elogios.dominio.Entidade.EntidadeBase;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Empresa extends EntidadeBase{

	private String nome;
	
	private String descricao;

	public Empresa() {
	}
	
	public Empresa(String nome, String descricao) throws ExcecaoDeCampoObrigatorio {
		validarCamposObrigatorios(nome, descricao);
		this.nome = nome;
		this.descricao = descricao;
	}

	private void validarCamposObrigatorios(String nome, String descricao) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoNulo(nome, "O nome da empresa não informado")
		.quandoVazio(nome, "O nome da empresa não pode ser vazio")
		.quandoNulo(descricao, "Descrição da empresa não informado")
		.quandoVazio(descricao, "Descrição da empresa não pode ser vazio!")
		.entaoDispara();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
