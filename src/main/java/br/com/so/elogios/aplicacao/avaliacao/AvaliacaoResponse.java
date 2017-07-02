package br.com.so.elogios.aplicacao.avaliacao;

import br.com.so.elogios.dominio.avaliacao.TipoDeAvaliacao;

public class AvaliacaoResponse {

	private Long id;
	private Long idDaEmpresa;
	private Long idDoUsuario;
	private int curtidas;
	private String descricao;
	private TipoDeAvaliacao tipoDeAvaliacao;
	private String nomeDoUsuario;
	private String emailDoUsuario;
	private String nomeDaEmpresa;
	private String enderecoCompletoDaEmpresa;
	private String imagem;

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

	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
	}
	
	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}

	public void setEmailDoUsuario(String emailDoUsuario) {
		this.emailDoUsuario = emailDoUsuario;
	}
	
	public String getEmailDoUsuario() {
		return emailDoUsuario;
	}

	public void setNomeDaEmpresa(String nomeDaEmpresa) {
		this.nomeDaEmpresa = nomeDaEmpresa;
	}
	
	public String getNomeDaEmpresa() {
		return nomeDaEmpresa;
	}

	public void setEndereoDaEmpresa(String enderecoCompletoDaEmpresa) {
		this.enderecoCompletoDaEmpresa = enderecoCompletoDaEmpresa;
	}
	
	public String getEnderecoCompletoDaEmpresa() {
		return enderecoCompletoDaEmpresa;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getImagem() {
		return imagem;
	}
}