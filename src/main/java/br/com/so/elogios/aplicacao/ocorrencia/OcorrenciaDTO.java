package br.com.so.elogios.aplicacao.ocorrencia;

import br.com.so.elogios.dominio.localizacao.Localizacao;
import br.com.so.elogios.dominio.ocorrencia.SituacaoDaOcorrencia;
import br.com.so.elogios.dominio.ocorrencia.TipoDaOcorrencia;

import java.time.LocalDateTime;

public class OcorrenciaDTO {
    private String imagem;

    private TipoDaOcorrencia tipoDaOcorrencia;

    private LocalDateTime horaDoRegistro;

    private Localizacao localizacao;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public TipoDaOcorrencia getTipoDaOcorrencia() {
        return tipoDaOcorrencia;
    }

    public void setTipoDaOcorrencia(TipoDaOcorrencia tipoDaOcorrencia) {
        this.tipoDaOcorrencia = tipoDaOcorrencia;
    }

    public LocalDateTime getHoraDoRegistro() {
        return horaDoRegistro;
    }

    public void setHoraDoRegistro(LocalDateTime horaDoRegistro) {
        this.horaDoRegistro = horaDoRegistro;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public String getNomeDoUsuario() {
        return nomeDoUsuario;
    }

    public void setNomeDoUsuario(String nomeDoUsuario) {
        this.nomeDoUsuario = nomeDoUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SituacaoDaOcorrencia getSituacaoDaOcorrencia() {
        return situacaoDaOcorrencia;
    }

    public void setSituacaoDaOcorrencia(SituacaoDaOcorrencia situacaoDaOcorrencia) {
        this.situacaoDaOcorrencia = situacaoDaOcorrencia;
    }

    private String nomeDoUsuario;

    private String descricao;

    private SituacaoDaOcorrencia situacaoDaOcorrencia;
}
