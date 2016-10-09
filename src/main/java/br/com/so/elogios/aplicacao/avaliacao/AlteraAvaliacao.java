package br.com.so.elogios.aplicacao.avaliacao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.avaliacao.Avaliacao;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.repositorio.avaliacao.AvaliacaoServicoAdapter;

@Service
public class AlteraAvaliacao {

	private final AvaliacaoServicoAdapter avaliacaoServicoAdapter;

	@Autowired
	public AlteraAvaliacao(AvaliacaoServicoAdapter avaliacaoServicoAdapter) {
		this.avaliacaoServicoAdapter = avaliacaoServicoAdapter;
	}

	@Transactional
	public void alterar(AvaliacaoRequest avaliacaoRequest) throws ExcecaoDeCampoObrigatorio {
		Avaliacao avaliacao = avaliacaoServicoAdapter.buscarPorId(avaliacaoRequest.getId());

		avaliacao.alterarDescricao(avaliacaoRequest.getDescricao());

		avaliacaoServicoAdapter.salvar(avaliacao);
	}

	@Transactional
	public void curtir(AvaliacaoRequest avaliacaoRequest) {
		Avaliacao avaliacao = avaliacaoServicoAdapter.buscarPorId(avaliacaoRequest.getId());

		avaliacao.curtir();

		avaliacaoServicoAdapter.salvar(avaliacao);

	}
}
