package br.com.so.elogios.repositorio.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.so.elogios.dominio.usuario.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

}
