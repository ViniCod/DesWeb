package service;

import java.util.ArrayList;

import dao.DAOComentario;
import model.Comentario;

public class ServiceComentario {
	private DAOComentario daoComentario = new DAOComentario();

	public int criar(Comentario comentario) {
		return this.daoComentario.criar(comentario);
	}

	public ArrayList<Comentario> list(Integer noticiaId) {
		return this.daoComentario.list(noticiaId);
	}
}