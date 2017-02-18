package br.com.holdenrh.ordenador.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import br.com.holdenrh.ordenador.model.Produto;
import br.com.holdenrh.ordenador.model.Unidade;

public class AutomatizadorDeMassa {
	
	public static Unidade preencheUnidade(String nomeUnidade) {
		Unidade unidade = new Unidade(nomeUnidade);
		LocalDate primeiraData = LocalDate.of(1988, 3, 1);
		Produto produtoA = new Produto("A", LocalDate.now(), unidade);

		Collection<Produto> produtos = new ArrayList<>();
		produtos.add(produtoA);
		produtos.add(new Produto("C", LocalDate.of(1992, 5, 13), unidade));
		produtos.add(new Produto("B", primeiraData, unidade));
		produtos.add(new Produto("B", LocalDate.now(), unidade));
		produtos.add(new Produto("A", LocalDate.of(1992, 5, 13), unidade));
		
		unidade.adicionaProdutos(produtos);
		
		return unidade;
	}
	
}