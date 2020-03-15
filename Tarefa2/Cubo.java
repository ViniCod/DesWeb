public class Cubo extends Poliedro {
	public Cubo(double base, double altura, double largura) {
		super(base, altura, largura);
	}
	@Override
	public double volume() {
		return super.getBase() * super.getAltura() * super.getLargura();
	}
	@Override
	public double area() {
		return 6 * (super.getBase() + super.getBase());
	}
	@Override
	public double perimetro() {
		return 12 * super.getBase();
	}
}
