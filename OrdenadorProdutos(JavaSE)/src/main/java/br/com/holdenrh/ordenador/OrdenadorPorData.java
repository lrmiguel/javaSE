package br.com.holdenrh.ordenador;

import java.util.List;

import br.com.holdenrh.ordenador.model.Produto;
import br.com.holdenrh.ordenador.model.Unidade;

public class OrdenadorPorData<E> implements Ordenador<E> {

	@SuppressWarnings("unchecked")
	@Override
	public List<E> ordenar(List<E> lista) {
		if (lista.get(0) instanceof Produto) {
			((List<Produto>)lista).sort((produto1, produto2) -> produto1.getData().compareTo(produto2.getData()));
		} else if (lista.get(0) instanceof Unidade) {
			((List<Unidade>)lista).forEach(unidade -> ((List<Produto>) unidade.getProdutos()).sort((produto1, produto2) -> produto1.getData().compareTo(produto2.getData())));
		}
		
		return lista;
	}

}
