package br.com.so.elogios.repositorio.empresa;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.repositorio.base.ServicoAdapterBase;

@Service
public class EmpresaServicoAdapter implements ServicoAdapterBase<Empresa> {

	private final EmpresaRepository empresaRepository;
	
	@Autowired
	public EmpresaServicoAdapter(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}

	@Override
	@Transactional
	public void salvar(Empresa empresa) {
		empresaRepository.save(empresa);
	}

	@Override
	@Transactional
	public int obterQuantidadeTotal() {
		return (int) empresaRepository.count();
	}

	@Override
	@Transactional
	public void excluir(Empresa empresaASerExcluida) {
		empresaRepository.delete(empresaASerExcluida);
	}

	@Override
	@Transactional
	public Empresa buscarPorId(Long id) {
		return empresaRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<Empresa> buscarTodas() {
		return (List<Empresa>) empresaRepository.findAll();
	}
}