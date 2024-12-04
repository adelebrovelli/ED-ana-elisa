public class ArvoreAVL<T extends Comparable<T>> {
    private NoAVL<T> raiz;

    private int altura(NoAVL<T> no) {
        if (no == null) {
            return 0;
        }
        return no.getAltura();
    }
    private void atualizarAltura(NoAVL<T> no) {
        if (no != null) {
            no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));
        }
    }
    private NoAVL<T> rotacaoDireita(NoAVL<T> y) {
        NoAVL<T> x = y.getEsquerda();
        NoAVL<T> T2 = x.getDireita();

        // Realizar a rotação
        x.setDireita(y);
        y.setEsquerda(T2);

        // Atualizar alturas
        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    // Função auxiliar para realizar uma rotação simples à esquerda
    private NoAVL<T> rotacaoEsquerda(NoAVL<T> x) {
        NoAVL<T> y = x.getDireita();
        NoAVL<T> T2 = y.getEsquerda();

        // Realizar a rotação
        y.setEsquerda(x);
        x.setDireita(T2);

        // Atualizar alturas
        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    private int fatorBalanceamento(NoAVL<T> no) {
        if (no == null) {
            return 0;
        }
        return altura(no.getEsquerda()) - altura(no.getDireita());
    }

    public void inserir(T valor) {
        raiz = inserirRec(raiz, valor);
    }

    private NoAVL<T> inserirRec(NoAVL<T> no, T valor) {
        if (no == null) {
            return new NoAVL<>(valor);
        }

        int comparacao = valor.compareTo(no.getValor());

        if (comparacao < 0) {
            no.setEsquerda(inserirRec(no.getEsquerda(), valor));
        } else if (comparacao > 0) {
            no.setDireita(inserirRec(no.getDireita(), valor));
        } else {
            return no;
        }

        atualizarAltura(no);
        int balance = fatorBalanceamento(no);
        if (balance > 1 && valor.compareTo(no.getEsquerda().getValor()) < 0) {
            return rotacaoDireita(no);
        }
        if (balance < -1 && valor.compareTo(no.getDireita().getValor()) > 0) {
            return rotacaoEsquerda(no);
        }
        if (balance > 1 && valor.compareTo(no.getEsquerda().getValor()) > 0) {
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            return rotacaoDireita(no);
        }
        if (balance < -1 && valor.compareTo(no.getDireita().getValor()) < 0) {
            no.setDireita(rotacaoDireita(no.getDireita()));
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void emOrdem() {
        emOrdemRec(raiz);
        System.out.println(" ");
    }

    private void emOrdemRec(NoAVL<T> no) {
        if (no != null) {
            emOrdemRec(no.getEsquerda());
            System.out.print(no.getValor() + " ");
            emOrdemRec(no.getDireita());
        }
    }

    public void preOrdem() {
        preOrdemRec(raiz);
        System.out.println(" ");
    }

    private void preOrdemRec(NoAVL<T> no) {
        if (no != null) {
            System.out.print(no.getValor() + " ");
            preOrdemRec(no.getEsquerda());
            preOrdemRec(no.getDireita());
        }
    }


    public void remover(T valor) {
        raiz = removerRec(raiz, valor);
    }

    private NoAVL<T> removerRec(NoAVL<T> no, T valor) {
        if (no == null) {
            return no; 
        }

        int comparacao = valor.compareTo(no.getValor());

        if (comparacao < 0) {
            no.setEsquerda(removerRec(no.getEsquerda(), valor));
        } else if (comparacao > 0) {
            no.setDireita(removerRec(no.getDireita(), valor));
        } else {
           
            if (no.getEsquerda() == null || no.getDireita() == null) {
                NoAVL<T> temp = (no.getEsquerda() != null) ? no.getEsquerda() : no.getDireita();

                if (temp == null) {
                    temp = no;
                    no = null;
                } else { 
                    no = temp;
                }
            } else {
                NoAVL<T> temp = encontrarMinimo(no.getDireita());
                no.setValor(temp.getValor());
                no.setDireita(removerRec(no.getDireita(), temp.getValor()));
            }
        }

        if (no == null) {
            return no;
        }

        atualizarAltura(no);

        int balance = fatorBalanceamento(no);

        if (balance > 1) {
            if (fatorBalanceamento(no.getEsquerda()) >= 0) {
                return rotacaoDireita(no);
            } else {
                no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
                return rotacaoDireita(no);
            }
        }

        if (balance < -1) {
            if (fatorBalanceamento(no.getDireita()) <= 0) {
                return rotacaoEsquerda(no);
            } else {
                no.setDireita(rotacaoDireita(no.getDireita()));
                return rotacaoEsquerda(no);
            }
        }

        return no;
    }

    private NoAVL<T> encontrarMinimo(NoAVL<T> no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }
    
    public void posOrdem() {
        posOrdemRec(raiz);
        System.out.println();
    }

    private void posOrdemRec(NoAVL<T> no) {
        if (no != null) {
            posOrdemRec(no.getEsquerda());
            posOrdemRec(no.getDireita());
            System.out.print(no.getValor() + " ");
        }
    }
}