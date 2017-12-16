package br.com.so.elogios.dominio.ocorrencia;

public enum SituacaoDaOcorrencia {
    ABERTO("Ocorrência criada"),
    RESOLVIDO("Ocorrência confirmada"),
    NEGADO("Ocorrência negada");

    private String descricao;

    SituacaoDaOcorrencia(String descricao){
        this.descricao = descricao;
    }

    public String descricao() {
        return descricao;
    }
}
