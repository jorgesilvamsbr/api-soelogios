package br.com.so.elogios.dominio.endereco;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MunicipioTest {
    @Test
    public void uma_cidade_deve_conter_o_nome_do_municipio() throws Exception {
        String municipioEsperada = "Paranhos";
        
        Municipio municipio = new Municipio(municipioEsperada);
        
        assertEquals(municipioEsperada , municipio .getNome());
    }
    
    @Test
    public void deve_conter_uma_uf_ao_criar_um_municipio() throws Exception {
        String municipioEsperada = "Paranhos";
        Municipio municipio = new Municipio(municipioEsperada);
        
        assertEquals("MS", municipio.getUf());
    }
}