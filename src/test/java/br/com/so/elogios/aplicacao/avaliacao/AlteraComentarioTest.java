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
public class AlteraComentarioTest extends TesteBase {
	
	@Autowired
	private UsuarioServicoAdapter usuarioServicoAdapter;
	
	@Autowired
	private MunicipioServicoAdapter municipioServicoAdapter;
	
	@Autowired
	private EmpresaServicoAdapter empresaServicoAdapter;
	
	@Autowired
	private AvaliacaoServicoAdapter avaliacaoServicoAdapter;
	
	@Autowired
	private ComentarioServicoAdapter comentarioServicoAdapter;
	
	private Empresa empresa;
	
	private Usuario usuario;

	@Autowired
	private AdicionaComentario adicionarComentario;

	@Autowired
	private AlteraComentario alterarComentario;

	@Before
	public void init() throws ExcecaoDeCampoObrigatorio, EmailInvalido{
		empresa = criarEmpresa();
		usuario = criarUsuario();
	}
	
	@Test
	public void deve_alterar_um_comentario() throws Exception {
		String descricaoDoComentarioEsperada = "Achei muito bom também.";
		AvaliacaoRequest avaliacaoRequest = criarAvaliacaoRequest();
		UsuarioRequest usuarioRequest = criarUsuarioRequest();
		ComentarioRequest comentarioRequest = new ComentarioRequest(descricaoDoComentarioEsperada, avaliacaoRequest, usuarioRequest);
		adicionarComentario.adicionar(comentarioRequest);
		String descricaoDoComentarioAlterada = "Achei muito bom também, galeraaa.";
		comentarioRequest.setDescricao(descricaoDoComentarioAlterada);

		alterarComentario.alterar(comentarioRequest);
		
		Comentario comentario = comentarioServicoAdapter.buscarPorId(comentarioRequest.getId());
		assertEquals(descricaoDoComentarioAlterada, comentario.getDescricao());
		
	}

	private UsuarioRequest criarUsuarioRequest() {
		UsuarioRequest usuarioRequest = new UsuarioRequest();
		usuarioRequest.setId(usuario.getId());
		return usuarioRequest;
	}

	private AvaliacaoRequest criarAvaliacaoRequest() throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comEmpresa(empresa).comUsuario(usuario).criar();
		avaliacaoServicoAdapter.salvar(avaliacao);
		AvaliacaoRequest avaliacaoRequest = new AvaliacaoRequest();
		avaliacaoRequest.setId(avaliacao.getId());
		return avaliacaoRequest;
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
