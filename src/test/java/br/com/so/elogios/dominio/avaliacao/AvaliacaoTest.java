package br.com.so.elogios.dominio.avaliacao;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class AvaliacaoTest {
	
	private static final String VAZIO = "";

	@Test
	public void deve_conter_uma_descricao() throws Exception {
		String descricaoEsperada = "estou testando";
		
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comDescricao(descricaoEsperada).criar();
		
		assertEquals(descricaoEsperada , avaliacao.getDescricao());
	}

	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_permitir_cadastrar_uma_descricao_vazia() throws Exception {
		AvaliacaoBuilder.novo().comDescricao(VAZIO).criar();
	}
	
	@Test
	public void uma_avaliacao_deve_ser_do_tipo_elogio() throws Exception {
		
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comTipo(TipoDeAvaliacao.ELOGIO).criar();
		
		assertEquals(TipoDeAvaliacao.ELOGIO, avaliacao.getTipo());
	}
	
	@Test
	public void uma_avaliacao_deve_ser_do_tipo_critica() throws Exception {
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comTipo(TipoDeAvaliacao.CRITICA).criar();
		
		assertEquals(TipoDeAvaliacao.CRITICA, avaliacao.getTipo());
	}
}