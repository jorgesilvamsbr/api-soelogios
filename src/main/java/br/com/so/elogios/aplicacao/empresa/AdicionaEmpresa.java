package br.com.so.elogios.aplicacao.empresa;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.repositorio.empresa.EmpresaRepository;
import br.com.so.elogios.repositorio.municipio.MunicipioRepository;

@Service
public class AdicionaEmpresa {

	private final EmpresaRepository empresaRepository;
	private final MunicipioRepository municipioRepository;
	
	@Autowired
	public AdicionaEmpresa(EmpresaRepository empresaRepository, MunicipioRepository municipioRepository) {
		this.empresaRepository = empresaRepository;
		this.municipioRepository = municipioRepository;
	}

	@Transactional
	public void adicionar(EmpresaRequest empresaRequest) throws ExcecaoDeCampoObrigatorio {
		Empresa empresa = MapearEmpresa.mapear(empresaRequest);
		municipioRepository.save(empresa.getEndereco().getMunicipio());
		empresaRepository.save(empresa);
		
		empresaRequest.setId(empresa.getId());
	}
}