package br.com.so.elogios.dominio.avaliacao;

import javax.persistence.*;

import br.com.so.elogios.dominio.Entidade.EntidadeBase;
import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.Usuario;

@Entity
public class Avaliacao extends EntidadeBase {

	@OneToOne
	private Usuario usuario;

	@OneToOne
	private Empresa empresa;
	
	@Column(length=500)
	private String descricao;

	@Enumerated(EnumType.STRING)
	private TipoDeAvaliacao tipo;

	private int curtida;

	@Column(length=999999)
	private String imagem;

	public Avaliacao() {
	}
	
	public Avaliacao(String descricao, TipoDeAvaliacao tipo, Empresa empresa, Usuario usuario) throws ExcecaoDeCampoObrigatorio {
		validarCamposObrigatorios(descricao, empresa, usuario);
		this.usuario = usuario;
		this.empresa = empresa;
		this.descricao = descricao;
		this.tipo = tipo;
	}

	private void validarCamposObrigatorios(String descricao, Empresa empresa, Usuario usuario) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoNulo(empresa, "Deve informar uma empresa")
		.quandoVazio(descricao, "Não é permitido informar uma descrição vazia")
		.quandoNulo(usuario, "O usuario nao foi informado")
		.entaoDispara();
	}

	public String getDescricao() {
		return descricao;
	}
	
	public TipoDeAvaliacao getTipo() {
		return tipo;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void curtir() {
		curtida++;
	}

	public int obterCurtidas() {
		return curtida;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void alterarDescricao(String novaDescricao) throws ExcecaoDeCampoObrigatorio {
		validarSeNaoEstaVazio(novaDescricao, "Descrição informada esta vazia");
		this.descricao = novaDescricao;
	}

	private void validarSeNaoEstaVazio(String campo, String mensagem) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoVazio(campo, mensagem)
		.entaoDispara();
	}

	public void setImagem(String imagem) throws ExcecaoDeCampoObrigatorio {
		this.imagem = imagem;
	}

	public String getImagem() {
		return this.imagem;
	}
}