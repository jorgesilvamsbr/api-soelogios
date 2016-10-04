package br.com.so.elogios.repositorio.municipio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.so.elogios.dominio.endereco.Municipio;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Long>{

}
