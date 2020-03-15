public abstract class Figura {
    private String nome;

    public Figura(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public abstract double area();
}