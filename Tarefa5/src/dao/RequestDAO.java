package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Request;

public class RequestDAO {
	public int criar(Request Request) {
		String sqlInsert = "INSERT INTO Pais(nome, populacao, area) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, Pais.getNome());
			stm.setLong(2, Pais.getPopulacao());
			stm.setDouble(3, Pais.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					Pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Pais.getId();
	}

	public void atualizar(Pais Pais) {
		String sqlUpdate = "UPDATE Pais SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, Pais.getNome());
			stm.setLong(2, Pais.getPopulacao());
			stm.setDouble(3, Pais.getArea());
			stm.setInt(4, Pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM Pais WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pais carregar(int id) {
		Pais Pais = new Pais();
		Pais.setId(id);
		String sqlSelect = "SELECT nome, populacao, area FROM Pais WHERE Pais.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, Pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					Pais.setNome(rs.getString("nome"));
					Pais.setPopulacao(rs.getLong("populacao"));
					Pais.setArea(rs.getDouble("area"));
				} else {
					Pais.setId(-1);
					Pais.setNome(null);
					Pais.setPopulacao(0);
					Pais.setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return Pais;
	}

}
