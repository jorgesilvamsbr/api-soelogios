package br.com.so.elogios.repositorio.avaliacao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.so.elogios.dominio.avaliacao.Comentario;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long>{

}
