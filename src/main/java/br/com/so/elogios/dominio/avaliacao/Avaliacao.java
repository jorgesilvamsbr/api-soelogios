package br.com.so.elogios.dominio.avaliacao;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.com.so.elogios.dominio.Entidade.EntidadeBase;
import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Avaliacao extends EntidadeBase {

	private String descricao;
	private TipoDeAvaliacao tipo;
	@OneToOne
	private Empresa empresa;
	private int curtida;

	public Avaliacao(String descricao, TipoDeAvaliacao tipo, Empresa empresa) throws ExcecaoDeCampoObrigatorio {
		validarCamposObrigatorios(descricao, empresa);
		this.empresa = empresa;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	private void validarCamposObrigatorios(String descricao, Empresa empresa) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoNulo(empresa, "Deve informar uma empresa")
		.quandoVazio(descricao, "Não é permitido informar uma descrição vazia")
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
}
