package br.com.so.elogios.aplicacao.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.so.elogios.repositorio.empresa.EmpresaRepository;

@Service
public class ExcluiEmpresa {

	private final EmpresaRepository empresaRepository;
	
	@Autowired
	public ExcluiEmpresa(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}
	
	@Transactional
	public void excluir(Long id) {
		empresaRepository.delete(id);
	}

}
