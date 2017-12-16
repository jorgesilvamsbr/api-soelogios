package br.com.so.elogios.aplicacao.ocorrencia;

import br.com.so.elogios.dominio.ocorrencia.Ocorrencia;
import br.com.so.elogios.repositorio.ocorrencia.OcorrenciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultarOcorrencia {

    private final OcorrenciaRepositorio ocorrenciaRepositorio;

    @Autowired
    public ConsultarOcorrencia(OcorrenciaRepositorio ocorrenciaRepositorio) {
        this.ocorrenciaRepositorio = ocorrenciaRepositorio;
    }

    @Transactional
    public List<OcorrenciaDTO> consultarTodas(){

        List<Ocorrencia> ocorrencias = (List<Ocorrencia>) ocorrenciaRepositorio.findAll();

        return ocorrencias.stream().map(ocorrencia -> {
            OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO();
            ocorrenciaDTO.setDescricao(ocorrencia.getDescricao());
            ocorrenciaDTO.setHoraDoRegistro(ocorrencia.getHoraDoRegistro());
            ocorrenciaDTO.setLocalizacao(ocorrencia.getLocalizacao());
            ocorrenciaDTO.setNomeDoUsuario(ocorrencia.getNomeDoUsuario());
            ocorrenciaDTO.setSituacaoDaOcorrencia(ocorrencia.getSituacaoDaOcorrencia());
            ocorrenciaDTO.setTipoDaOcorrencia(ocorrencia.getTipoDaOcorrencia());

            if(ocorrencia.getRegistroDaOcorrencia() != null) {
                String dados = Base64.getEncoder().encodeToString(ocorrencia.getRegistroDaOcorrencia().getDados());
                ocorrenciaDTO.setImagem(dados);
            }
            return ocorrenciaDTO;
        }).collect(Collectors.toList());
    }
}
