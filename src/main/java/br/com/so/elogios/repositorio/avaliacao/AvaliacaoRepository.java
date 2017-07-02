package br.com.so.elogios.repositorio.avaliacao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.so.elogios.dominio.avaliacao.Avaliacao;

@Repository
public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long>{

	@Query("SELECT a FROM Avaliacao a WHERE empresa_id = :idDaEmpresa")
	List<Avaliacao> buscarTodosDeUmaEmpresa(@Param("idDaEmpresa") Long idDaEmpresa);

	@Query("SELECT a FROM Avaliacao a WHERE usuario_id = :idDoUsuario")
	List<Avaliacao> buscarTodosDeUmUsuario(@Param("idDoUsuario") Long idDoUsuario);

}