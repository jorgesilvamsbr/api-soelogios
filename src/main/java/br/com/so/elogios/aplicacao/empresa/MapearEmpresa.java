package br.com.so.elogios.aplicacao.empresa;

import br.com.so.elogios.dominio.empresa.Empresa;
import br.com.so.elogios.dominio.endereco.Endereco;
import br.com.so.elogios.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class MapearEmpresa {

	public static Empresa mapear(EmpresaRequest empresaRequest) throws ExcecaoDeCampoObrigatorio {
		Endereco endereco = new Endereco(empresaRequest.getEnderecoCompleto(), empresaRequest.getCep(), empresaRequest.getMunicipio());
		return new Empresa(empresaRequest.getNome(), empresaRequest.getRamo(), endereco);
	}
}