package br.com.so.elogios.aplicacao.avaliacao;

public class ComentarioResponse {
	
	private Long id;
	
	private String descricao;
	
	private Long idDaAvaliacao;
	
	private Long idDoUsuario;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdDaAvaliacao() {
		return idDaAvaliacao;
	}

	public void setIdDaAvaliacao(Long idDaAvaliacao) {
		this.idDaAvaliacao = idDaAvaliacao;
	}

	public Long getIdDoUsuario() {
		return idDoUsuario;
	}

	public void setIdDoUsuario(Long idDoUsuario) {
		this.idDoUsuario = idDoUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
