package com.alberto.pedro.william.entity;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Locacao {
	private Long id;

	private Cliente clientes;
	private Filme filmes;
	private Date data_emprestimo;
	private Date data_devolucao;
	private String obs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getClientes() {
		return clientes;
	}

	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}

	public Filme getFilmes() {
		return filmes;
	}

	public void setFilmes(Filme filmes) {
		this.filmes = filmes;
	}

	public Date getData_emprestimo() {
		return data_emprestimo;
	}

	public void setData_emprestimo(Date data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}

	public Date getData_devolucao() {
		return data_devolucao;
	}

	public void setData_devolucao(Date data_devolucao) {
		this.data_devolucao = data_devolucao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getDataEmprestimoFormatado() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.data_emprestimo.toLocalDate().format(dataFormatada).toString();
	}
	public String getDataDevolucaoFormatado() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.data_devolucao.toLocalDate().format(dataFormatada).toString();
	}


}
