package ativ;

public class Biscoito {
    private int codigo;
    private String marca;
    private String recheio;

    public Biscoito() {
        this.codigo = -1;
        this.marca = "";
        this.recheio = "";
    }

    public Biscoito(int codigo, String marca, String recheio) {
        this.codigo = codigo;
        this.marca = marca;
        this.recheio = recheio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRecheio() {
        return recheio;
    }

    public void setRecheio(String recheio) {
        this.recheio = recheio;
    }

    @Override
    public String toString() {
        return "Biscoito [codigo=" + codigo + ", marca=" + marca + ", recheio=" + recheio + "]";
    }
}
