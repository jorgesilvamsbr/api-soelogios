package br.com.so.elogios.aplicacao.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.so.elogios.aplicacao.empresa.EmpresaRequest;
import br.com.so.elogios.aplicacao.usuario.UsuarioRequest;
import br.com.so.elogios.dominio.avaliacao.Avaliacao;
import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.endereco.Municipio;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.EmailInvalido;
import br.com.so.elogios.dominio.usuario.Usuario;
import br.com.so.elogios.repositorio.avaliacao.AvaliacaoServicoAdapter;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;
import br.com.so.elogios.repositorio.municipio.MunicipioServicoAdapter;
import br.com.so.elogios.repositorio.usuario.UsuarioServicoAdapter;

@Service
public class AdicionaAvaliacao {

	private final AvaliacaoServicoAdapter avaliacaoServicoAdapter;
	private final EmpresaServicoAdapter empresaServicoAdapter;
	private final UsuarioServicoAdapter usuarioServicoAdapter;
	private final MunicipioServicoAdapter municipioServicoAdapter;
	
	@Autowired
	public AdicionaAvaliacao(AvaliacaoServicoAdapter avaliacaoServicoAdapter, EmpresaServicoAdapter empresaServicoAdapter,
			UsuarioServicoAdapter usuarioServicoAdapter, MunicipioServicoAdapter municipioServicoAdapter) {
		this.avaliacaoServicoAdapter = avaliacaoServicoAdapter;
		this.empresaServicoAdapter = empresaServicoAdapter;
		this.usuarioServicoAdapter = usuarioServicoAdapter;
		this.municipioServicoAdapter = municipioServicoAdapter;
	}
	
	@Transactional
	public void adicionar(AvaliacaoRequest avaliacaoRequest) throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		Avaliacao avaliacao = new Avaliacao(avaliacaoRequest.getDescricao(), avaliacaoRequest.getTipoDeAvaliacao(), criarEmpresa(avaliacaoRequest), criarUsuario(avaliacaoRequest));
		
		this.avaliacaoServicoAdapter.salvar(avaliacao);
		
		avaliacaoRequest.setId(avaliacao.getId());
	}

	private Usuario criarUsuario(AvaliacaoRequest avaliacaoRequest) throws ExcecaoDeCampoObrigatorio, EmailInvalido {
		UsuarioRequest usuarioRequest = avaliacaoRequest.getUsuarioRequest();
		if(usuarioRequest.getId() == null){
			Usuario usuario = new Usuario(usuarioRequest.getNome(), usuarioRequest.getEmail(), usuarioRequest.getSenha());
			usuarioServicoAdapter.salvar(usuario);
			return usuario;
		}
		return usuarioServicoAdapter.buscarPorId(usuarioRequest.getId());
	}

	private Empresa criarEmpresa(AvaliacaoRequest avaliacaoRequest) throws ExcecaoDeCampoObrigatorio {
		EmpresaRequest empresaRequest = avaliacaoRequest.getEmpresaRequest();
		if(empresaRequest.getId() == null){
			Empresa empresa = new Empresa(empresaRequest.getNome(), empresaRequest.getRamo(), criarEndereco(empresaRequest));
			empresa.inserirIdApiGoogle(avaliacaoRequest.getIdApiGoogle());
			empresa.inserirUrlIconeApiGoogle(avaliacaoRequest.getUrlIconeApiGoogle());
			empresaServicoAdapter.salvar(empresa);
			return empresa;
		}
		return  empresaServicoAdapter.buscarPorId(empresaRequest.getId());
	}

	private Endereco criarEndereco(EmpresaRequest empresaRequest) throws ExcecaoDeCampoObrigatorio {
		Municipio municipio = new Municipio(empresaRequest.getMunicipio().getNome());
		municipioServicoAdapter.salvar(municipio);
		return new Endereco(empresaRequest.getEnderecoCompleto(), empresaRequest.getCep(), municipio);
	}
}