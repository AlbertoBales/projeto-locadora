package com.alberto.pedro.william.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alberto.pedro.william.entity.Locacao;

public class LocacaoDAO implements DAO<Locacao> {

	private ClienteDAO clienteDAO;

	private FilmeDAO filmeDAO;

	public LocacaoDAO() {
		this.clienteDAO = new ClienteDAO();
		this.filmeDAO = new FilmeDAO();
	}

	@Override
	public Object get(Long id) {
		Locacao locacao = null;
		String sql = "select * from locacao where id = ?";

		// Recupera a conexao com o banco
		Connection conexao = null;

		// Criar uma preparacao da consulta
		PreparedStatement stm = null;

		// Criar uma classe que guarde o retorno da operacao
		ResultSet rset = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, id.intValue());
			rset = stm.executeQuery();

			while (rset.next()) {
				locacao = new Locacao();
				
				// buscando as chaves estrangeiras
				locacao.setClientes(this.clienteDAO.get(rset.getLong("cliente_id")));
				locacao.setFilmes(this.filmeDAO.get(rset.getLong("filme_id")));

				// atribui campo para atributo
				locacao.setId(rset.getLong("id"));
				locacao.setData_emprestimo(rset.getDate("data_emprestimo"));
				locacao.setData_devolucao(rset.getDate("data_devolucao"));
				locacao.setObs(rset.getString("obs"));


			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return locacao;
	}

	@Override
	public List<Locacao> getAll() {
		List<Locacao> locacaos = new ArrayList<Locacao>();

		String sql = "select * from locacao";

		// Recupera a conexao com o banco
		Connection conexao = null;

		// Criar uma preparacao da consulta
		PreparedStatement stm = null;

		// Criar uma classe que guarde o retorno da operacao
		ResultSet rset = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			rset = stm.executeQuery();

			while (rset.next()) {
				Locacao locacao = new Locacao();
				
				// buscando as chaves estrangeiras
				locacao.setClientes(this.clienteDAO.get(rset.getLong("cliente_id")));
				locacao.setFilmes(this.filmeDAO.get(rset.getLong("filme_id")));

				// atribui campo para atributo
				locacao.setId(rset.getLong("id"));
				locacao.setData_emprestimo(rset.getDate("data_emprestimo"));
				locacao.setData_devolucao(rset.getDate("data_devolucao"));
				locacao.setObs(rset.getString("obs"));



				locacaos.add(locacao);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return locacaos;
	}

	@Override
	public int save(Locacao locacao) {
		String sql = "insert into locacao ( cliente_id, filme_id, data_emprestimo, data_devolucao, obs)" + " values ( ?, ?, ?, ?, ?)";

		// Recupera a conexao com o banco
		Connection conexao = null;

		// Criar uma preparacao da consulta
		PreparedStatement stm = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setLong(1, locacao.getClientes().getId());
			stm.setLong(2, locacao.getFilmes().getId());
			stm.setDate(3, locacao.getData_emprestimo());
			stm.setDate(4, locacao.getData_devolucao());
			stm.setString(5, locacao.getObs());
	

			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public boolean update(Locacao locacao, String[] params) {
		String sql = "update locacao set  cliente_id = ?, filme_id = ?, data_emprestimo = ?, data_devolucao = ?, obs = ? where id = ?";

		// Recupera a conexao com o banco
		Connection conexao = null;

		// Criar uma preparacao da consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setLong(1, locacao.getClientes().getId());
			stm.setLong(2, locacao.getFilmes().getId());
			stm.setDate(3, locacao.getData_emprestimo());
			stm.setDate(4, locacao.getData_devolucao());
			stm.setString(5, locacao.getObs());
			stm.setLong(6, locacao.getId());
		

			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Locacao locacao) {
		String sql = "delete from locacao where id = ?";

		// Recupera a conexao com o banco
		Connection conexao = null;

		// Criar uma preparacao da consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setLong(1, locacao.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
