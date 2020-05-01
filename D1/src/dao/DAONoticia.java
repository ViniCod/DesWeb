package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Noticia;

public class DAONoticia {
	public boolean serializeFk = true;

	public int criar(Noticia noticia) {
		String sqlInsert = "INSERT INTO noticia(descricao, titulo, texto) VALUES (?, ?, ?)";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, noticia.getDescricao());
			stm.setString(2, noticia.getTitulo());
			stm.setString(3, noticia.getTexto());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";

			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					noticia.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticia.getId();
	}

	public void atualizar(Noticia noticia) {
		String sqlUpdate = "UPDATE noticia SET descricao=?, titulo=?, texto=? WHERE id=?";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, noticia.getDescricao());
			stm.setString(2, noticia.getTitulo());
			stm.setString(3, noticia.getTexto());
			stm.setInt(4, noticia.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(Integer id) {
		String sqlDelete = "DELETE FROM noticia WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Noticia ler(int id) {
		Noticia noticia = new Noticia();
		noticia.setId(id);
		String sqlSelect = "SELECT * FROM noticia WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, noticia.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					DAOComentario daoComentario = new DAOComentario();
					noticia.setDescricao(rs.getString("descricao"));
					noticia.setId(rs.getInt("id"));
					noticia.setTexto(rs.getString("texto"));
					noticia.setTitulo(rs.getString("titulo"));
					if (this.serializeFk) {
						noticia.setComentarios(daoComentario.list(noticia.getId()));
					}
				} else {
					noticia.setId(-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return noticia;
	}

	public ArrayList<Noticia> list() {
		String sqlSelect = "SELECT * FROM noticia";
		ArrayList<Noticia> noticias = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				DAOComentario daoComentario = new DAOComentario();
				while (rs.next()) {
					Noticia noticia = new Noticia();
					noticia.setDescricao(rs.getString("descricao"));
					noticia.setId(rs.getInt("id"));
					noticia.setTexto(rs.getString("texto"));
					noticia.setTitulo(rs.getString("titulo"));
					noticia.setComentarios(daoComentario.list(noticia.getId()));
					noticias.add(noticia);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return noticias;
	}

}