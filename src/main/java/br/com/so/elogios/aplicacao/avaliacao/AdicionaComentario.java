package br.com.so.elogios.aplicacao.avaliacao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.avaliacao.Avaliacao;
import br.com.so.elogios.dominio.avaliacao.Comentario;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.Usuario;
import br.com.so.elogios.repositorio.avaliacao.AvaliacaoServicoAdapter;
import br.com.so.elogios.repositorio.avaliacao.ComentarioServicoAdapter;
import br.com.so.elogios.repositorio.usuario.UsuarioServicoAdapter;

@Service
public class AdicionaComentario {

	private final ComentarioServicoAdapter comentarioServicoAdapter;
	private final AvaliacaoServicoAdapter avaliacaoServicoAdapter;
	private final UsuarioServicoAdapter usuarioServicoAdapter;
	
	@Autowired
	public AdicionaComentario(ComentarioServicoAdapter comentarioServicoAdapter, AvaliacaoServicoAdapter avaliacaoServicoAdapter, UsuarioServicoAdapter usuarioServicoAdapter) {
		this.comentarioServicoAdapter = comentarioServicoAdapter;
		this.avaliacaoServicoAdapter = avaliacaoServicoAdapter;
		this.usuarioServicoAdapter = usuarioServicoAdapter;
	}

	@Transactional
	public void adicionar(ComentarioRequest comentarioRequest) throws ExcecaoDeCampoObrigatorio {
		Avaliacao avaliacao = this.avaliacaoServicoAdapter.buscarPorId(comentarioRequest.getAvaliacaoRequest().getId());
		Usuario usuario = this.usuarioServicoAdapter.buscarPorId(comentarioRequest.getUsuarioRequest().getId());
		
		Comentario comentario = new Comentario(comentarioRequest.getDescricao(), avaliacao, usuario);
		
		comentarioServicoAdapter.salvar(comentario);
		
		comentarioRequest.setId(comentario.getId());
	}

}
