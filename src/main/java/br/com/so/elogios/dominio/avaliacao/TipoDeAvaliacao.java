package br.com.so.elogios.dominio.avaliacao;

public enum TipoDeAvaliacao {
	CRITICA("Critica"),
	ELOGIO("Elogio");
	
	private String descricao;

	private TipoDeAvaliacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String descricao() {
		return descricao;
	}
}
