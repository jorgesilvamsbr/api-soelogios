package br.com.so.elogios.dominio.avaliacao;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.Usuario;
import br.com.so.elogios.dominio.usuario.UsuarioBuilder;

public class AvaliacaoTest {

	private static final String VAZIO = "";
	private static final Empresa NULA = null;

	@Test
	public void deve_conter_uma_descricao() throws Exception {
		String descricaoEsperada = "estou testando";

		Avaliacao avaliacao = AvaliacaoBuilder.novo().comDescricao(descricaoEsperada).criar();

		assertEquals(descricaoEsperada, avaliacao.getDescricao());
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

	@Test
	public void deve_conter_uma_empresa() throws Exception {
		Empresa empresaEsperada = EmpresaBuilder.novo().comNome("Burger King").criar();

		Avaliacao avaliacao = AvaliacaoBuilder.novo().comEmpresa(empresaEsperada).criar();

		assertEquals(empresaEsperada.getNome(), avaliacao.getEmpresa().getNome());
	}

	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_cadastrar_empresa_nula() throws Exception {
		AvaliacaoBuilder.novo().comEmpresa(NULA).criar();
	}

	@Test
	public void deve_ser_possivel_curtir_uma_avaliacao() throws Exception {
		int qtdDecurtidasEsperadas = 3;
		Avaliacao avaliacao = AvaliacaoBuilder.novo().criar();

		avaliacao.curtir();
		avaliacao.curtir();
		avaliacao.curtir();

		assertEquals(qtdDecurtidasEsperadas, avaliacao.obterCurtidas());
	}

	@Test
	public void uma_avaliacao_deve_conter_um_usuario() throws Exception {
		String nome = "Patricia da Silva";
		Usuario usuarioEsperado = UsuarioBuilder.novo().comNome(nome).criar();

		Avaliacao avaliacao = AvaliacaoBuilder.novo().comUsuario(usuarioEsperado).criar();

		assertEquals(usuarioEsperado.getNome(), avaliacao.getUsuario().getNome());
	}

	@Test
	public void deve_ser_possivel_alterar_uma_descricao() throws Exception {
		String descricaoEsperada = "Nova descricao";
		Avaliacao avaliacao = AvaliacaoBuilder.novo().criar();

		avaliacao.alterarDescricao(descricaoEsperada);
		
		assertEquals(descricaoEsperada, avaliacao.getDescricao());
	}
}