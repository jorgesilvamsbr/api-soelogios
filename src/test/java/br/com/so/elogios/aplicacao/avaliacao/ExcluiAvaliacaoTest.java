package br.com.so.elogios.aplicacao.avaliacao;

import static org.junit.Assert.*;

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
public class ExcluiAvaliacaoTest extends TesteBase {

	@Autowired private EmpresaServicoAdapter empresaServicoAdapter;
	@Autowired private UsuarioServicoAdapter usuarioServicoAdapter;
	@Autowired private AvaliacaoServicoAdapter avaliacaoServicoAdapter;
	@Autowired private MunicipioServicoAdapter municipioServicoAdapter;
	@Autowired private ExcluiAvaliacao excluiAvaliacao;
	
	@Test
	public void deve_excluir_uma_avaliacao_especifica() throws Exception {
		Avaliacao avaliacao = criarAvaliacao();
		AvaliacaoRequest avaliacaoRequest = new AvaliacaoRequest();
		avaliacaoRequest.setId(avaliacao.getId());
		
		excluiAvaliacao.excluir(avaliacaoRequest);
		
		assertNull(avaliacaoServicoAdapter.buscarPorId(avaliacaoRequest.getId()));
	}
	
	private Avaliacao criarAvaliacao() throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		Empresa empresa = EmpresaBuilder.novo().comEndereco(criarEndereco()).criar();
		empresaServicoAdapter.salvar(empresa);
		Usuario usuario = UsuarioBuilder.novo().criar();
		usuarioServicoAdapter.salvar(usuario);
		Avaliacao avaliacao = AvaliacaoBuilder.novo().comEmpresa(empresa).comUsuario(usuario ).criar();
		avaliacaoServicoAdapter.salvar(avaliacao);
		return avaliacao;
	}
	
	private Endereco criarEndereco() throws ExcecaoDeCampoObrigatorio {
		Municipio municipio = new Municipio("Dourados");
		municipioServicoAdapter.salvar(municipio);
		return EnderecoBuilder.novo().comMunicipio(municipio).criar();
	}
}
