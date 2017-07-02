package br.com.so.elogios.dominio.usuario;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class UsuarioTest {

	private static final String VAZIO = "";

	@Test
	public void deve_conter_o_nome() throws Exception {
		String nomeEsperado = "Jorge Luiz Gomes da Silva";
		
		Usuario usuario = UsuarioBuilder.novo().comNome(nomeEsperado).criar();
		
		assertEquals(nomeEsperado, usuario.getNome());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void o_nome_nao_deve_ser_vazio() throws Exception {
		UsuarioBuilder.novo().comNome(VAZIO).criar();
	}
	
	@Test
	public void deve_conter_o_email() throws Exception {
		String emailEsperado = "jorge@leafweb.com.br";
		
		Usuario usuario = UsuarioBuilder.novo().comEmail(emailEsperado).criar();
		
		assertEquals(emailEsperado, usuario.getEmail());
	}
	
	@Test(expected = EmailInvalido.class)
	public void nao_deve_permitir_cadastrar_um_email_invalido() throws Exception {
		String email = "qualquer_email.com.br";
		UsuarioBuilder.novo().comEmail(email).criar();
	}
	
	@Test
	public void deve_conter_uma_senha() throws Exception {
		String senhaEsperada = "password";
	
		Usuario usuario = UsuarioBuilder.novo().comSenha(senhaEsperada).criar();
		
		assertEquals(senhaEsperada, usuario.getSenha());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_permitir_cadastrar_senha_vazia() throws Exception {
		UsuarioBuilder.novo().comSenha(VAZIO).criar();
	}
}
