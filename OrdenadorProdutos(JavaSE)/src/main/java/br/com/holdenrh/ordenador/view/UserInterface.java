package br.com.holdenrh.ordenador.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import br.com.holdenrh.ordenador.Ordenador;
import br.com.holdenrh.ordenador.OrdenadorPorData;
import br.com.holdenrh.ordenador.OrdenadorPorNomeProduto;
import br.com.holdenrh.ordenador.model.Produto;
import br.com.holdenrh.ordenador.model.Unidade;
import br.com.holdenrh.ordenador.model.dao.DAO;

public class UserInterface {
	private static LocalDate argData = null;
	private static String argProduto = null;
	private static String argOrder = null;
	private static DAO dao;
	
	public static void main(String[] args) {
		dao = new DAO();
		
		// Recupera os argumentos passados pela linha de comando
		for (int i = 0; i < 3 && i < args.length; i++) {
			try {
				if (argData == null) {
					argData = LocalDate.parse(args[i].toString());
				}
			} catch (DateTimeParseException e) {
				recuperaArgsTipoString(args, i); 
				if (argProduto == null && argOrder == null) {
					System.err.println("A data deve estar no formato yyyy-mm-dd");
				}
			}
			recuperaArgsTipoString(args, i);
		}
		
		exibir(args.length);
	}

	private static void recuperaArgsTipoString(String[] args, int i) {
		if (!args[i].toString().equalsIgnoreCase("Data") && !args[i].toString().equalsIgnoreCase("Produto")) {
			try {
				LocalDate.parse(args[i].toString());
			} catch (DateTimeParseException e) {
				if (e.getMessage().toCharArray()[e.getMessage().length() - 1] == '0')
					argProduto = args[i].toString();
			}
		} else if (args[i].toString().equalsIgnoreCase("Data") || args[i].toString().equalsIgnoreCase("Produto")) {
			if (argOrder == null) {
				argOrder = args[i].toString();
			}
		}
	}

	private static void exibir(int argsLength) {
		if (argsLength == 0) {
			exibeCabecalhoTabela();
			exibirTudo();
		}
		
		if (argsLength == 1 && argData != null) {
			exibeCabecalhoTabela();
			filtrarPorData();
		}
		
		if (argsLength == 1 && argData == null && argProduto != null) {
			exibeCabecalhoTabela();
			dao.listUnidades().forEach(
						filtrarProdutosPorNomeProduto()
					);
		} else if (argsLength == 2 && argData != null && argProduto != null) {
			exibeCabecalhoTabela();
			dao.listUnidades().forEach(
						filtrarProdutosPorNomeProduto()
					);
		} else if (argsLength == 2 && argProduto != null && argOrder != null) {
			Ordenador<Produto> ordenador = decidirOrdenacao();
			exibeCabecalhoTabela();
			ordenador.ordenar( 
			dao.search(dao.getProdutos(), argProduto)).forEach(exibeCorpoTabela());
		}
		
		if (argsLength == 1 && argData == null && argProduto == null && argOrder != null) {
			Ordenador<Produto> ordenador = decidirOrdenacao();
			
			exibeCabecalhoTabela();
						ordenador.ordenar(dao.getProdutos()).forEach(
								exibeCorpoTabela()
					);
		} else if (argsLength == 2 && argData != null && argProduto == null && argOrder != null) {
			Ordenador<Produto> ordenador = decidirOrdenacao();
			
			exibeCabecalhoTabela();
				Collection<Produto> filtradoPorData = dao.search(dao.getProdutos(), argData);
				ordenador.ordenar((List<Produto>) filtradoPorData).forEach(
						exibeCorpoTabela()
					);
		} else if (argsLength == 3 && argData != null && argProduto != null && argOrder != null) {
			Ordenador<Produto> ordenador = decidirOrdenacao();
			
			exibeCabecalhoTabela();
				Collection<Produto> filtradoPorNome = dao.search(dao.getProdutos(), argProduto);
				Collection<Produto> filtradoPorData = dao.search(filtradoPorNome, argData);
				ordenador.ordenar((List<Produto>) filtradoPorData).forEach(
						exibeCorpoTabela()
					);
		}
	}

	private static Ordenador<Produto> decidirOrdenacao() {
		Ordenador<Produto> ordenador = null;
		
		if (argOrder.equalsIgnoreCase("Data")) {
			ordenador = new OrdenadorPorData<Produto>();
		} else if (argOrder.equalsIgnoreCase("Produto")) {
			ordenador = new OrdenadorPorNomeProduto<Produto>();
		}
		return ordenador;
	}

	private static void exibeCabecalhoTabela() {
		System.out.println("Data         Produto    Unidade      Qtd/Unid");
	}
	
	private static void filtrarPorData() {
		dao.listUnidades().forEach(
					filtrarProdutosPorData()
				);
	}

	private static void exibirTudo() {
		dao.listUnidades().forEach(unidade -> unidade.getProdutos().forEach(
				exibeCorpoTabela())
				);
	}

	private static Consumer<? super Unidade> filtrarProdutosPorData() {
		return unidade -> 
		dao.search(unidade.getProdutos(), argData).forEach(
				exibeCorpoTabela());
	}
	
	private static Consumer<? super Unidade> filtrarProdutosPorNomeProduto() {
		return unidade -> 
		dao.search(unidade.getProdutos(), argProduto).forEach(
				exibeCorpoTabela());
	}

	private static Consumer<? super Produto> exibeCorpoTabela() {
		if (argData == null) {
			return produto -> 
			System.out.println(produto.getData() + "      "
					+ produto.getNome() + "         " + produto.getUnidade().getNome() + "\t\t"
					+ produto.calculaQtdPorUnidade());
		} else {
			return produto -> 
			System.out.println(produto.getData() + "      "
					+ produto.getNome() + "         " + produto.getUnidade().getNome() + "\t\t"
					+ produto.calculaQtdPorUnidade(argData));
		}
	}
}
