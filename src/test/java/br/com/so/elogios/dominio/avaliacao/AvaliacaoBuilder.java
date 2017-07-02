package br.com.so.elogios.dominio.avaliacao;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.EmailInvalido;
import br.com.so.elogios.dominio.usuario.Usuario;
import br.com.so.elogios.dominio.usuario.UsuarioBuilder;

public class AvaliacaoBuilder {
	private TipoDeAvaliacao tipo;
	private String descricao;
	private Empresa empresa;
	private Usuario usuario;
	private String imagem;

	public AvaliacaoBuilder() throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		this.tipo = TipoDeAvaliacao.ELOGIO;
		this.descricao = "descricao";
		this.empresa = EmpresaBuilder.novo().criar();
		this.usuario = UsuarioBuilder.novo().criar();
		this.imagem = "lkjLKAShdLKSAJdlKASJdlkAJSdlHAKFJGHAKJSDJlkASLKAAAAdskdjasldjALsdjks";
	}
	
	public static AvaliacaoBuilder novo() throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		return new AvaliacaoBuilder();
	}

	public AvaliacaoBuilder comTipo(TipoDeAvaliacao critica) {
		this.tipo = critica;
		return this;
	}

	public AvaliacaoBuilder comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public AvaliacaoBuilder comEmpresa(Empresa empresa) {
		this.empresa = empresa;
		return this;
	}
	
	public AvaliacaoBuilder comUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}
	
	public AvaliacaoBuilder comImagem(String imagem) {
		this.imagem = imagem;
		return this;
	}
	
	public Avaliacao criar() throws ExcecaoDeCampoObrigatorio {
		Avaliacao avaliacao = new Avaliacao(descricao, tipo, empresa, usuario);
		avaliacao.setImagem(this.imagem);
		return avaliacao;
	}
}
