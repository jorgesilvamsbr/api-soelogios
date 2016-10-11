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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.so.elogios.aplicacao.avaliacao.AdicionaAvaliacao;
import br.com.so.elogios.aplicacao.avaliacao.AlteraAvaliacao;
import br.com.so.elogios.aplicacao.avaliacao.AvaliacaoRequest;
import br.com.so.elogios.aplicacao.avaliacao.AvaliacaoResponse;
import br.com.so.elogios.aplicacao.avaliacao.ConsultaAvaliacao;
import br.com.so.elogios.aplicacao.avaliacao.ExcluiAvaliacao;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.usuario.EmailInvalido;

@RestController
@CrossOrigin("*")
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
	
	private final ConsultaAvaliacao consultaAvaliacao;
	private final AdicionaAvaliacao adicionaAvaliacao;
	private final AlteraAvaliacao alteraAvaliacao;
	private final ExcluiAvaliacao excluiAvaliacao;

	@Autowired
	 public AvaliacaoController(ConsultaAvaliacao consultaAvaliacao, AdicionaAvaliacao adicionaAvaliacao,
			 AlteraAvaliacao alteraAvaliacao, ExcluiAvaliacao excluiAvaliacao)  {
		this.consultaAvaliacao = consultaAvaliacao;
		this.adicionaAvaliacao = adicionaAvaliacao;
		this.alteraAvaliacao = alteraAvaliacao;
		this.excluiAvaliacao = excluiAvaliacao;
	}
	 
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<AvaliacaoResponse> listar(){
		return consultaAvaliacao.buscarTodas();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/empresa/{id}")
	public List<AvaliacaoResponse> buscarPorIdDaEmpresa(@PathVariable Long id){
		return consultaAvaliacao.buscarPeloIdDaEmpresa(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/usuario/{id}")
	public List<AvaliacaoResponse> buscarPorIdDoUsuario(@PathVariable Long id){
		return consultaAvaliacao.buscarPeloIdDaEmpresa(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> adicionar(@RequestBody AvaliacaoRequest avaliacaoRequest) throws ExcecaoDeCampoObrigatorio, EmailInvalido{
		this.adicionaAvaliacao.adicionar(avaliacaoRequest);
		return criarRespostaComAEmpresaAdicionada(avaliacaoRequest);
	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> alterar(@RequestBody AvaliacaoRequest avaliacaoRequest) throws ExcecaoDeCampoObrigatorio{
		this.alteraAvaliacao.alterar(avaliacaoRequest);
		return criarRespostaComAEmpresaAdicionada(avaliacaoRequest);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/curtir")
	public ResponseEntity<?> curtir(@RequestBody AvaliacaoRequest avaliacaoRequest) throws ExcecaoDeCampoObrigatorio{
		this.alteraAvaliacao.curtir(avaliacaoRequest);
		return criarRespostaComAEmpresaAdicionada(avaliacaoRequest);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void remover(@RequestBody AvaliacaoRequest avaliacaoRequest){
		this.excluiAvaliacao.excluir(avaliacaoRequest);
	}
	
	private ResponseEntity<?> criarRespostaComAEmpresaAdicionada(AvaliacaoRequest avaliacaoRequest) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(avaliacaoRequest.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
}
