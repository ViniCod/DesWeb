
public class Trapezio extends Poligono {
	private double baseMenor;
	
	public Trapezio(double base, double altura, double baseMenor) {
		super(base, altura);
		this.setBaseMenor(baseMenor);
	}
	@Override
	public double area() {
		return ((super.getBase() + this.getBaseMenor()) *  super.getAltura()) / 2;
	}
	@Override
	public double perimetro() {
		double baseTriangulo = super.getBase() - this.getBaseMenor();
		double side = Math.sqrt(baseTriangulo * baseTriangulo +	super.getAltura() * super.getAltura());
		return 2 * lado + super.getBase() + this.getBaseMenor();
	}
	public double getBaseMenor() {
		return baseMenor;
	}

	public void setBaseMenor(double baseMenor) {
		this.baseMenor = baseMenor;
	}
}
