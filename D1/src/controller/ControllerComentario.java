package controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comentario;
import service.ServiceNoticia;
import service.ServiceComentario;


@WebServlet("/Comentarios.do")
public class ControllerComentario extends HttpServlet {
	protected static final long serialVersionUID = 1L;
	private final String noticiasUrl = "http://localhost:8080/D1/Noticias.do";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noticia = request.getParameter("noticia");
		String nome = request.getParameter("nome");
		String texto = request.getParameter("texto");
		
		ServiceNoticia serviceNoticia = new ServiceNoticia();
		serviceNoticia.serializeFk = false;
		Comentario comentario = new Comentario();
		comentario.setNome(nome);
		comentario.setNoticia(serviceNoticia.ler(Integer.parseInt(noticia)));
		comentario.setTexto(texto);
		
		ServiceComentario serviceComentario= new ServiceComentario();
		serviceComentario.criar(comentario);
		
		response.sendRedirect(this.noticiasUrl + "/" + noticia);
	}
}