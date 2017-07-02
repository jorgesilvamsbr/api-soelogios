package br.com.so.elogios.aplicacao.avaliacao;

import static org.junit.Assert.*;

import java.util.List;

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
import br.com.so.elogios.repositorio.avaliacao.AvaliacaoServicoAdapter;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;
import br.com.so.elogios.repositorio.municipio.MunicipioServicoAdapter;
import br.com.so.elogios.repositorio.usuario.UsuarioServicoAdapter;
import br.com.so.elogios.testebase.TesteBase;

@RunWith(SpringRunner.class)
public class ConsultaAvaliacaoTest extends TesteBase {

	@Autowired
	private MunicipioServicoAdapter municipioServicoAdapter;
	
	@Autowired
	private EmpresaServicoAdapter empresaServicoAdapter;

	@Autowired
	private UsuarioServicoAdapter usuarioServicoAdapter;

	@Autowired
	private AvaliacaoServicoAdapter avaliacaoServicoAdapter;

	@Autowired
	private ConsultaAvaliacao consultaAvalicao;

	@Test
	public void deve_consultar_todas_das_avaliacoes() throws Exception {
		criarAvaliacao();
		criarAvaliacao();
		criarAvaliacao();
		criarAvaliacao();
		
		List<AvaliacaoResponse> avaliacoes = consultaAvalicao.buscarTodas();
		
		assertTrue(avaliacoes.stream().allMatch(avaliacao -> avaliacao.getId() != null));
	}
	
	@Test
	public void deve_consultar_avaliacoes_de_uma_empresa() throws Exception {
		Empresa burgerKing = EmpresaBuilder.novo().comNome("Burger King").criar();
		criarAvaliacaoPara(burgerKing);
		Empresa masseria = EmpresaBuilder.novo().comNome("Masseria").criar();
		criarAvaliacaoPara(masseria);
		criarAvaliacaoPara(masseria);
		
		List<AvaliacaoResponse> avaliacoes = consultaAvalicao.buscarPeloIdDaEmpresa(masseria.getId());
		
		assertTrue(avaliacoes.stream().allMatch(avaliacao -> avaliacao.getIdDaEmpresa().equals(masseria.getId())));
	}
	
	@Test
	public void deve_consultar_avaliacoes_de_um_usuario() throws Exception {
		Usuario jorgeSilva = UsuarioBuilder.novo().comNome("Jorge Silva").criar();
		usuarioServicoAdapter.salvar(jorgeSilva);
		Usuario jonasMadureira = UsuarioBuilder.novo().comNome("Jonas Madureira").criar();
		usuarioServicoAdapter.salvar(jonasMadureira);
		criarAvaliacaoParaOUsuario(jonasMadureira);
		criarAvaliacaoParaOUsuario(jorgeSilva);
		criarAvaliacaoParaOUsuario(jorgeSilva);
		
		List<AvaliacaoResponse> avaliacoes = consultaAvalicao.buscarPeloIdDoUsuario(jorgeSilva.getId());
		
		assertTrue(avaliacoes.stream().allMatch(avaliacao -> avaliacao.getIdDoUsuario().equals(jorgeSilva.getId())));
	}
	
	@Test
	public void deve_buscar_uma_avaliacao_especifica() throws Exception {
		Avaliacao avaliacao = criarAvaliacao();
		
		AvaliacaoResponse avaliacaoResponse = consultaAvalicao.buscarPorId(avaliacao.getId());
		
		assertEquals(avaliacao.getDescricao(), avaliacaoResponse.getDescricao());
	}

	private void criarAvaliacaoParaOUsuario(Usuario usuario) throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		Empresa empresa = EmpresaBuilder.novo().comEndereco(criarEndereco()).criar();
		empresaServicoAdapter.salvar(empresa);
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comEmpresa(empresa).comUsuario(usuario).criar();
		avaliacaoServicoAdapter.salvar(avaliacao);
	}

	private void criarAvaliacaoPara(Empresa empresa) throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		empresa.alterarEndereco(criarEndereco());
		salvarAvaliacaoParaA(empresa);
	}

	private Avaliacao criarAvaliacao() throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		Empresa empresa = EmpresaBuilder.novo().comEndereco(criarEndereco()).criar();
		return salvarAvaliacaoParaA(empresa);
	}

	private Avaliacao salvarAvaliacaoParaA(Empresa empresa) throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		empresaServicoAdapter.salvar(empresa);
		Usuario usuario = UsuarioBuilder.novo().criar();
		usuarioServicoAdapter.salvar(usuario);
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comEmpresa(empresa).comUsuario(usuario).criar();
		avaliacaoServicoAdapter.salvar(avaliacao);
		return avaliacao;
	}

	private Endereco criarEndereco() throws ExcecaoDeCampoObrigatorio {
		Municipio municipio = new Municipio("Dourados");
		municipioServicoAdapter.salvar(municipio);
		return EnderecoBuilder.novo().comMunicipio(municipio).criar();
	}
}
