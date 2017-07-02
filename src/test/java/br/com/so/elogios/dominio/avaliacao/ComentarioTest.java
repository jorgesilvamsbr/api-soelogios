package br.com.so.elogios.dominio.avaliacao;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.Usuario;
import br.com.so.elogios.dominio.usuario.UsuarioBuilder;

public class ComentarioTest {
	private static final String VAZIO = "";

	@Test
	public void deve_conter_uma_descricao() throws Exception {
		String descricaoEsperada = "Uhuul obrigado pela dica vou ir neste restaurante! :D";
		
		Comentario comentario = ComentarioBuilder.novo().comDescricao(descricaoEsperada).criar();
		
		assertEquals(descricaoEsperada, comentario.getDescricao());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_permitir_uma_descricao_vazia() throws Exception {
		ComentarioBuilder.novo().comDescricao(VAZIO).criar();
	}
	
	@Test
	public void deve_conter_um_elogio() throws Exception {
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comTipo(TipoDeAvaliacao.ELOGIO).comDescricao("Muito bom este lugar!").criar();
		
		Comentario comentario = ComentarioBuilder.novo().comAvaliacao(avaliacao).criar();
		
		assertEquals(avaliacao.getDescricao(), comentario.getAvaliacao().getDescricao());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_permitir_uma_avaliacao_nula() throws Exception {
		ComentarioBuilder.novo().comAvaliacao(null).criar();
	}
	
	@Test
	public void deve_conter_um_comentario() throws Exception {
		String email = "soelogios@soelogios.com.br";
		Usuario usuarioEsperado = UsuarioBuilder.novo().comEmail(email).criar();
		
		Comentario comentario = ComentarioBuilder.novo().comUsuario(usuarioEsperado).criar();
		
		assertEquals(usuarioEsperado.getEmail(), comentario.getUsuario().getEmail());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_permitir_um_comentario_nulo() throws Exception {
		ComentarioBuilder.novo().comUsuario(null).criar();
	}
	
	@Test
	public void deve_ser_possivel_alterar_a_descricao_do_comentario() throws Exception {
		String descricaoAntiga = "Descricao Antiga";
		String descricaoNova = "Descricao Nova";
		Comentario comentario = ComentarioBuilder.novo().comDescricao(descricaoAntiga).criar();
		
		comentario.alterarDescricao(descricaoNova);
		
		assertEquals(descricaoNova, comentario.getDescricao());
	}
	
	@Test(expected = DescricaoInvalida.class)
	public void nao_deve_permitir_alterar_a_descricao_se_a_mesma_for_vazia() throws Exception {
		String descricaoAntiga = "Descricao Antiga";
		Comentario comentario = ComentarioBuilder.novo().comDescricao(descricaoAntiga).criar();
		
		comentario.alterarDescricao(VAZIO);
	}
}