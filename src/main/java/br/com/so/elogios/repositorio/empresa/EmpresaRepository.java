package br.com.so.elogios.repositorio.empresa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.so.elogios.dominio.empresa.Empresa;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long>{

}
