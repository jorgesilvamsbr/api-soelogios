package br.com.so.elogios.aplicacao.empresa;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;
import br.com.so.elogios.repositorio.municipio.MunicipioServicoAdapter;

@Service
public class AdicionaEmpresa {

	private final EmpresaServicoAdapter empresaServicoAdapter;
	private final MunicipioServicoAdapter municipioServicoAdapter;
	
	@Autowired
	public AdicionaEmpresa(EmpresaServicoAdapter empresaServicoAdapter, MunicipioServicoAdapter municipioServicoAdapter) {
		this.empresaServicoAdapter = empresaServicoAdapter;
		this.municipioServicoAdapter = municipioServicoAdapter;
	}

	@Transactional
	public void adicionar(EmpresaRequest empresaRequest) throws ExcecaoDeCampoObrigatorio {
		Empresa empresa = MapearEmpresa.mapear(empresaRequest);
		
		municipioServicoAdapter.salvar(empresa.getEndereco().getMunicipio());
		empresaServicoAdapter.salvar(empresa);
		
		empresaRequest.setId(empresa.getId());
	}
}