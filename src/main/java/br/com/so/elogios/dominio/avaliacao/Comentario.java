package br.com.so.elogios.dominio.avaliacao;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.com.so.elogios.dominio.Entidade.EntidadeBase;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.Usuario;

@Entity
public class Comentario extends EntidadeBase {

	private String descricao;
	
	@OneToOne
	private Avaliacao avaliacao;
	
	@OneToOne
	private Usuario usuario;

	public Comentario(String descricao, Avaliacao avaliacao, Usuario usuario) throws ExcecaoDeCampoObrigatorio {
		validarCamposObrigatorios(descricao, avaliacao, usuario);
		this.usuario = usuario;
		this.descricao = descricao;
		this.avaliacao = avaliacao;
	}

	private void validarCamposObrigatorios(String descricao, Avaliacao avaliacao, Usuario usuario) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoNulo(avaliacao, "A avaliacao não foi informada")
		.quandoVazio(descricao, "A descricao esta vazia")
		.quandoNulo(usuario, "Usuario não informado")
		.entaoDispara();
	}

	public String getDescricao() {
		return descricao;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void alterarDescricao(String descricaoNova) throws DescricaoInvalida {
		validar(descricaoNova);
		descricao = descricaoNova;
	}

	private void validar(String descricao) throws DescricaoInvalida {
		if(descricao.isEmpty()){
			throw new DescricaoInvalida();
		}
	}
}
