package br.com.so.elogios.repositorio.empresa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.endereco.EnderecoBuilder;
import br.com.so.elogios.dominio.endereco.Municipio;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.repositorio.municipio.MunicipioRepository;
import br.com.so.elogios.testebase.TesteBase;

@RunWith(SpringRunner.class)
public class EmpresaServicoAdapterTest extends TesteBase{

	@Autowired
	private EmpresaServicoAdapter empresaServicoAdapter;
	
	@Autowired
	private MunicipioRepository municipioRepository;

	@Test
	public void deve_salvar_uma_empresa() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();

		empresaServicoAdapter.salvar(empresa);

		assertNotNull(empresa.getId());
	}
	
	@Test
	public void deve_buscar_todas_a_quantidade_de_todas_empresas_cadastradas() throws Exception {
		int quantidadeEsperada = 3;
		empresaCriar(quantidadeEsperada);

		assertEquals(quantidadeEsperada, empresaServicoAdapter.obterQuantidadeTotal());
	}
	
	@Test
	public void deve_excluir_empresa_cadastrada() throws Exception {
		criarEmpresa();
		Empresa empresaASerExcluida = criarEmpresa();
		
		empresaServicoAdapter.excluir(empresaASerExcluida);
		
		assertNull(empresaServicoAdapter.buscar(empresaASerExcluida));
		assertEquals(1, empresaServicoAdapter.obterQuantidadeTotal());
	}
	
	@Test
	public void deve_buscar_todas_as_empresas_cadastradas() throws Exception {
		int qtdEsperada = 5;
		empresaCriar(qtdEsperada);
		
		List<Empresa> empresas = empresaServicoAdapter.buscarTodas();
		
		assertEquals(qtdEsperada, empresas.size());
		assertTrue(empresas.stream().allMatch(emp -> emp.getId() != null));
	}

	private void empresaCriar(int qtd) throws ExcecaoDeCampoObrigatorio {
		for(int i=0; i< qtd; i++){
			criarEmpresa();
		}
	}
	
	public Empresa criarEmpresa() throws ExcecaoDeCampoObrigatorio{
		Municipio municipio = new Municipio("Campo Grande");
		municipioRepository.save(municipio);
		Endereco endereco = EnderecoBuilder.novo().comMunicipio(municipio).criar();
		Empresa empresa = EmpresaBuilder.novo().comEndereco(endereco).criar();
		empresaServicoAdapter.salvar(empresa);
		return empresa;
	}	
}