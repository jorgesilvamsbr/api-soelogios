package br.com.so.elogios.aplicacao.avaliacao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.avaliacao.Comentario;
import br.com.so.elogios.repositorio.avaliacao.ComentarioServicoAdapter;

@Service
public class ExcluiComentario {

	@Autowired private ComentarioServicoAdapter comentarioServicoAdapter;

	@Transactional
	public void excluir(ComentarioRequest comentarioRequest) {
		Comentario comentario = comentarioServicoAdapter.buscarPorId(comentarioRequest.getId());
		comentarioServicoAdapter.excluir(comentario);
	}

}
