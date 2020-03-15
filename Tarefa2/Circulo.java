public class Circulo extends Figura {
	private double raio;
	
	public Circle(double raio) {
		this.setRaio(raio);
	}
	public double getRaio() {
		return raio;
	}
	public void setRaio(double raio) {
		this.radius = radius;
	}
	@Override
	public double area() {
		return Math.PI * this.getRaio();
	}
	@Override
	public double perimetro() {
		return 2 * Math.PI * this.getRaio();
	}
}