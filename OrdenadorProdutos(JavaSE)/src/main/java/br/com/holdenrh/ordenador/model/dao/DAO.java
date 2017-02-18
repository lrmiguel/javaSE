package br.com.holdenrh.ordenador.model.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.holdenrh.ordenador.model.Produto;
import br.com.holdenrh.ordenador.model.Unidade;
import br.com.holdenrh.ordenador.util.AutomatizadorDeMassa;

public class DAO {
	
	private List<Unidade> unidades = new ArrayList<>();
	private List<Produto> produtos = new ArrayList<>();

	public DAO() {
		unidades.add(AutomatizadorDeMassa.preencheUnidade("SP"));
		produtos.addAll(unidades.get(0).getProdutos());
		unidades.add(AutomatizadorDeMassa.preencheUnidade("RJ"));
		produtos.addAll(unidades.get(1).getProdutos());
	}
	
	public List<Produto> search(Collection<Produto> produtos, String nome) {
		return produtos.stream().filter(p1 -> p1.getNome().equalsIgnoreCase(nome)).collect(Collectors.toList());
	}
	
	public Collection<Produto> search(Collection<Produto> produtos, LocalDate data) {
		return produtos.stream().filter(p1 -> p1.getData().equals(data)).collect(Collectors.toList());
	}
	
	public Collection<Unidade> listUnidades() {
		return unidades;
	}

	public Iterable<Produto> search(String nome, Collection<Produto> resultPorData) {
		return resultPorData.stream().filter(p1 -> p1.getNome().equalsIgnoreCase(nome)).collect(Collectors.toList());
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
