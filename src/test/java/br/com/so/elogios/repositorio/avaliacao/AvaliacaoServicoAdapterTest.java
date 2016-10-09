package br.com.so.elogios.repositorio.avaliacao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.dominio.avaliacao.Avaliacao;
import br.com.so.elogios.dominio.avaliacao.AvaliacaoBuilder;
import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.endereco.EnderecoBuilder;
import br.com.so.elogios.dominio.endereco.Municipio;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.EmailInvalido;
import br.com.so.elogios.dominio.usuario.Usuario;
import br.com.so.elogios.dominio.usuario.UsuarioBuilder;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;
import br.com.so.elogios.repositorio.municipio.MunicipioServicoAdapter;
import br.com.so.elogios.repositorio.usuario.UsuarioServicoAdapter;
import br.com.so.elogios.testebase.TesteBase;

@RunWith(SpringRunner.class)
public class AvaliacaoServicoAdapterTest extends TesteBase {

	@Autowired
	private AvaliacaoServicoAdapter avaliacaoServicoAdapter; 
	
	@Autowired
	private EmpresaServicoAdapter empresaServicoAdapter;

	@Autowired
	private UsuarioServicoAdapter usuarioServicoAdapter;

	@Autowired
	private MunicipioServicoAdapter municipioServicoAdapter;

	private Usuario usuario;

	private Endereco endereco;

	@Before
	public void init() throws EmailInvalido, ExcecaoDeCampoObrigatorio{
		usuario = UsuarioBuilder.novo().criar();
		usuarioServicoAdapter.salvar(usuario);
		Municipio municipio = new Municipio("Campo Grande");
		municipioServicoAdapter.salvar(municipio);
		endereco = EnderecoBuilder.novo().comMunicipio(municipio).criar();
	}
	
	@Test
	public void deve_buscar_uma_avaliacao_de_uma_empresa_especifica() throws Exception {
		Usuario soElogios = UsuarioBuilder.novo().comEmail("soelogios@soelogios.com.br").criar();
		usuarioServicoAdapter.salvar(soElogios);
		criarAvaliacaoParaOUsuario(usuario);
		criarAvaliacaoParaOUsuario(soElogios);
		criarAvaliacaoParaOUsuario(soElogios);
		
		List<Avaliacao> avaliacoes = avaliacaoServicoAdapter.buscarPorIdDaEmresa(soElogios.getId());
		
		assertTrue(avaliacoes.stream().allMatch(ava -> ava.getEmpresa().getId().equals(soElogios.getId())));
	}
	
	@Test
	public void deve_buscar_uma_avaliacao_de_um_usuario_especifico() throws Exception {
		Empresa empresa = EmpresaBuilder.novo().comEndereco(endereco).criar();
		empresaServicoAdapter.salvar(empresa);
		Avaliacao avaliacao1 = AvaliacaoBuilder.novo().comUsuario(usuario).comEmpresa(empresa).criar();
		avaliacaoServicoAdapter.salvar(avaliacao1);
		Avaliacao avaliacao2 = AvaliacaoBuilder.novo().comUsuario(usuario).comEmpresa(empresa).criar();
		avaliacaoServicoAdapter.salvar(avaliacao2);
		
		List<Avaliacao> avaliacoes = avaliacaoServicoAdapter.buscarPorIdDaEmresa(empresa.getId());
		
		assertTrue(avaliacoes.stream().allMatch(ava -> ava.getEmpresa().getId().equals(empresa.getId())));
	}
	
	private void criarAvaliacaoParaOUsuario(Usuario soElogios) throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		Empresa empresa = EmpresaBuilder.novo().comEndereco(endereco).criar();
		empresaServicoAdapter.salvar(empresa);
		Avaliacao avaliacao2 = AvaliacaoBuilder.novo().comUsuario(soElogios).comEmpresa(empresa).criar();
		avaliacaoServicoAdapter.salvar(avaliacao2);
	}
}
