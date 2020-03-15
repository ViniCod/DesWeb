public class Circulo extends Figura {
    private double area;

    public Circulo(String nome, double area){
        super(nome);
        this.area = area;
    }
    public void setArea(double area){
        this.area = area;
    }
    @Override
    public double area(){
        return area;
    }
}