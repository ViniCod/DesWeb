package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pais;

public class PaisDAO {
	public int criar(Pais Pais) {
		String sqlInsert = "INSERT INTO Pais(nome, fone, email) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, Pais.getNome());
			stm.setString(2, Pais.getFone());
			stm.setString(3, Pais.getEmail());
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
		String sqlUpdate = "UPDATE Pais SET nome=?, fone=?, email=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, Pais.getNome());
			stm.setString(2, Pais.getFone());
			stm.setString(3, Pais.getEmail());
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
		String sqlSelect = "SELECT nome, fone, email FROM Pais WHERE Cliente.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, Pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					Pais.setNome(rs.getString("nome"));
					Pais.setFone(rs.getString("fone"));
					Pais.setEmail(rs.getString("email"));
				} else {
					Pais.setId(-1);
					Pais.setNome(null);
					Pais.setFone(null);
					Pais.setEmail(null);
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
