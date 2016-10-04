package br.com.so.elogios.aplicacao.empresa;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.dominio.endereco.Municipio;
import br.com.so.elogios.repositorio.empresa.EmpresaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdicionaEmpresaTest {
	@Autowired
	private AdicionaEmpresa adicionaEmpresa;
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Test
	public void deve_adicionar_uma_empresa() throws Exception {
		EmpresaRequest empresaRequest = new EmpresaRequest();
		empresaRequest.setNome("Marisa");
		empresaRequest.setRamo("Roupas");
		empresaRequest.setEnderecoDTO(new EnderecoDTO("Rua das Garças,289", "79081650", new Municipio("Campo Grande")));
		
		adicionaEmpresa.adicionar(empresaRequest);
		
		assertNotNull(empresaRepository.count());
	}
}
