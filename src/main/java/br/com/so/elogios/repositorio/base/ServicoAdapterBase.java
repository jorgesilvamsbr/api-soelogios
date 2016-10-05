package br.com.so.elogios.repositorio.base;

import java.util.List;

public interface ServicoAdapterBase<T> {
	public abstract void salvar(T obj);
	public abstract int obterQuantidadeTotal();
	public abstract void excluir(T obj);
	public abstract T buscar(T obj);
	public abstract List<T> buscarTodas();
}
