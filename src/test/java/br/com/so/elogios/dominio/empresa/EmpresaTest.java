package br.com.so.elogios.dominio.empresa;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class EmpresaTest {
	private static final String VAZIO = "";

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
	
}