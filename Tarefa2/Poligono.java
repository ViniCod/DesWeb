public class Poligono extends Figura {
    private double base;
	private double altura;
	
	public Poligono(double altura) {
		this.setAltura(altura);
	}
	public Poligono(double base, double altura) {
		this.setBase(base);
		this.setAltura(altura);
	}
	public double getBase() {
		return base;
	}
	public void setBase(double base) {
		this.base = base;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
}