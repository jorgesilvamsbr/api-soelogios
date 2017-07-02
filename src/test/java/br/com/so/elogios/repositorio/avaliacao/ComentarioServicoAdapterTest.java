package br.com.so.elogios.repositorio.avaliacao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.dominio.avaliacao.Avaliacao;
import br.com.so.elogios.dominio.avaliacao.AvaliacaoBuilder;
import br.com.so.elogios.dominio.avaliacao.Comentario;
import br.com.so.elogios.dominio.avaliacao.ComentarioBuilder;
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
public class ComentarioServicoAdapterTest extends TesteBase{
	
	@Autowired
	private ComentarioServicoAdapter comentarioServicoAdapter;
	
	@Autowired
	private AvaliacaoServicoAdapter avaliacaoServicoAdapter;
	
	@Autowired
	private UsuarioServicoAdapter usuarioServicoAdapter;

	@Autowired
	private EmpresaServicoAdapter empresaServicoAdapter;

	@Autowired
	private MunicipioServicoAdapter municipioServicoAdapter;

	private Empresa empresa;

	private Usuario usuario;

	@Before
	public void init() throws ExcecaoDeCampoObrigatorio, EmailInvalido{
		empresa = criarEmpresa();
		usuario = criarUsuario();
	}

	@Test
	public void deve_buscar_comentarios_de_uma_avaliacao_especifica() throws Exception {
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comEmpresa(empresa).comUsuario(usuario).criar();
		Avaliacao avaliacao2 = AvaliacaoBuilder.novo().comEmpresa(empresa).comUsuario(usuario).criar();
		avaliacaoServicoAdapter.salvar(avaliacao);
		avaliacaoServicoAdapter.salvar(avaliacao2);
		Comentario comentario = ComentarioBuilder.novo().comAvaliacao(avaliacao).comUsuario(usuario).criar();
		Comentario comentario2 = ComentarioBuilder.novo().comAvaliacao(avaliacao2).comUsuario(usuario).criar();
		Comentario comentario3 = ComentarioBuilder.novo().comAvaliacao(avaliacao).comUsuario(usuario).criar();
		
		comentarioServicoAdapter.salvar(comentario);
		comentarioServicoAdapter.salvar(comentario2);
		comentarioServicoAdapter.salvar(comentario3);
		
		List<Comentario> comentarios = comentarioServicoAdapter.buscarPorIdDaAvaliacao(avaliacao.getId());
		
		assertTrue(comentarios.stream().allMatch(com -> comentario.getAvaliacao().getId().equals(avaliacao.getId())));
	}	
	
	private Usuario criarUsuario() throws EmailInvalido, ExcecaoDeCampoObrigatorio {
		Usuario usuario = UsuarioBuilder.novo().criar();
		usuarioServicoAdapter.salvar(usuario);
		return usuario;
	}
	
	private Empresa criarEmpresa() throws ExcecaoDeCampoObrigatorio {
		Municipio municipio = new Municipio("Campo Grande");
		municipioServicoAdapter.salvar(municipio);
		Endereco endereco = EnderecoBuilder.novo().comMunicipio(municipio).criar();
		Empresa empresa = EmpresaBuilder.novo().comEndereco(endereco).criar();
		empresaServicoAdapter.salvar(empresa);
		return empresa;
	}
}
