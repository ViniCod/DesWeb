package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pais;
import service.PaisService;

/**
 * Servlet implementation class ManterClienteController
 */
@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		Long pPopulacao = Long.parseLong(request.getParameter("populacao"));
		Double pArea = Double.parseDouble(request.getParameter("area"));
		
		//instanciar o javabean
		Pais Pais = new Pais();
		Pais.setNome(pNome);
		Pais.setPopulacao(pPopulacao);
		Pais.setArea(pArea);
		
		//instanciar o service
		PaisService cs = new PaisService();
		cs.criar(Pais);
		Pais = cs.carregar(Pais.getId());
		
		request.setAttribute("Pais", Pais);
		
		RequestDispatcher view = 
		request.getRequestDispatcher("pais.jsp");
		view.foward(request, response);
		
	}

}
