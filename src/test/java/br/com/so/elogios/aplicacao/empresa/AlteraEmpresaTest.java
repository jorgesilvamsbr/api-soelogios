package br.com.so.elogios.aplicacao.empresa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.repositorio.empresa.EmpresaRepository;
import br.com.so.elogios.repositorio.municipio.MunicipioRepository;
import br.com.so.elogios.testebase.TesteBase;

@RunWith(SpringRunner.class)
public class AlteraEmpresaTest extends TesteBase {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private MunicipioRepository municipioRepository;

	@Autowired
	private AlteraEmpresa alteraEmpresa;

	@Autowired
	private ConsultaEmpresa consultaEmpresa;

	@Test
	public void deve_ser_possivel_alterar_o_nome_da_empresa() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().comNome("DigithoBrasil").criar();
		salvar(empresa);
		EmpresaRequest empresaRequest = MapearEmpresa.mapearParaRequest(empresa);
		String novoNome = "Burguer King";
		empresaRequest.setNome(novoNome);
		
		alteraEmpresa.alterar(empresaRequest);
		
		EmpresaResponse empresaResponse = consultaEmpresa.buscarPorId(empresaRequest.getId());
		assertEquals(novoNome, empresaResponse.getNome());
	}

	private void salvar(Empresa empresa) throws ExcecaoDeCampoObrigatorio {
		municipioRepository.save(empresa.getEndereco().getMunicipio());
		empresaRepository.save(empresa);
	}
}
