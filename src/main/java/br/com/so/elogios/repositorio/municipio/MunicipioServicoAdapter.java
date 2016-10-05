package br.com.so.elogios.repositorio.municipio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.endereco.Municipio;
import br.com.so.elogios.repositorio.base.ServicoAdapterBase;

@Service
public class MunicipioServicoAdapter implements ServicoAdapterBase<Municipio>{

	private final MunicipioRepository municipioRepository;

	@Autowired
	public MunicipioServicoAdapter(MunicipioRepository municipioRepository) {
		this.municipioRepository = municipioRepository;
	}
	
	@Override
	@Transactional
	public void salvar(Municipio municipio) {
		this.municipioRepository.save(municipio);
	}

	@Override
	@Transactional
	public int obterQuantidadeTotal() {
		return (int) this.municipioRepository.count();
	}

	@Override
	@Transactional
	public void excluir(Municipio municipio) {
		this.municipioRepository.delete(municipio);
	}

	@Override
	@Transactional
	public Municipio buscar(Municipio municipio) {
		return this.municipioRepository.findOne(municipio.getId());
	}

	@Override
	@Transactional
	public List<Municipio> buscarTodas() {
		return (List<Municipio>) this.municipioRepository.findAll();
	}
}