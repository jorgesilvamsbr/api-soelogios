package br.com.so.elogios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.so.elogios.aplicacao.empresa.ConsultaEmpresa;
import br.com.so.elogios.aplicacao.empresa.EmpresaResponse;

@RestController
public class EmpresaController {

	private final ConsultaEmpresa consultaEmpresa;

	@Autowired
	public EmpresaController(ConsultaEmpresa consultaEmpresa) {
		this.consultaEmpresa = consultaEmpresa;
	}	
	
	@RequestMapping(method=RequestMethod.GET)
	public List<EmpresaResponse> listar(){
		return consultaEmpresa.buscarTodas();
	}
}