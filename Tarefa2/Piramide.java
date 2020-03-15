public class Piramide extends Poliedro {
	@Override
	public double volume() {
		return 1/3 * super.getLargura() * super.getAltura() * super.getBase();
	}
	@Override
	public double area() {
		double base = super.getBase() / 2;
		double ladoMeio = Math.sqrt(base * base + super.getAltura() * super.getAltura());
		double lado = ladoMeio * base;
		return 4 * lado + 4 * super.getBase();
	}
	@Override
	public double perimetro() {
		return 4 * super.getBase();
	}
}
