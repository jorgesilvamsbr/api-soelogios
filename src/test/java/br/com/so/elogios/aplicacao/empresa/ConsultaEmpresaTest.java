package br.com.so.elogios.aplicacao.empresa;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;
import br.com.so.elogios.repositorio.municipio.MunicipioRepository;
import br.com.so.elogios.testebase.TesteBase;

@RunWith(SpringRunner.class)
public class ConsultaEmpresaTest extends TesteBase {

	@Autowired private ConsultaEmpresa consultaEmpresa;
	@Autowired private MunicipioRepository municipioRepository;
	@Autowired private EmpresaServicoAdapter empresaServicoAdapter;
	private int QTD_DE_EMRPESAS_NO_BANCO;

	@Before
	public void init(){
		QTD_DE_EMRPESAS_NO_BANCO = empresaServicoAdapter.buscarTodas().size();
	}
	
	@Test
	public void deve_consultar_todas_as_empresas_cadastrdas() throws Exception {
		int qtdEsperada = 3;
		criarEmpresa();
		criarEmpresa();
		criarEmpresa();
		
		List<EmpresaResponse> empresas = consultaEmpresa.buscarTodas();
		
		assertEquals(QTD_DE_EMRPESAS_NO_BANCO + qtdEsperada, empresas.size());
	}
	
	@Test
	public void deve_consultar_uma_empresa_em_especifico() throws Exception {
		criarEmpresa();
		Empresa empresaASerProcurada = criarEmpresa();
		
		EmpresaResponse empresaRetornada = consultaEmpresa.buscarPorId(empresaASerProcurada.getId());
		
		assertEquals(empresaRetornada.getId(), empresaASerProcurada.getId());
		assertEquals(empresaRetornada.getNome(), empresaASerProcurada.getNome());
	}

	private Empresa criarEmpresa() throws ExcecaoDeCampoObrigatorio {
		Empresa empresa = EmpresaBuilder.novo().criar();
		municipioRepository.save(empresa.getEndereco().getMunicipio());
		empresaServicoAdapter.salvar(empresa);
		return empresa;
	}
}
