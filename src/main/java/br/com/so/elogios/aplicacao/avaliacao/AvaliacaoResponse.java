package br.com.so.elogios.aplicacao.avaliacao;

import br.com.so.elogios.dominio.avaliacao.TipoDeAvaliacao;

public class AvaliacaoResponse {

	private Long id;
	private Long idDaEmpresa;
	private Long idDoUsuario;
	private int curtidas;
	private String descricao;
	private TipoDeAvaliacao tipoDeAvaliacao;

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setIdDaEmpresa(Long idDaEmpresa) {
		this.idDaEmpresa = idDaEmpresa;
	}
	
	public Long getIdDaEmpresa() {
		return idDaEmpresa;
	}

	public void setIdDoUsuario(Long idDoUsuario) {
		this.idDoUsuario = idDoUsuario;
	}
	
	public Long getIdDoUsuario() {
		return idDoUsuario;
	}

	public void setCurtidas(int curtidas) {
		this.curtidas = curtidas;
	}
	
	public int getCurtidas() {
		return curtidas;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
		
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setTipo(TipoDeAvaliacao tipoDeAvaliacao) {
		this.tipoDeAvaliacao = tipoDeAvaliacao;
	}
	
	public TipoDeAvaliacao getTipoDeAvaliacao() {
		return tipoDeAvaliacao;
	}
}