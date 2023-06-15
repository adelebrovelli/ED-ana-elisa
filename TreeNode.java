package ed1.unicap.br;

class TreeNode<T extends Comparable <T>>{
    private T info;
    private TreeNode<T> left;
    private TreeNode<T> right;
    
    TreeNode (T info){
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
    
      void insertNode(T value) {
        if (value.compareTo(this.getInfo()) == 0) {
            System.out.println("Valor repetido.");
        } else if (value.compareTo(this.getInfo()) < 0) {
            if (this.left == null) {
                this.left = new TreeNode<T>(value);
            } else {
                this.left.insertNode(value);
            }
        } else {
            if (this.right == null) {
                this.right = new TreeNode<T>(value);
            } else {
                this.right.insertNode(value);
            }
        }
    }
      
       T findNode (T value) {
        if (this.getInfo().compareTo(value) == 0) {
            return this.getInfo();
        }
        else if (value.compareTo(this.getInfo()) < 0) {
            if (this.getLeft() == null){
                return null;
            }
            else {
                return this.getLeft().findNode (value);
            }
        }
        else {
            if (this.getRight() == null){
                return null;
            }
            else {
                return this.getRight().findNode (value);
            }
        }
    }

     TreeNode<T> removeNode(TreeNode<T> r, T value) {
        if (r != null) {
            if (value.compareTo(r.getInfo()) == 0) {
                TreeNode<T> pai, filho;
                if (r.getLeft() == null && r.getRight() == null) { // Não tem filhos
                    r = null;
                } else if (r.getLeft() == null) { // Não tem filho a esquerda
                    r = r.getRight();
                } else if (r.getRight() == null) { // Não tem filho a direita
                    r = r.getLeft();
                } else { // Tem ambos os filhos
                    pai = r;
                    filho = pai.getLeft();
                    while (filho.getRight() != null) {
                        pai = filho;
                        filho = filho.getRight();
                    }
                    pai.setRight(filho.getLeft());
                    r.setInfo(filho.getInfo());
                }

            } else if (value.compareTo(r.getInfo()) < 0) {
                r.setLeft(removeNode(r.getLeft(), value));
            } else {
                r.setRight(removeNode(r.getRight(), value));
            }
        }
        return r;
    }

       
       
}

