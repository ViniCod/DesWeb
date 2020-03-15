public abstract class Quadrilatero extends Poligono implements Diagonal {
	public Quadrilatero(double base, double altura) {
		super(base, altura);
	}
	@Override
	public double area() {
		return super.getBase() * super.getAltura();
	}
	@Override
	public double perimetro() {
		return 2 * super.getBase() + 2 * super.getAltura();
	}
	@Override
	public double diagonal() {
		return Math.sqrt(
			super.getBase() * super.getBase() + super.getAltura() * super.getAltura());
	}
}
