package br.com.so.elogios.controller;


import br.com.so.elogios.aplicacao.avaliacao.AvaliacaoRequest;
import br.com.so.elogios.aplicacao.avaliacao.AvaliacaoResponse;
import br.com.so.elogios.aplicacao.empresa.EmpresaRequest;
import br.com.so.elogios.aplicacao.ocorrencia.AdicionaOcorrencia;
import br.com.so.elogios.aplicacao.ocorrencia.ConsultarOcorrencia;
import br.com.so.elogios.aplicacao.ocorrencia.OcorrenciaDTO;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;
import br.com.so.elogios.dominio.ocorrencia.Ocorrencia;
import br.com.so.elogios.dominio.usuario.EmailInvalido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

    private final ConsultarOcorrencia consultarOcorrencia;
    private final AdicionaOcorrencia adicionaOcorrencia;

    @Autowired
    public OcorrenciaController(ConsultarOcorrencia consultarOcorrencia, AdicionaOcorrencia adicionaOcorrencia) {
        this.consultarOcorrencia = consultarOcorrencia;
        this.adicionaOcorrencia = adicionaOcorrencia;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<OcorrenciaDTO> listar() {
        return consultarOcorrencia.consultarTodas();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Ocorrencia> adicionar(@RequestBody AdicionarOcorrenciaHttpDTO adicionarOcorrenciaHttpDTO) throws ExcecaoDeCampoObrigatorio, EmailInvalido {
        Ocorrencia adicionar = this.adicionaOcorrencia.adicionar(adicionarOcorrenciaHttpDTO);
        return criarRespostaComAEmpresaAdicionada(adicionar);
    }

    private ResponseEntity<Ocorrencia> criarRespostaComAEmpresaAdicionada(Ocorrencia ocorrencia) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString()));
        return new ResponseEntity<Ocorrencia>(ocorrencia, httpHeaders, HttpStatus.OK);
    }
}
