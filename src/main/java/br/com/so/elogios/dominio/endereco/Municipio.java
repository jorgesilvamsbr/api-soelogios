package br.com.so.elogios.dominio.endereco;

import javax.persistence.Entity;

import br.com.so.elogios.dominio.Entidade.EntidadeBase;

@Entity
public class Municipio extends EntidadeBase {

    private static final String UF = "MS";
    
    private String nome;

    private Municipio() {
    }
    
    public Municipio(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getUf() {
        return UF;
    }
}