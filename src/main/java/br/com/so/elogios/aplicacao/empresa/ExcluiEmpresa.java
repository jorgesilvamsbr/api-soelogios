package br.com.so.elogios.aplicacao.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;

@Service
public class ExcluiEmpresa {

	private final EmpresaServicoAdapter empresaServicoAdapter;
	
	@Autowired
	public ExcluiEmpresa(EmpresaServicoAdapter empresaServicoAdapter) {
		this.empresaServicoAdapter = empresaServicoAdapter;
	}
	
	@Transactional
	public void excluir(Long id) {
		Empresa empresa = empresaServicoAdapter.buscarPorId(id);
		empresaServicoAdapter.excluir(empresa);
	}

}
