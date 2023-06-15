package ed1.unicap.br;

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;
    
    public boolean isEmpty(){
        if(root == null){
            return true;
        } else {
            return false;
        }
    }
    
       public void insert(T value) { //////quase letra a
        if (this.isEmpty() == true) {
            this.root = new TreeNode<T>(value);
        } else {
            this.root.insertNode(value);
        }
    }
       
          public T find(T value) { /////quase letra c
        if (this.isEmpty() == true) {
            return null;
        } else {
            return this.root.findNode(value);
        }
    }
    public void remove(T value) {
        if (this.isEmpty() == true) {
            System.out.println("Lista vazia");
        } else {
            this.root = this.root.removeNode(this.root,value);
        }
       
    }

          
       private void percorrerEmOrdem(TreeNode<T> r) {
        if (r != null) {
            percorrerEmOrdem(r.getLeft()); //esq
            System.out.println(r.getInfo()); //raiz
            percorrerEmOrdem(r.getRight()); //dir
        } 
    }
       
       public void emOrdem() {
        if (this.isEmpty() == true) {
            System.out.println("Árvore vazia");
        } else {
            percorrerEmOrdem(this.root);
        }
    }
          
       
}


