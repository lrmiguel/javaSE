package br.com.holdenrh.ordenador.model;

import java.time.LocalDate;

public class Produto {

	private Long id;
	
	private String nome;
	
	private LocalDate data;
	
	private Unidade unidade;

	public Produto() {
	}
	
	public Produto(String nomeProduto, LocalDate data, Unidade unidade) {
		this.nome = nomeProduto;
		this.data = data;
		this.unidade = unidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Unidade getUnidade() {
		return unidade;
	}
	
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	public Long calculaQtdPorUnidade() {
		return this.unidade.getProdutos().stream().filter(produto -> this.equals(produto)).count();
	}
	
	public Long calculaQtdPorUnidade(LocalDate data) {
		return this.unidade.getProdutos().stream().filter(produto -> produto.getData().equals(data)).filter(produto -> this.equals(produto)).count();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getNome().equals(((Produto) obj).getNome());
	}
	
}
