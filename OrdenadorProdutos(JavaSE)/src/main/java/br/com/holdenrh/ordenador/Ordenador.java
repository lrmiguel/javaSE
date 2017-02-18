package br.com.holdenrh.ordenador;

import java.util.List;

public interface Ordenador<E> {

	public List<E> ordenar(List<E> lista);
	
	
}
