package service;

import model.Cliente;
import dao.ClienteDAO;


public class ClienteService {
	ClienteDAO dao = new ClienteDAO();
	
	public int criar(Cliente Cliente) {
		return dao.criar(Cliente);
	}
	
	public void atualizar(Cliente Cliente){
		dao.atualizar(Cliente);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Cliente carregar(int id){
		return dao.carregar(id);
	}

}
