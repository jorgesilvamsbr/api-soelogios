package br.com.so.elogios.dominio.usuario;

import br.com.so.elogios.dominio.excecao.ExcecaoDeRegraDeNegocio;

public class EmailInvalido extends ExcecaoDeRegraDeNegocio {
	private static final long serialVersionUID = -5827271266245807253L;

	public EmailInvalido() {
		super("O email informado esta em um formato inválido");
	}	
}
