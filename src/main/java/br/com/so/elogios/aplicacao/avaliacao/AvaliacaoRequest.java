package br.com.so.elogios.aplicacao.avaliacao;

import br.com.so.elogios.aplicacao.empresa.EmpresaRequest;
import br.com.so.elogios.aplicacao.usuario.UsuarioRequest;
import br.com.so.elogios.dominio.avaliacao.TipoDeAvaliacao;

public class AvaliacaoRequest {

	private UsuarioRequest usuarioRequest;
	private EmpresaRequest empresaRequest;
	private String descricao;
	private TipoDeAvaliacao avaliacao;
	private Long id;
	
	public AvaliacaoRequest() {
	}

	public AvaliacaoRequest(UsuarioRequest usuarioRequest, EmpresaRequest empresaRequest, String descricao,
			TipoDeAvaliacao elogio) {
				this.usuarioRequest = usuarioRequest;
				this.empresaRequest = empresaRequest;
				this.descricao = descricao;
				this.avaliacao = elogio;
	}

	public UsuarioRequest getUsuarioRequest() {
		return usuarioRequest;
	}

	public void setUsuarioRequest(UsuarioRequest usuarioRequest) {
		this.usuarioRequest = usuarioRequest;
	}

	public EmpresaRequest getEmpresaRequest() {
		return empresaRequest;
	}

	public void setEmpresaRequest(EmpresaRequest empresaRequest) {
		this.empresaRequest = empresaRequest;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoDeAvaliacao getTipoDeAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(TipoDeAvaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}