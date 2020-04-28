package service;

import model.Request;
import dao.RequestDAO;


public class RequestService {
	RequestDAO dao = new RequestDAO();
	
	public int criar(Request Request) {
		return dao.criar(Request);
	}
	
	public void atualizar(Request Request){
		dao.atualizar(Request);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Request carregar(int id){
		return dao.carregar(id);
	}

}
