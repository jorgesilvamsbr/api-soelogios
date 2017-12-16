package br.com.so.elogios.dominio.ocorrencia;

public enum TipoDaOcorrencia {

    ESTACIONAMENTO_INAPROPRIADO("Estacionado em local não permitido"),
    ULTRAPASSAGEM_INDEVIDA("Ultrapassagem indevida"),
    USO_DE_CELULAR_DIRIGINDO("Uso do celular ao volante"),
    OCORRENCIA_DE_ACIDENTE("Ocorrência de acidente"),
    BURACO_NA_VIA("Buraco na via"),
    OUTRO("Outros");

    private String descricao;

    TipoDaOcorrencia(String descricao){
        this.descricao = descricao;
    }

    public String descricao() {
        return descricao;
    }
}
