public class Main {
    public static void main (String args[]){
        ArvoreAVL t = new ArvoreAVL<>();
        t.inserir(1);
        t.inserir(2);
        t.inserir(3);
        t.inserir(4);
        t.inserir(8);
        t.inserir(5);
        t.inserir(7);
        t.inserir(6);
        t.posOrdem();
        t.preOrdem();
        t.emOrdem();
        t.remover(6);
        t.remover(8);
        t.posOrdem();
        t.preOrdem();
        t.emOrdem();
    }
}
