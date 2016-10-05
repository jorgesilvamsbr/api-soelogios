package br.com.so.elogios.aplicacao.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.repositorio.empresa.EmpresaRepository;

@Service
public class AlteraEmpresa {

	private final EmpresaRepository empresaRepository;

	@Autowired
	public AlteraEmpresa(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}

	@Transactional
	public void alterar(EmpresaRequest empresaRequest) throws ExcecaoDeCampoObrigatorio {
		Empresa empresa = empresaRepository.findOne(empresaRequest.getId());
		
		Endereco endereco = new Endereco(empresaRequest.getEnderecoCompleto(), empresaRequest.getCep(), empresaRequest.getMunicipio());
		empresa.alterarEndereco(endereco);
		empresa.alterarNome(empresaRequest.getNome());
		empresa.alterarRamoDeNegocio(empresaRequest.getRamo());
		
		empresaRepository.save(empresa);
	}
}
