package br.com.so.elogios.dominio.usuario;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class UsuarioBuilder {

	private String nome;
	private String email;
	private String senha;
	
	public UsuarioBuilder() {
		this.nome = "Natan Leite de Oliveira";
		this.email = "natan@leafweb.com.br";
		this.senha = "password";
	}

	public static UsuarioBuilder novo() {
		return new UsuarioBuilder();
	}

	public UsuarioBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public UsuarioBuilder comEmail(String email) {
		this.email = email;
		return this;
	}
	
	public Usuario criar() throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		return new Usuario(nome, email, senha);
	}

	public UsuarioBuilder comSenha(String senha) {
		this.senha = senha;
		return this;
		
	}
}