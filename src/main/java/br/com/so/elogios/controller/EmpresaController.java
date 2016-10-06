package br.com.so.elogios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.so.elogios.aplicacao.empresa.AdicionaEmpresa;
import br.com.so.elogios.aplicacao.empresa.AlteraEmpresa;
import br.com.so.elogios.aplicacao.empresa.ConsultaEmpresa;
import br.com.so.elogios.aplicacao.empresa.EmpresaRequest;
import br.com.so.elogios.aplicacao.empresa.EmpresaResponse;
import br.com.so.elogios.aplicacao.empresa.ExcluiEmpresa;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

@RestController
@RequestMapping("empresas")
public class EmpresaController {

	private final ConsultaEmpresa consultaEmpresa;
	private final AdicionaEmpresa adicionaEmpresa;
	private final ExcluiEmpresa excluiEmpresa;
	private final AlteraEmpresa alteraEmpresa;

	@Autowired
	public EmpresaController(ConsultaEmpresa consultaEmpresa, AdicionaEmpresa adicionaEmpresa,
			ExcluiEmpresa excluiEmpresa, AlteraEmpresa alteraEmpresa) {
		this.consultaEmpresa = consultaEmpresa;
		this.adicionaEmpresa = adicionaEmpresa;
		this.excluiEmpresa = excluiEmpresa;
		this.alteraEmpresa = alteraEmpresa;
	}	
	
	@RequestMapping(method=RequestMethod.GET)
	public List<EmpresaResponse> listar(){
		return consultaEmpresa.buscarTodas();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public EmpresaResponse buscarPorId(@PathVariable Long id){
		return this.consultaEmpresa.buscarPorId(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void adicionar(@RequestBody EmpresaRequest empresaRequest) throws ExcecaoDeCampoObrigatorio{
		this.adicionaEmpresa.adicionar(empresaRequest);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public void alterar(@RequestBody EmpresaRequest empresaRequest) throws ExcecaoDeCampoObrigatorio{
		this.alteraEmpresa.alterar(empresaRequest);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void remover(@PathVariable Long id){
		this.excluiEmpresa.excluir(id);
	}
}