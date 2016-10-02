package br.com.so.elogios.dominio.usuario;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class Usuario {
	private String nome;
	private String email;
	private String senha;
	
	public Usuario(String nome, String email, String senha) throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		validarCamposObrigatorios(nome, email, senha);
		validarEmail(email);		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	private void validarEmail(String email) throws EmailInvalido {
		if(ValidaEmail.validar(email)){
			throw new EmailInvalido();
		}
	}

	private void validarCamposObrigatorios(String nome, String email, String senha) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoVazio(nome, "O nome não pode ser vazio")
		.quandoVazio(email, "O email não pode ser vazio")
		.quandoVazio(senha, "A senha não pode ser vazia")
		.entaoDispara();
	}

	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
}