package br.com.so.elogios.repositorio.avaliacao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.avaliacao.Comentario;
import br.com.so.elogios.repositorio.base.ServicoAdapterBase;

@Service
public class ComentarioServicoAdapter implements ServicoAdapterBase<Comentario>{

	private final ComentarioRepository comentarioRepository;

	@Autowired
	public ComentarioServicoAdapter(ComentarioRepository comentarioRepository) {
		this.comentarioRepository = comentarioRepository;
	}
	
	@Transactional
	@Override
	public void salvar(Comentario comentario) {
		this.comentarioRepository.save(comentario);
	}

	@Transactional
	@Override
	public int obterQuantidadeTotal() {
		return (int) this.comentarioRepository.count();
	}

	@Transactional
	@Override
	public void excluir(Comentario comentario) {
		this.comentarioRepository.delete(comentario);
	}

	@Transactional
	@Override
	public Comentario buscarPorId(Long id) {
		return this.comentarioRepository.findOne(id);
	}

	@Transactional
	@Override
	public List<Comentario> buscarTodas() {
		return (List<Comentario>) this.comentarioRepository.findAll();
	}
}