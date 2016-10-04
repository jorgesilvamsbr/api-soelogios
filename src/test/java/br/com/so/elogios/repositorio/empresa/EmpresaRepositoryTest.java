package br.com.so.elogios.repositorio.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.endereco.EnderecoBuilder;
import br.com.so.elogios.dominio.endereco.Municipio;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.repositorio.empresa.EmpresaRepository;
import br.com.so.elogios.repositorio.municipio.MunicipioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmpresaRepositoryTest {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private MunicipioRepository municipioRepository;

	@Test
	public void deve_salvar_uma_empresa() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();

		empresaRepository.save(empresa);

		assertNotNull(empresa.getId());
	}
	
	@Test
	public void deve_buscar_todas_as_empresas_cadastradas() throws Exception {
		int quantidadeEsperada = 3;
		empresaCriar(quantidadeEsperada);

		assertEquals(quantidadeEsperada, empresaRepository.count());
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
		empresaRepository.save(empresa);
		return empresa;
	}	
}