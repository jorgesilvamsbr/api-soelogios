package br.com.so.elogios.aplicacao.ocorrencia;

import br.com.so.elogios.controller.AdicionarOcorrenciaHttpDTO;
import br.com.so.elogios.dominio.ocorrencia.Ocorrencia;
import br.com.so.elogios.dominio.ocorrencia.RegistroDaOcorrencia;
import br.com.so.elogios.dominio.ocorrencia.TipoDoRegistro;
import br.com.so.elogios.repositorio.ocorrencia.OcorrenciaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Objects;

public class AdicionaOcorrencia {

    @Autowired
    private OcorrenciaRepositorio ocorrenciaRepositorio;

    @Transactional
    public Ocorrencia adicionar(AdicionarOcorrenciaHttpDTO adicionarOcorrenciaHttpDTO ) {
        Ocorrencia ocorrencia;
        if(Objects.nonNull(adicionarOcorrenciaHttpDTO.getImagem()) && !adicionarOcorrenciaHttpDTO.getImagem().isEmpty()){
            byte[] imagemDecodificada = Base64.getDecoder().decode(adicionarOcorrenciaHttpDTO.getImagem());
            RegistroDaOcorrencia registroDaOcorrencia = new RegistroDaOcorrencia(imagemDecodificada, TipoDoRegistro.IMAGEM);
            ocorrencia = new Ocorrencia(registroDaOcorrencia, adicionarOcorrenciaHttpDTO.getTipoDaOcorrencia(), adicionarOcorrenciaHttpDTO.getHoraDoRegistro(), adicionarOcorrenciaHttpDTO.getLocalizacao(), adicionarOcorrenciaHttpDTO.getNomeDoUsuario(), adicionarOcorrenciaHttpDTO.getDescricao());
        } else {
            ocorrencia = new Ocorrencia(adicionarOcorrenciaHttpDTO.getTipoDaOcorrencia(), adicionarOcorrenciaHttpDTO.getHoraDoRegistro(), adicionarOcorrenciaHttpDTO.getLocalizacao(), adicionarOcorrenciaHttpDTO.getNomeDoUsuario(), adicionarOcorrenciaHttpDTO.getDescricao());
        }
        this.ocorrenciaRepositorio.save(ocorrencia);
        return ocorrencia;
    }

}
