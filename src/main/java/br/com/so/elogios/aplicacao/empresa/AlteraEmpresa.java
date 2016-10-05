package br.com.so.elogios.aplicacao.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.endereco.Endereco;
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
		Empresa empresaResponse = empresaServicoAdapter.buscarPorId(empresaRequest.getId());
		
		Endereco endereco = new Endereco(empresaRequest.getEnderecoCompleto(), empresaRequest.getCep(), empresaRequest.getMunicipio());
		empresaResponse.alterarEndereco(endereco);
		empresaResponse.alterarNome(empresaRequest.getNome());
		empresaResponse.alterarRamoDeNegocio(empresaRequest.getRamo());
		
		empresaServicoAdapter.salvar(empresaResponse);
	}
}
