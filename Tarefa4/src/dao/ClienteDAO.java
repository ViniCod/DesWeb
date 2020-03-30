package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;

public class ClienteDAO {
	public int criar(Cliente Cliente) {
		String sqlInsert = "INSERT INTO Cliente(nome, fone, email) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, Cliente.getNome());
			stm.setString(2, Cliente.getFone());
			stm.setString(3, Cliente.getEmail());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					Cliente.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Cliente.getId();
	}

	public void atualizar(Cliente Cliente) {
		String sqlUpdate = "UPDATE Cliente SET nome=?, fone=?, email=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, Cliente.getNome());
			stm.setString(2, Cliente.getFone());
			stm.setString(3, Cliente.getEmail());
			stm.setInt(4, Cliente.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM Cliente WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cliente carregar(int id) {
		Cliente Cliente = new Cliente();
		Cliente.setId(id);
		String sqlSelect = "SELECT nome, fone, email FROM Cliente WHERE Cliente.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, Cliente.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					Cliente.setNome(rs.getString("nome"));
					Cliente.setFone(rs.getString("fone"));
					Cliente.setEmail(rs.getString("email"));
				} else {
					Cliente.setId(-1);
					Cliente.setNome(null);
					Cliente.setFone(null);
					Cliente.setEmail(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return Cliente;
	}

}
