package br.com.so.elogios.dominio.endereco;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class EnderecoTest {
	private static final Municipio NULO = null;
	private static final String VAZIO = "";

	@Test
	public void deve_conter_o_endereco_completo() throws Exception {
		String enderecoEsperado = "Av. Bom Pastor, 328 - Vila Vilas Boas";

		Endereco endereco = EnderecoBuilder.novo().comEnderecoCompleto(enderecoEsperado).criar();

		assertEquals(enderecoEsperado, endereco.getEnderecoCompleto());
	}
	
	@Test
	public void deve_conter_o_cep() throws Exception {
		String cepEsperado = "79051-852";

		Endereco endereco = EnderecoBuilder.novo().comCep(cepEsperado).criar();

		assertEquals(cepEsperado, endereco.getCep());
	}
	
	@Test
	public void deve_conter_um_municipio() throws Exception {
		Municipio municipioEsperado = new Municipio("Campo Grande");
		
		Endereco endereco = EnderecoBuilder.novo().comMunicipio(municipioEsperado).criar();
		
		assertEquals(municipioEsperado.getNome(), endereco.getMunicipio().getNome());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void o_municipio_nao_deve_ser_nulo() throws Exception {
		EnderecoBuilder.novo().comMunicipio(NULO).criar();
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void o_endereco_completo_nao_deve_ser_vazio() throws Exception {
		EnderecoBuilder.novo().comEnderecoCompleto(VAZIO).criar();
	}
}