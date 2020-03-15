
public class Cilindro extends Poliedro {
	private double raio;
	
	public Cilindro(double raio, double altura) {
		super(altura);
		this.setRaio(raio);
	}
	@Override
	public double volume() {
        return Math.PI * super.getAltura() * this.getRaio() * this.getRaio() * this.getRaio();
	}
	@Override
	public double area() {
		return 2 * (Math.PI * this.getRaio() * this.getRaio()) + 2 * (Math.PI * this.getRaio() * super.getAltura());
	}
	@Override
	public double perimetro() {
		return 2 * this.getRaio() * Math.PI;
	}
	public double getRaio() {
		return raio;
	}
	public void setRaio(double raio) {
		this.raio = raio;
	}
}
