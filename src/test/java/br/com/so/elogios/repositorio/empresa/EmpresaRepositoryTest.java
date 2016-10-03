package br.com.so.elogios.repositorio.empresa;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class EmpresaRepositoryTest {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Test
	public void deve_salvar_uma_empresa() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().criar();

		empresaRepository.save(empresa);

		assertNotNull(empresa.getId());
	}
}
