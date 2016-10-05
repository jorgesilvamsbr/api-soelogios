package br.com.so.elogios.dominio.empresa;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import br.com.so.elogios.dominio.Entidade.EntidadeBase;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Empresa extends EntidadeBase{

	private String nome;
	
	private String ramoDeNegocio;

	@Embedded
	private Endereco endereco;

	Empresa() {
	}
	
	public Empresa(String nome, String ramoDeNegocio, Endereco endereco) throws ExcecaoDeCampoObrigatorio {
		validarCamposObrigatorios(nome, ramoDeNegocio, endereco);
		this.endereco = endereco;
		this.nome = nome;
		this.ramoDeNegocio = ramoDeNegocio;
	}

	private void validarCamposObrigatorios(String nome, String descricao, Endereco endereco) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoVazio(nome, "O nome da empresa não pode ser vazio")
		.quandoVazio(descricao, "Descrição da empresa não pode ser vazio!")
		.quandoNulo(endereco, "O endereco nao foi informado")
		.entaoDispara();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getRamoDeNegocio() {
		return ramoDeNegocio;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void alterarNome(String novoNome) throws ExcecaoDeCampoObrigatorio {
		validarSeOCampoEstaVazio(novoNome, "O nome da empresa esta vazio");
		nome = novoNome;
	}

	public void alterarEndereco(Endereco novoEndereco) throws ExcecaoDeCampoObrigatorio {
		validarSeOCampoEstaNulo(novoEndereco, "Endereço informado é inválido");
		endereco = novoEndereco;
	}

	private void validarSeOCampoEstaNulo(Endereco campo, String mensagem) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio().quandoNulo(campo, mensagem).entaoDispara();
	}

	public void alterarRamoDeNegocio(String ramo) throws ExcecaoDeCampoObrigatorio {
		validarSeOCampoEstaVazio(ramo, "O ramo de negócio informado esta vazio");
		ramoDeNegocio = ramo;
	}

	private void validarSeOCampoEstaVazio(String campo, String mensagem) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio().quandoVazio(campo, mensagem).entaoDispara();
	}
}
