import java.util.ArrayList;

public class Geometria {
	public static void main(String[] args) {
		ArrayList<Quadrilateral> figuras = new ArrayList<>();
		
		figuras.add(new Retangulo(4.0, 6.0));
        figuras.add(new Quadrado(3.0));
		
		for(Quadrilatero figura : figuras) {
			String text = "";
			text += "Figura: " + figura.getClass().getName() + "\n";
			text += "Area: " + figura.area() + "\n";
			text += "Perimetro: " + figura.perimetro() + "\n";
			text += "Diagonal: " + figura.diagonal();
			System.out.println(text);
		}
	}
}
