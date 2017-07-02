package br.com.so.elogios.aplicacao.avaliacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.aplicacao.usuario.UsuarioRequest;
import br.com.so.elogios.dominio.avaliacao.Avaliacao;
import br.com.so.elogios.dominio.avaliacao.AvaliacaoBuilder;
import br.com.so.elogios.dominio.avaliacao.Comentario;
import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.endereco.EnderecoBuilder;
import br.com.so.elogios.dominio.endereco.Municipio;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.EmailInvalido;
import br.com.so.elogios.dominio.usuario.Usuario;
import br.com.so.elogios.dominio.usuario.UsuarioBuilder;
import br.com.so.elogios.repositorio.avaliacao.AvaliacaoServicoAdapter;
import br.com.so.elogios.repositorio.avaliacao.ComentarioServicoAdapter;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;
import br.com.so.elogios.repositorio.municipio.MunicipioServicoAdapter;
import br.com.so.elogios.repositorio.usuario.UsuarioServicoAdapter;
import br.com.so.elogios.testebase.TesteBase;

@RunWith(SpringRunner.class)
public class AdicionaComentarioTest extends TesteBase {

	@Autowired
	private UsuarioServicoAdapter usuarioServicoAdapter;
	
	@Autowired
	private MunicipioServicoAdapter municipioServicoAdapter;
	
	@Autowired
	private EmpresaServicoAdapter empresaServicoAdapter;
	
	private Empresa empresa;
	private Usuario usuario;
	
	@Autowired
	private AvaliacaoServicoAdapter avaliacaoServicoAdapter;
	
	@Autowired
	private ComentarioServicoAdapter comentarioServicoAdapter;
	
	@Autowired
	private AdicionaComentario adicionarComentario;

	@Before
	public void init() throws ExcecaoDeCampoObrigatorio, EmailInvalido{
		empresa = criarEmpresa();
		usuario = criarUsuario();
	}
	
	@Test
	public void deve_adicionar_um_comentario() throws Exception {
		String descricaoDoComentarioEsperada = "Achei muito bom também.";
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comEmpresa(empresa).comUsuario(usuario).criar();
		avaliacaoServicoAdapter.salvar(avaliacao);
		AvaliacaoRequest avaliacaoRequest = new AvaliacaoRequest();
		avaliacaoRequest.setId(avaliacao.getId());
		UsuarioRequest usuarioRequest = new UsuarioRequest();
		usuarioRequest.setId(usuario.getId());
		ComentarioRequest comentarioRequest = new ComentarioRequest(descricaoDoComentarioEsperada, avaliacaoRequest, usuarioRequest);

		adicionarComentario.adicionar(comentarioRequest);
		
		Comentario comentario = comentarioServicoAdapter.buscarPorId(comentarioRequest.getId());
		assertEquals(descricaoDoComentarioEsperada, comentario.getDescricao());
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
