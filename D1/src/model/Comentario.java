package model;

public class Comentario {
	private Integer id;
	private String nome;
	private String texto;
	private Noticia noticia;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Noticia getNoticia() {
		return noticia;
	}
	
	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}
	
}