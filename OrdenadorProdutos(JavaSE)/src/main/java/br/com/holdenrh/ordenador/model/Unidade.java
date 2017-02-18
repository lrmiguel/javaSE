package br.com.holdenrh.ordenador.model;

import java.util.ArrayList;
import java.util.Collection;

public class Unidade {

	private Long codigo;
	
	private String nome;
	
	private Collection<Produto> produtos;

	public Unidade() {
	}
	
	public Unidade(String nomeUnidade) {
		this.nome = nomeUnidade;
		produtos = new ArrayList<>();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void adicionaProduto(Produto produto) {
		produtos.add(produto);
	}

	public void adicionaProdutos(Collection<Produto> produtos) {
		this.produtos.addAll(produtos);
	}
}
