public class NoAVL<T extends Comparable<T>>  {
     private T valor;
    private NoAVL<T> esquerda;
    private NoAVL<T> direita;
    private int altura;

    public NoAVL(T valor) {
        this.valor = valor;
        this.altura = 1; // Novo nó começa com altura 1
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NoAVL<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoAVL<T> esquerda) {
        this.esquerda = esquerda;
    }

    public NoAVL<T> getDireita() {
        return direita;
    }

    public void setDireita(NoAVL<T> direita) {
        this.direita = direita;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
