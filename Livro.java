package infos;


public class Livro implements Comparable <Livro> {
    private String cod;
    private String titulo;
   private String autor;
   private double preco;
   private int qtd;

    public Livro(String cod, String titulo, String autor, double preco, int qtd) {
        this.cod = cod;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
        this.qtd = qtd;
    }

   
    public Livro(String cod) {
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String gettitulo() {
        return titulo;
    }

    public void settitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getautor() {
        return autor;
    }

    public void setautor(String autor) {
        this.autor = autor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return "Livro {" + "codigo: " + cod + ", título:" + titulo + ", autor:" + autor + ", preço: " + preco + ", qtd: " + qtd + '}';
    }
    @Override
    public int compareTo(Livro o) {
        int retorno;
        retorno = this.cod.compareTo (o.cod);
        return retorno;
    }
}

