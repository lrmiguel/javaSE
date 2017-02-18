package br.com.holdenrh.ordenador.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.holdenrh.ordenador.OrdenadorPorData;
import br.com.holdenrh.ordenador.OrdenadorPorNomeProduto;
import br.com.holdenrh.ordenador.model.Produto;
import br.com.holdenrh.ordenador.model.Unidade;

public class OrdenadorTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testaOrdenacaoPorNomeProduto() {
		Unidade unidadeSP = AutomatizadorDeMassa.preencheUnidade("São Paulo");
		
		new OrdenadorPorNomeProduto().ordenar((List<Produto>) unidadeSP.getProdutos());
		
		Assert.assertFalse(((List<Produto>)unidadeSP.getProdutos()).get(1).getNome().equals("C"));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testaOrdenacaoPorDataProduto() {
		Unidade unidadeSP = AutomatizadorDeMassa.preencheUnidade("São Paulo");
		
		new OrdenadorPorData().ordenar((List<Produto>) unidadeSP.getProdutos());
		
		Assert.assertTrue(((List<Produto>)unidadeSP.getProdutos()).get(0).getNome().equals("B"));
	}

}
