package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Comentario;

public class DAOComentario {

	public int criar(Comentario comentario) {
		String sqlInsert = "INSERT INTO comentario(nome, texto, fk_noticia_id) VALUES (?, ?, ?)";
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatment stm = conn.prepareStatment(sqlInsert);) {
			stm.setString(1, comentario.getNome());
			stm.setString(2, comentario.getTexto());
			stm.setString(3, comentario.getNoticia().getId());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					comentario.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comentario.getId();
	}

	public ArrayList<Comentario> list(int noticiaId) {
		String sqlSelect = "SELECT * FROM comentario WHERE fk_noticia_id=?";
		ArrayList<Comentario> comentarios = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, noticiaId);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Comentario comentario = new Comentario();
					comentario.setNome(rs.getString("nome"));
					comentario.setTexto(rs.getString("texto"));
					comentario.add(comentario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return comentarios;
	}
}