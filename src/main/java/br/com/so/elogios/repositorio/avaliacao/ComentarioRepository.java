package br.com.so.elogios.repositorio.avaliacao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.so.elogios.dominio.avaliacao.Comentario;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long>{

	@Query("SELECT c FROM Comentario c WHERE Avaliacao_id = :idDaAvaliacao")
	List<Comentario> buscarTodosDeUmaAvaliacao(@Param("idDaAvaliacao") Long idDaAvaliacao);
}
