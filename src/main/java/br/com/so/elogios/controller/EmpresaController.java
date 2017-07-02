package br.com.so.elogios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.so.elogios.aplicacao.empresa.AdicionaEmpresa;
import br.com.so.elogios.aplicacao.empresa.AlteraEmpresa;
import br.com.so.elogios.aplicacao.empresa.ConsultaEmpresa;
import br.com.so.elogios.aplicacao.empresa.EmpresaRequest;
import br.com.so.elogios.aplicacao.empresa.EmpresaResponse;
import br.com.so.elogios.aplicacao.empresa.ExcluiEmpresa;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("empresas")
public class EmpresaController {

	private final ConsultaEmpresa consultaEmpresa;
	private final AdicionaEmpresa adicionaEmpresa;
	private final ExcluiEmpresa excluiEmpresa;
	private final AlteraEmpresa alteraEmpresa;

	@Autowired
	public EmpresaController(ConsultaEmpresa consultaEmpresa, AdicionaEmpresa adicionaEmpresa, ExcluiEmpresa excluiEmpresa, AlteraEmpresa alteraEmpresa) {
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
	public ResponseEntity<?> adicionar(@RequestBody EmpresaRequest empresaRequest) throws ExcecaoDeCampoObrigatorio{
		this.adicionaEmpresa.adicionar(empresaRequest);
		return criarRespostaComAEmpresaAdicionada(empresaRequest);
	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> alterar(@RequestBody EmpresaRequest empresaRequest) throws ExcecaoDeCampoObrigatorio{
		this.alteraEmpresa.alterar(empresaRequest);
		return criarRespostaComAEmpresaAdicionada(empresaRequest);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void remover(@PathVariable Long id){
		this.excluiEmpresa.excluir(id);
	}
	
	private ResponseEntity<?> criarRespostaComAEmpresaAdicionada(EmpresaRequest empresaRequest) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empresaRequest.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
}