package br.com.so.elogios.dominio.avaliacao;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.com.so.elogios.dominio.Entidade.EntidadeBase;
import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.Usuario;

@Entity
public class Avaliacao extends EntidadeBase {

	private String descricao;
	private TipoDeAvaliacao tipo;
	@OneToOne
	private Empresa empresa;
	private int curtida;
	private Usuario usuario;

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
}