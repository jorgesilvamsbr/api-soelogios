package br.com.so.elogios.aplicacao.empresa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.repositorio.empresa.EmpresaServicoAdapter;

@Service
public class ConsultaEmpresa {

	private final EmpresaServicoAdapter empresaServicoAdapter;
	
	@Autowired
	public ConsultaEmpresa(EmpresaServicoAdapter empresaServicoAdapter) {
		this.empresaServicoAdapter = empresaServicoAdapter;
	}
	
	@Transactional
	public List<EmpresaResponse> buscarTodas() {
		List<Empresa> empresas = (List<Empresa>) empresaServicoAdapter.buscarTodas();
		return empresas.stream().map(this::criarEmpresaResponse).collect(Collectors.toList());
	}

	@Transactional
	public EmpresaResponse buscarPorId(Long id) {
		Empresa empresa = empresaServicoAdapter.buscarPorId(id);
		return criarEmpresaResponse(empresa);
	}

	private EmpresaResponse criarEmpresaResponse(Empresa empresa){
		return MapearEmpresa.mapearParaResponse(empresa);
	}

}
