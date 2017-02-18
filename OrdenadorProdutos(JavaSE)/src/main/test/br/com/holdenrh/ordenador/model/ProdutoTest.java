package br.com.holdenrh.ordenador.model;


import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.com.holdenrh.ordenador.util.AutomatizadorDeMassa;

public class ProdutoTest {

	@Test
	public void testaCalculoDeQuantidade() {
		Unidade unidadeSP = AutomatizadorDeMassa.preencheUnidade("São Paulo");
		Produto produtoA = new Produto("A", LocalDate.now(), unidadeSP);
		
		Assert.assertTrue(produtoA.calculaQtdPorUnidade().equals(2L));
	}

}
