package br.com.so.elogios.dominio.avaliacao;

import br.com.so.elogios.dominio.excecao.ExcecaoDeRegraDeNegocio;

public class DescricaoInvalida extends ExcecaoDeRegraDeNegocio{
	private static final long serialVersionUID = 7258054012742286126L;

	public DescricaoInvalida() {
		super("Descricao informada é inválida");
	}
}
