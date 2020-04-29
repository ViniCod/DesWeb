package service;

import java.util.ArrayList;

import dao.DAONoticia;
import model.Noticia;

public class ServiceNoticia {
	private DAONoticia daoNoticia = new DAONoticia();
	public boolean serializeFk = true;
	
	public int criar(Noticia noticia) {
		return this.daoNoticia.criar(noticia);
	}

	public void atualizar(Noticia noticia) {
		this.daoNoticia.atualizar(noticia);
	}
	
	public void delete(int id) {
		this.daoNoticia.excluir(id);
	}

	public Noticia read(int id) {
		this.daoNoticia.serializeFk = this.serializeFk;
		Noticia n = this.daoNoticia.read(id);
		this.daoNoticia.serializeFk = true;
		return n;
	}

	public ArrayList<Noticia> list() {
		return this.daoNoticia.list();
	}

}