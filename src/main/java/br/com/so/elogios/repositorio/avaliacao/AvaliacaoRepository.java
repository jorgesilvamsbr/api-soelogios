package br.com.so.elogios.repositorio.avaliacao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.so.elogios.dominio.avaliacao.Avaliacao;

@Repository
public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long>{

}
