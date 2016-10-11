package br.com.so.elogios.aplicacao.avaliacao;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.avaliacao.Comentario;
import br.com.so.elogios.repositorio.avaliacao.ComentarioServicoAdapter;

@Service
public class ConsultaComentario {

	@Autowired
	private ComentarioServicoAdapter comentarioServicoAdapter;

	@Transactional
	public List<ComentarioResponse> buscaPeloIdDaAvaliacao(Long id) {
		List<Comentario> comentarios = comentarioServicoAdapter.buscarPorIdDaAvaliacao(id);
		return comentarios.stream().map(this::criarAvaliacaoResponse).collect(Collectors.toList());
		
	}
		
	private ComentarioResponse criarAvaliacaoResponse(Comentario comentario)
	{
		ComentarioResponse comentarioResponse = new ComentarioResponse();
		comentarioResponse.setId(comentario.getId());
		comentarioResponse.setDescricao(comentario.getDescricao());
		comentarioResponse.setIdDaAvaliacao(comentario.getAvaliacao().getId());
		comentarioResponse.setIdDoUsuario(comentario.getUsuario().getId());
		
		return comentarioResponse;
	}

}
