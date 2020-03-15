public abstract class Poliedro extends Poligono {
	private double largura;
	
	public Poliedro() {
		super();
	}
	public Poliedro(double altura) {
		super(altura);
	}
	public Poliedro(double base, double altura, double largura) {
		super(base, altura);
		this.setLargura(largura);
	}
	public double getLargura() {
		return largura;
	}
	public void setLargura(double largura) {
		this.largura = largura;
	}
	public abstract double volume();
}
