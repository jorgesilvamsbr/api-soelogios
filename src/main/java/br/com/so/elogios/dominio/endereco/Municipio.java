package br.com.so.elogios.dominio.endereco;

import javax.persistence.Entity;

import br.com.so.elogios.dominio.Entidade.EntidadeBase;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Municipio extends EntidadeBase {

    private static final String UF = "MS";
    
    private String nome;

    public Municipio() {
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

	public void alterarNome(String nome) throws ExcecaoDeCampoObrigatorio {
		validarSeEstaVazio(nome, "O nome da cidade não foi informado");
		this.nome = nome;
	}
	
	
	private void validarSeEstaVazio(String campo, String mensagem) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoVazio(campo, mensagem)
		.entaoDispara();
	}
}