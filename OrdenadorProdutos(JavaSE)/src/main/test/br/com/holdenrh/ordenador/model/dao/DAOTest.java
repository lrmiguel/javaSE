package br.com.holdenrh.ordenador.model.dao;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.com.holdenrh.ordenador.model.Unidade;
import br.com.holdenrh.ordenador.util.AutomatizadorDeMassa;

public class DAOTest {

	@Test
	public void testaProcuraPorProduto() {
		Unidade unidadeSP = AutomatizadorDeMassa.preencheUnidade("São Paulo");
		
		Assert.assertEquals(new DAO().search(unidadeSP.getProdutos(), "A").size(), 2);
	}
	
	@Test
	public void testaProcuraPorData() {
		Unidade unidadeSP = AutomatizadorDeMassa.preencheUnidade("São Paulo");
		
		Assert.assertNotNull(new DAO().search(unidadeSP.getProdutos(), LocalDate.of(1992, 5, 13)));
	}

}
