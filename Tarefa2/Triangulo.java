public class Triangulo extends Poligono {
	public Triangulo(double base, double altura) {
		super(base, altura);
	}
	@Override
	public double area() {
		return (super.getBase() * super.getAltura()) / 2;
	}
	@Override
	public double perimetro() {
		double lado = Math.sqrt(
			super.getBase() * super.getBase() + super.getAltura() * super.getAltura());
		return 2 * lado + super.getBase();
	}
}