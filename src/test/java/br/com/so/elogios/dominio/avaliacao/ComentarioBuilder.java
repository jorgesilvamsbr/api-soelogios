package br.com.so.elogios.dominio.avaliacao;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.EmailInvalido;
import br.com.so.elogios.dominio.usuario.Usuario;
import br.com.so.elogios.dominio.usuario.UsuarioBuilder;

public class ComentarioBuilder {

	private String descricao;
	private Avaliacao avaliacao;
	private Usuario usuario;

	public ComentarioBuilder() throws EmailInvalido, ExcecaoDeCampoObrigatorio {
		this.descricao = "descricao";
		this.avaliacao = AvaliacaoBuilder.novo().criar();
		this.usuario = UsuarioBuilder.novo().criar();
	}
	
	public static ComentarioBuilder novo() throws EmailInvalido, ExcecaoDeCampoObrigatorio {
		return new ComentarioBuilder();
	}
	
	public ComentarioBuilder comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public ComentarioBuilder comAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
		return this;
	}
	
	public ComentarioBuilder comUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}

	public Comentario criar() throws ExcecaoDeCampoObrigatorio {
		return new Comentario(descricao, avaliacao, usuario);
	}
}
