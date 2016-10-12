package br.com.so.elogios.aplicacao.avaliacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.so.elogios.aplicacao.empresa.EmpresaRequest;
import br.com.so.elogios.aplicacao.empresa.EnderecoDTO;
import br.com.so.elogios.aplicacao.usuario.UsuarioRequest;
import br.com.so.elogios.dominio.avaliacao.Avaliacao;
import br.com.so.elogios.dominio.avaliacao.TipoDeAvaliacao;
import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.empresa.EmpresaBuilder;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.endereco.EnderecoBuilder;
import br.com.so.elogios.dominio.endereco.Municipio;
import br.com.so.elogios.repositorio.avaliacao.AvaliacaoServicoAdapter;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;
import br.com.so.elogios.repositorio.municipio.MunicipioServicoAdapter;
import br.com.so.elogios.testebase.TesteBase;

@RunWith(SpringRunner.class)
public class AdicionaAvaliacaoTest extends TesteBase {

	@Autowired
	private AdicionaAvaliacao adicionaAvaliacao;
	
	@Autowired
	private AvaliacaoServicoAdapter avaliacaoServicoAdapter;
	
	@Autowired
	private EmpresaServicoAdapter empresaServicoAdapter;

	@Autowired
	private MunicipioServicoAdapter municipioServicoAdapter;

	private int QTD_DE_EMRPESAS_NO_BANCO;

	@Before
	public void init(){
		QTD_DE_EMRPESAS_NO_BANCO = empresaServicoAdapter.buscarTodas().size();
	}
	
	
	@Test
	public void deve_ser_possivel_informar_um_elogio_para_uma_empresa() throws Exception {
		String descricao = "Lanche muito da hora!";
		AvaliacaoRequest avaliacaoRequest = new AvaliacaoRequest(criarUsuarioRequest(), criarEmpresaRequest(), descricao, TipoDeAvaliacao.ELOGIO, "", "");
		
		adicionaAvaliacao.adicionar(avaliacaoRequest);
		
		Avaliacao avaliacaoRetornada = avaliacaoServicoAdapter.buscarPorId(avaliacaoRequest.getId());
		assertNotNull(avaliacaoRequest.getId());
		assertEquals(descricao, avaliacaoRetornada.getDescricao());
	}
	
	@Test
	public void ao_adicionar_mensagem_nao_deve_salvar_empresa_se_a_mesma_ja_existir() throws Exception {
		Municipio municipio = new Municipio("Dourados");
		municipioServicoAdapter.salvar(municipio );
		Endereco endereco = EnderecoBuilder.novo().comMunicipio(municipio).criar();
		Empresa empresa = EmpresaBuilder.novo().comNome("Empresa ++").comEndereco(endereco ).criar();
		empresaServicoAdapter.salvar(empresa);
		EmpresaRequest empresaRequest = new EmpresaRequest();
		empresaRequest.setId(empresa.getId());
		String descricao = "Lanche muito da hora!";
		AvaliacaoRequest avaliacaoRequest = new AvaliacaoRequest(criarUsuarioRequest(), empresaRequest, descricao, TipoDeAvaliacao.ELOGIO, "", "");
		
		adicionaAvaliacao.adicionar(avaliacaoRequest);
		
		assertEquals(QTD_DE_EMRPESAS_NO_BANCO + 1, empresaServicoAdapter.obterQuantidadeTotal());
	}

	private UsuarioRequest criarUsuarioRequest() {
		String nome = "Jorge Luiz Gomes da Silva";
		String email = "contato@soelogios.com.br";
		String senha = "senha123";
		UsuarioRequest usuarioRequest = new UsuarioRequest(nome, email, senha);
		return usuarioRequest;
	}

	private EmpresaRequest criarEmpresaRequest() {
		String nome = "Bugre Burger & CO";
		String ramo = "Fast Food";
		return new EmpresaRequest(nome, ramo, criarEnderecoDTO());
	}

	private EnderecoDTO criarEnderecoDTO() {
		Municipio municipio = new Municipio("Campo Grande");
		String cep = "79081-650";
		String enderecoCompleto = "Rua Maracaju, 9827 - Centro";
		return  new EnderecoDTO(enderecoCompleto, cep, municipio);
	}
}
