package br.com.so.elogios.aplicacao.avaliacao;

import br.com.so.elogios.aplicacao.usuario.UsuarioRequest;

public class ComentarioRequest {

	private String descricao;
	private AvaliacaoRequest avaliacaoRequest;
	private UsuarioRequest usuarioRequest;
	private Long id;

	public ComentarioRequest(String descricao, AvaliacaoRequest avaliacaoRequest,
			UsuarioRequest usuarioRequest) {
				this.setDescricao(descricao);
				this.setAvaliacaoRequest(avaliacaoRequest);
				this.setUsuarioRequest(usuarioRequest);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public AvaliacaoRequest getAvaliacaoRequest() {
		return avaliacaoRequest;
	}

	public void setAvaliacaoRequest(AvaliacaoRequest avaliacaoRequest) {
		this.avaliacaoRequest = avaliacaoRequest;
	}

	public UsuarioRequest getUsuarioRequest() {
		return usuarioRequest;
	}

	public void setUsuarioRequest(UsuarioRequest usuarioRequest) {
		this.usuarioRequest = usuarioRequest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
