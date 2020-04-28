package service;

import model.Pais;
import dao.PaisDAO;


public class PaisService {
	PaisDAO dao = new PaisDAO();
	
	public int criar(Pais Pais) {
		return dao.criar(Pais);
	}
	
	public void atualizar(Pais Pais){
		dao.atualizar(Pais);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Pais carregar(int id){
		return dao.carregar(id);
	}

}
