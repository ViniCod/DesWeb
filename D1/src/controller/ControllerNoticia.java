package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Noticia;
import model.Comentario;
import service.ServiceNoticia;


@WebServlet(name="Noticias", urlPatterns={"/Noticias.do/*"})
public class ControllerNoticia extends HttpServlet {
	protected static final long serialVersionUID = 1L;
	private final String url = "http://localhost:8080/D1/Noticias.do";
	private final String comentariosUrl = "http://localhost:8080/D1/Comentarios.do";
	
	private String deleteForm(Noticia noticia) {
		String form = "";
		form += "<h3>Deseja deletar a noticia \"" + noticia.getTitulo() + "\"?</h3>";
		form += "<form action=\"" + this.url + "\" method=\"POST\">";
		form += "<input class=\"input\" type=\"hidden\" name=\"deletar\" value=\"1\"><br>";
		form += "<input class=\"input\" type=\"hidden\" name=\"id\" value=\"" + noticia.getId() + "\"><br>";
		form += "<input class=\"input\" type=\"submit\" value=\"Deletar noticia\">";
		form += "</form><br>";
		return form;
	}
	
	private String editForm(Noticia noticia) {
		String form = "";
		form += "<form action=\"" + this.url + "\" method=\"POST\">";
		form += "<input class=\"input\" type=\"hidden\" name=\"id\" value=\"" + noticia.getId() + "\"><br>";
		form += "<input class=\"input\" type=\"text\" name=\"titulo\" value=\"" + noticia.getTitulo() + "\"><br>";
		form += "<input class=\"input\" type=\"text\" name=\"descricao\" value=\"" + noticia.getDescricao() + "\"><br>";
		form += "<textarea class=\"input\" name=\"texto\">" + noticia.getTexto() + "</textarea><br>";			
		form += "<input class=\"input\" type=\"submit\" value=\"Atualizar noticia\">";
		form += "</form>";
		return form;
	}
	
	private String detailForm(Noticia noticia) {
		String resposta = "";
		resposta += "<h1>" + noticia.getTitulo() + "</h1>";
		resposta += "<h2>" + noticia.getDescricao() + "</h2>";
		resposta += "<h3>" + noticia.getTexto() + "</h3>";
		
		resposta += "<br><hr><br>";
		
		if (noticia.getComentarios().size() == 0) {
			resposta += "<p>Não há comentários!</p>";
		}
		for (Comentario c : noticia.getComentarios()) {
			resposta += "<h3>" + c.getNome() + "</h3>";
			resposta += "<p>" + c.getTexto() + "</p><br>";
		}
		resposta += "<br><hr><br>";
		
		resposta += "<form action=\"" + this.comentariosUrl + "\" method=\"POST\">";
		resposta += "<input class=\"input\" type=\"hidden\" name=\"noticia\" value=\"" + noticia.getId() + "\"><br>";
		resposta += "<input class=\"input\" type\"texto\" name=\"nome\" placeholder=\"Seu nome\"><br>";
		resposta += "<textarea class=\"input\" name=\"texto\" placeholder=\"Comentario\"></textarea><br>";
		resposta += "<input class=\"input\" type=\"submit\" value=\"Postar\">";
		resposta += "</form>";
		return resposta;
	}
	
	private String currentEndpoint(String uri) {
		String[] _uri = uri.split("/ProvaD1-RealNews/Noticias.do");
		if (_uri.length == 0) return "";
		else return _uri[_uri.length - 1];
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String endpoint = this.currentEndpoint(request.getRequestURI());
		
		String id = null;
		if (!endpoint.equals("")) {
			String[] _uri = endpoint.split("/");
			id = _uri[1];
		}
		ServiceNoticia serviceNoticia = new ServiceNoticia();
		PrintWriter out = response.getWriter();
		String resposta = "<html><head><meta charset=\"UTF-8\"><link href=\"/../../../WebContent/style.css\" rel=\"stylesheet\"><title>RealNews</title></head><body><br>";
		
		if (id == null) {
			ArrayList<Noticia> noticias = serviceNoticia.list();
			
			resposta += "<ul>";
			if (noticias.size() == 0) {
				resposta += "<li>Nenhuma noticia cadastrada</li>";
			}
			for (Noticia n : noticias) {
				resposta += "<li>";
				resposta += "<a href=\"" + this.url + "/" + n.getId() + "\">" + n.getTitulo() + "</a> - ";
				resposta += "<a href=\"" + this.url + "/" + n.getId() + "/editar\">Editar</a> - ";
				resposta += "<a href=\"" + this.url + "/" + n.getId() + "/deletar\">Deletar</a>";
				resposta += "</li>";
			}
			resposta += "</ul><br><hr><br>";
			
			resposta += "<form action=\"" + this.url + "\" method=\"POST\">";
			resposta += "<input class=\"input\" type=\"text\" name=\"titulo\" placeholder=\"Titulo da noticia\"><br>";
			resposta += "<input class=\"input\" type=\"text\" name=\"descricao\" placeholder=\"Descri��o da noticia\"><br>";
			resposta += "<textarea class=\"input\" name=\"texto\" placeholder=\"Texto da noticia\"></textarea><br>";
			resposta += "<input class=\"input\" type=\"submit\" value=\"Postar\">";
			resposta += "</form>";
		}
		else {
			Noticia noticia = serviceNoticia.ler(Integer.parseInt(id));
			if (endpoint.contains("editar")) resposta += this.editForm(noticia);
			else if (endpoint.contains("deletar")) resposta += this.deleteForm(noticia);
			else resposta += this.detailForm(noticia);
		}
		
		resposta += "</body></html>";
		out.print(resposta);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceNoticia serviceNoticia = new ServiceNoticia();
		String id = request.getParameter("id");
		String deletar = request.getParameter("deletar");
		if (deletar != null) {
			serviceNoticia.excluir(Integer.parseInt(id));
			response.sendRedirect(this.url);
			return;
		}
		
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String texto = request.getParameter("texto");
		
		Noticia noticia = new Noticia();
		noticia.setDescricao(descricao);
		noticia.setTexto(texto);
		noticia.setTitulo(titulo);
		noticia.setId(id == null ? null : Integer.parseInt(id));
		
		if (noticia.getId() != null) {
			serviceNoticia.atualizar(noticia);
		}
		else {
			noticia.setId(serviceNoticia.criar(noticia));			
		}
		
		response.sendRedirect(this.url + "/" + noticia.getId());
	}
}