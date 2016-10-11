package br.com.so.elogios.aplicacao.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.avaliacao.Comentario;
import br.com.so.elogios.dominio.avaliacao.DescricaoInvalida;
import br.com.so.elogios.repositorio.avaliacao.ComentarioServicoAdapter;

@Service
public class AlteraComentario {

	@Autowired
	private ComentarioServicoAdapter comentarioServicoAdapter;

	public void alterar(ComentarioRequest comentarioRequest) throws DescricaoInvalida {
		Comentario comentario = comentarioServicoAdapter.buscarPorId(comentarioRequest.getId());
		comentario.alterarDescricao(comentarioRequest.getDescricao());
	}

}
