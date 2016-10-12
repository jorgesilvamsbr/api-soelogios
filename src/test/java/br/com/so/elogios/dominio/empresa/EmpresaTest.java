package br.com.so.elogios.dominio.empresa;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.endereco.EnderecoBuilder;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class EmpresaTest {
	private static final String VAZIO = "";
	private static final Endereco NULO = null;

	@Test
	public void uma_empresa_deve_conter_um_nome() throws Exception {
		String nomeEsperado = "CO Burgueria";
		
		Empresa empresa = EmpresaBuilder.novo().comNome(nomeEsperado).criar();
		
		assertEquals(nomeEsperado, empresa.getNome());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void o_nome_da_empresa_nao_pode_ser_vazio() throws Exception {
		EmpresaBuilder.novo().comNome(VAZIO).criar();
	}
	
	@Test
	public void uma_empresa_deve_conter_um_ramo_de_negocio() throws Exception {
		String ramoEsperada = "Restaurante";
		
		Empresa empresa = EmpresaBuilder.novo().comRamoDeNeogocio(ramoEsperada).criar();
		
		assertEquals(ramoEsperada, empresa.getRamoDeNegocio());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void o_ramo_de_negocio_da_empresa_nao_pode_ser_vazio() throws Exception {
		EmpresaBuilder.novo().comNome(VAZIO).criar();
	}
	
	@Test
	public void uma_empresa_deve_conter_seu_endereco() throws Exception {
		Endereco endereco = EnderecoBuilder.novo().criar();
		
		Empresa empresa = EmpresaBuilder.novo().comEndereco(endereco).criar();
		
		assertEquals(endereco.getEnderecoCompleto(), empresa.getEndereco().getEnderecoCompleto());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void o_endereco_da_empresa_nao_pode_ser_nulo() throws Exception {
		EmpresaBuilder.novo().comEndereco(NULO).criar();
	}
	
	@Test
	public void deve_ser_possivel_alterar_o_nome_da_empresa() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();
		String novoNome = "Novo nome";
		
		empresa.alterarNome(novoNome);
		
		assertEquals(novoNome, empresa.getNome());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_ser_possivel_informar_um_nome_em_branco() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();
		
		empresa.alterarNome(VAZIO);
	}
	
	@Test
	public void deve_ser_possivel_alterar_o_endereco_de_uma_empresa() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();
		Endereco novoEndereco = EnderecoBuilder.novo().comEnderecoCompleto("Rua da Divisão, 975").criar();
		
		empresa.alterarEndereco(novoEndereco);
		
		assertEquals(novoEndereco.getEnderecoCompleto(), empresa.getEndereco().getEnderecoCompleto());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_ser_possivel_informar_um_endereco_nulo() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();
		
		empresa.alterarEndereco(null);
	}
	
	@Test
	public void deve_ser_possivel_alterar_o_ramo_da_empresa() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();
		String novoRamoDeNegocio = "Novo ramo de negocio";
		
		empresa.alterarRamoDeNegocio(novoRamoDeNegocio);
		
		assertEquals(novoRamoDeNegocio, empresa.getRamoDeNegocio());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_ser_possivel_informar_um_ramo_em_branco() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();
		
		empresa.alterarRamoDeNegocio(VAZIO);
	}
	
	@Test
	public void deve_conter_a_url_da_imagem_google() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();
		String urlEsperada = "https://maps.gstatic.com/mapfiles/place_api/icons/shopping-71.png";
		
		empresa.inserirUrlIconeApiGoogle(urlEsperada);
		
		assertEquals(urlEsperada, empresa.getUrlIconeApiGoogle());
	}
	
	
	@Test(expected=ExcecaoDeCampoObrigatorio.class)
	public void a_url_do_icone_da_api_google_nao_deve_ser_vazia() throws Exception {
		EmpresaBuilder.novo().comUrlDoIconeApiGoogle("").criar();
	}

	@Test
	public void deve_conter_do_id_da_imagem_google() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();
		String idApiDoGoogle = "9fc2c43d89ea2096d982d470d7b9b68cd6f733cf";
		
		empresa.inserirIdApiGoogle(idApiDoGoogle);
		
		assertEquals(idApiDoGoogle, empresa.getIdApiDoGoogle());
	}
	
	
	@Test(expected=ExcecaoDeCampoObrigatorio.class)
	public void a_url_do_id_google_nao_deve_ser_vazia() throws Exception {
		EmpresaBuilder.novo().comIdApiGoogle("").criar();
	}
}