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
public class ExcluiEmpresaTest extends TesteBase {

	private static final int UM = 1;
	@Autowired private MunicipioRepository municipioRepository;
	@Autowired private EmpresaRepository empresaRepository;
	@Autowired private ExcluiEmpresa excluiEmpresa;
	@Autowired private ConsultaEmpresa consultaEmpresa;


	@Test
	public void deve_excluir_uma_empresa_especifica() throws Exception {
		criarEmpresa();
		Empresa empresaASerExcluida = criarEmpresa();
		
		excluiEmpresa.excluir(empresaASerExcluida.getId());
		
		assertNull(empresaRepository.findOne(empresaASerExcluida.getId()));
		assertEquals(UM, consultaEmpresa.buscarTodas().size());
		
	}
	

	private Empresa criarEmpresa() throws ExcecaoDeCampoObrigatorio {
		Empresa empresa = EmpresaBuilder.novo().criar();
		municipioRepository.save(empresa.getEndereco().getMunicipio());
		empresaRepository.save(empresa);
		return empresa;
	}
}
