package br.com.so.elogios.dominio.empresa;

import javax.persistence.Entity;

import br.com.so.elogios.dominio.Entidade.EntidadeBase;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Empresa extends EntidadeBase{

	private String nome;
	
	private String ramoDeNegocio;

	private Empresa() {
	}
	
	public Empresa(String nome, String ramoDeNegocio) throws ExcecaoDeCampoObrigatorio {
		validarCamposObrigatorios(nome, ramoDeNegocio);
		this.nome = nome;
		this.ramoDeNegocio = ramoDeNegocio;
	}

	private void validarCamposObrigatorios(String nome, String descricao) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoVazio(nome, "O nome da empresa não pode ser vazio")
		.quandoVazio(descricao, "Descrição da empresa não pode ser vazio!")
		.entaoDispara();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getRamoDeNegocio() {
		return ramoDeNegocio;
	}
}
