package br.com.so.elogios.aplicacao.avaliacao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.avaliacao.Avaliacao;
import br.com.so.elogios.repositorio.avaliacao.AvaliacaoServicoAdapter;

@Service
public class ExcluiAvaliacao {

	private final AvaliacaoServicoAdapter avaliacaoServicoAdapter;

	@Autowired
	public ExcluiAvaliacao(AvaliacaoServicoAdapter avaliacaoServicoAdapter) {
		this.avaliacaoServicoAdapter = avaliacaoServicoAdapter;
	}
	
	@Transactional
	public void excluir(AvaliacaoRequest avaliacaoRequest) {
		Avaliacao avaliacao = avaliacaoServicoAdapter.buscarPorId(avaliacaoRequest.getId());
		avaliacaoServicoAdapter.excluir(avaliacao);
	}

}
