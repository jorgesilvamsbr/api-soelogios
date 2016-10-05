package br.com.so.elogios.repositorio.avaliacao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.avaliacao.Avaliacao;
import br.com.so.elogios.repositorio.base.ServicoAdapterBase;

@Service
public class AvaliacaoServicoAdapter implements ServicoAdapterBase<Avaliacao> {

	private final AvaliacaoRepository avaliacaoRepository;

	@Autowired
	public AvaliacaoServicoAdapter(AvaliacaoRepository avaliacaoRepository) {
		this.avaliacaoRepository = avaliacaoRepository;
	}
	
	@Transactional
	@Override
	public void salvar(Avaliacao avaliacao) {
		this.avaliacaoRepository.save(avaliacao);
	}

	@Transactional
	@Override
	public int obterQuantidadeTotal() {
		return (int) this.avaliacaoRepository.count();
	}

	@Transactional
	@Override
	public void excluir(Avaliacao avaliacao) {
		this.avaliacaoRepository.delete(avaliacao);
	}

	@Transactional
	@Override
	public Avaliacao buscarPorId(Long id) {
		return this.avaliacaoRepository.findOne(id);
	}

	@Transactional
	@Override
	public List<Avaliacao> buscarTodas() {
		return (List<Avaliacao>) this.avaliacaoRepository.findAll();
	}
}