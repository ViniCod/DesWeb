package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pais;
import service.PaisService;

@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		long pPopulacao = Long.parseLong(request.getParameter("populacao"));
		double pArea = Double.parseDouble(request.getParameter("area"));
		
		Pais Pais = new Pais();
		Pais.setNome(pNome);
		Pais.setPopulacao(pPopulacao);
		Pais.setArea(pArea);
		
		PaisService cs = new PaisService();
		cs.criar(Pais);
		Pais = cs.carregar(Pais.getId());
		
		request.setAttribute("pais", Pais);
		
		RequestDispatcher view = 
		request.getRequestDispatcher("Pais.jsp");
		view.forward(request, response);
		
	}

}
