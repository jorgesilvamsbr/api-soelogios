package br.com.so.elogios.aplicacao.avaliacao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.so.elogios.dominio.avaliacao.Avaliacao;
import br.com.so.elogios.repositorio.avaliacao.AvaliacaoServicoAdapter;

@Service
public class ConsultaAvaliacao {

	private final AvaliacaoServicoAdapter avaliacaoServicoAdapter;

	@Autowired
	public ConsultaAvaliacao(AvaliacaoServicoAdapter avaliacaoServicoAdapter) {
		this.avaliacaoServicoAdapter = avaliacaoServicoAdapter;
	}

	@Transactional
	public List<AvaliacaoResponse> buscarTodas() {
		List<Avaliacao> avaliacoes = avaliacaoServicoAdapter.buscarTodas();
		List<AvaliacaoResponse> avaliacoesResponse = avaliacoes.stream().map(this::criarAvaliacaoResponse).collect(Collectors.toList());
		return avaliacoesResponse.stream().sorted((a1, a2) -> a2.getId().compareTo(a1.getId())).collect(Collectors.toList());
	}
	
	@Transactional
	public List<AvaliacaoResponse> buscarPeloIdDaEmpresa(Long id) {
		List<Avaliacao> avaliacoes = avaliacaoServicoAdapter.buscarPorIdDaEmresa(id);
		return avaliacoes.stream().map(this::criarAvaliacaoResponse).collect(Collectors.toList());
	}

	@Transactional
	public List<AvaliacaoResponse> buscarPeloIdDoUsuario(Long id) {
		List<Avaliacao> avaliacoes = avaliacaoServicoAdapter.buscarPorIdDoUsuario(id);
		return avaliacoes.stream().map(this::criarAvaliacaoResponse).collect(Collectors.toList());
	}
	
	@Transactional
	public AvaliacaoResponse buscarPorId(Long id) {
		Avaliacao avaliacao = avaliacaoServicoAdapter.buscarPorId(id);
		return criarAvaliacaoResponse(avaliacao);
	}
	
	private AvaliacaoResponse criarAvaliacaoResponse(Avaliacao avaliacao){
		AvaliacaoResponse avaliacaoResponse = new AvaliacaoResponse();
		avaliacaoResponse.setId(avaliacao.getId());
		avaliacaoResponse.setIdDaEmpresa(avaliacao.getEmpresa().getId());
		avaliacaoResponse.setNomeDaEmpresa(avaliacao.getEmpresa().getNome());
		avaliacaoResponse.setEndereoDaEmpresa(avaliacao.getEmpresa().getEndereco().getEnderecoCompleto());
		avaliacaoResponse.setIdDoUsuario(avaliacao.getUsuario().getId());
		avaliacaoResponse.setNomeDoUsuario(avaliacao.getUsuario().getNome());
		avaliacaoResponse.setEmailDoUsuario(avaliacao.getUsuario().getEmail());
		avaliacaoResponse.setCurtidas(avaliacao.obterCurtidas());
		avaliacaoResponse.setDescricao(avaliacao.getDescricao());
		avaliacaoResponse.setTipo(avaliacao.getTipo());
		avaliacaoResponse.setImagem(avaliacao.obterImagemDecodificada());
		return avaliacaoResponse;
	}
}
