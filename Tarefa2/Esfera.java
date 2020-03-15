public class Esfera extends Poliedro {
	private double raio;
	
	public Esfera(double raio) {
		super();
		this.setRaio(raio);
	}
	@Override
	public double area() {
		return 4 * Math.PI * this.getRaio() * this.getRaio();
	}
	@Override
	public double perimetro() {
		return 2 * Math.PI * this.getRaio();
	}
	public double getRaio() {
		return raio;
	}
	public void setRaio(double raio) {
		this.raio = raio;
	}
	@Override
	public double volume() {
		return 4/3 * (Math.PI * this.getRaio() * this.getRaio() * this.getRaio());
	}
}
