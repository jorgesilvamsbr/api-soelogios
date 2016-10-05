package br.com.so.elogios.repositorio.usuario;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.usuario.Usuario;
import br.com.so.elogios.repositorio.base.ServicoAdapterBase;

@Service
public class UsuarioServicoAdapter implements ServicoAdapterBase<Usuario>{

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServicoAdapter(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Transactional
	@Override
	public void salvar(Usuario usuario) {
		this.usuarioRepository.save(usuario);
	}

	@Transactional
	@Override
	public int obterQuantidadeTotal() {
		return (int) this.usuarioRepository.count();
	}

	@Transactional
	@Override
	public void excluir(Usuario usuario) {
		this.usuarioRepository.delete(usuario);
	}

	@Transactional
	@Override
	public Usuario buscarPorId(Long id) {
		return this.usuarioRepository.findOne(id);
	}

	@Transactional
	@Override
	public List<Usuario> buscarTodas() {
		return (List<Usuario>) this.usuarioRepository.findAll();
	}
}