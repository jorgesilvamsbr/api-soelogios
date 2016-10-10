package br.com.so.elogios.aplicacao.avaliacao;

import static org.junit.Assert.*;

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
import br.com.so.elogios.repositorio.avaliacao.ComentarioServicoAdapter;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;
import br.com.so.elogios.repositorio.municipio.MunicipioServicoAdapter;
import br.com.so.elogios.repositorio.usuario.UsuarioServicoAdapter;
import br.com.so.elogios.testebase.TesteBase;

@RunWith(SpringRunner.class)
public class ExcluiComentarioTest extends TesteBase {

	@Autowired private UsuarioServicoAdapter usuarioServicoAdapter;
	@Autowired private MunicipioServicoAdapter municipioServicoAdapter;
	@Autowired private EmpresaServicoAdapter empresaServicoAdapter;
	@Autowired private ComentarioServicoAdapter comentarioServicoAdapter;
	@Autowired private ExcluiComentario excluirComentario;

	@Test
	public void deve_excluir_um_comentario_especifico() throws Exception {
		Comentario comentario = CriarComentario();
		ComentarioRequest comentarioRequest = new ComentarioRequest();
		comentarioRequest.setId(comentario.getId());
		
		excluirComentario.excluir(comentarioRequest);
		assertNull(comentarioServicoAdapter.buscarPorId(comentarioRequest.getId()));
	}

	private Comentario CriarComentario() throws EmailInvalido, ExcecaoDeCampoObrigatorio {
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comEmpresa(criarEmpresa()).comUsuario(criarUsuario()).criar();
		Comentario comentario = ComentarioBuilder.novo().comAvaliacao(avaliacao).comUsuario(criarUsuario()).comDescricao("test").criar();
		comentarioServicoAdapter.salvar(comentario);
		
		return comentario;
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
