package br.com.so.elogios.dominio.usuario;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class Usuario {
	private String nome;
	private String email;
	
	public Usuario(String nome, String email) throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		validarCamposObrigatorios(nome, email);
		validarEmail(email);
		this.nome = nome;
		this.email = email;
	}

	private void validarEmail(String email) throws EmailInvalido {
		if(ValidaEmail.validar(email)){
			throw new EmailInvalido();
		}
	}

	private void validarCamposObrigatorios(String nome, String email) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoVazio(nome, "O nome não pode ser vazio")
		.quandoVazio(email, "O email não pode ser vazio")
		.entaoDispara();
	}

	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
}