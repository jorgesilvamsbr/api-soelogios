package br.com.so.elogios.aplicacao.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.endereco.Municipio;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;

@Service
public class AlteraEmpresa {

	private final EmpresaServicoAdapter empresaServicoAdapter;

	@Autowired
	public AlteraEmpresa(EmpresaServicoAdapter empresaServicoAdapter) {
		this.empresaServicoAdapter = empresaServicoAdapter;
	}

	@Transactional
	public void alterar(EmpresaRequest empresaRequest) throws ExcecaoDeCampoObrigatorio {
		Empresa empresa = empresaServicoAdapter.buscarPorId(empresaRequest.getId());

		atualizaInofrmacoesDaEmpresa(empresaRequest, empresa);
		
		empresaServicoAdapter.salvar(empresa);
	}

	private void atualizaInofrmacoesDaEmpresa(EmpresaRequest empresaRequest, Empresa empresa) throws ExcecaoDeCampoObrigatorio {
		empresa.alterarEndereco(retornarEnderecoAtualizado(empresaRequest, empresa));
		empresa.alterarNome(empresaRequest.getNome());
		empresa.alterarRamoDeNegocio(empresaRequest.getRamo());
	}

	private Endereco retornarEnderecoAtualizado(EmpresaRequest empresaRequest, Empresa empresa) throws ExcecaoDeCampoObrigatorio {
		Endereco endereco = empresa.getEndereco();
		endereco.alterarEnderecoCompleto(empresaRequest.getEnderecoCompleto());
		endereco.alterarCep(empresaRequest.getCep());
		endereco.alterarMunicipio(retornaMunicipioAtualizado(empresaRequest, endereco));
		return endereco;
	}

	private Municipio retornaMunicipioAtualizado(EmpresaRequest empresaRequest, Endereco endereco) throws ExcecaoDeCampoObrigatorio {
		Municipio municipio = endereco.getMunicipio();
		municipio.alterarNome(empresaRequest.getMunicipio().getNome());
		return municipio;
	}
}
