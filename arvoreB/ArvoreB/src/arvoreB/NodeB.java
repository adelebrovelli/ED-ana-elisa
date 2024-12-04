package arvoreB;

    public class NodeB<T extends Comparable<T>> {
    private int ordem;
    private int nChaves;
    private T[] chaves; 
    private NodeB<T>[] ponteiro;
    private boolean isLeaf;

    NodeB(int ordem, boolean isLeaf) {
        this.ordem = ordem;
        this.isLeaf = isLeaf;
        this.chaves = (T[]) new Comparable[ordem - 1]; // Cria um array genérico
        this.ponteiro = new NodeB[ordem];
        this.nChaves = 0;
    }

    public int getnChaves() {
        return nChaves;
    }

    public void setnChaves(int nChaves) {
        this.nChaves = nChaves;
    }

    public T[] getChaves() {
        return chaves;
    }

    public void setChaves(T k, int posicao) {
        chaves[posicao] = k;
    }

    public NodeB<T>[] getPonteiro() {
        return ponteiro;
    }

    public void setPonteiro(NodeB<T> k, int posicao) {
        ponteiro[posicao] = k;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public int compareTo(T value1, T value2) {
        return value1.compareTo(value2);
    }
}
