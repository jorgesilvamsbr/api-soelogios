package br.com.so.elogios.aplicacao.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.endereco.Municipio;
import br.com.so.elogios.repositorio.empresa.EmpresaRepository;
import br.com.so.elogios.testebase.TesteBase;

@RunWith(SpringRunner.class)
public class AdicionaEmpresaTest extends TesteBase{
	
	@Autowired private AdicionaEmpresa adicionaEmpresa;
	@Autowired private EmpresaRepository empresaRepository;

	@Test
	public void deve_adicionar_uma_empresa() throws Exception {
		EmpresaRequest empresaRequest = criarEmpresa();
		
		adicionaEmpresa.adicionar(empresaRequest);
		
		assertNotNull(empresaRequest.getId());
	}
	
	@Test
	public void deve_verificar_que_uma_empresa_foi_adicionada_com_seus_detalhes() throws Exception {
		EmpresaRequest empresaRequest = criarEmpresa();
		
		adicionaEmpresa.adicionar(empresaRequest);
		
		Empresa empresa = empresaRepository.findOne(empresaRequest.getId());
		assertEquals(empresaRequest.getNome(), empresa.getNome());
		assertEquals(empresaRequest.getEnderecoCompleto(), empresa.getEndereco().getEnderecoCompleto());
	}	
	
	private EmpresaRequest criarEmpresa() {
		return new EmpresaRequest("Marisa Ltd.", "Roupas", criarEndereco());
	}

	private EnderecoDTO criarEndereco() {
		return new EnderecoDTO("Rua das Garças,289", "79081650", new Municipio("Campo Grande"));
	}
}
