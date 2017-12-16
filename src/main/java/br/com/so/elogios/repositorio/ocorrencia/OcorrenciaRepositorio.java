package br.com.so.elogios.repositorio.ocorrencia;

import br.com.so.elogios.dominio.ocorrencia.Ocorrencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcorrenciaRepositorio extends CrudRepository<Ocorrencia, Long> {

}
