public class AVLTree<T extends Comparable <T>> {
    AVLNode<T> root = new AVLNode<T>(null);
    AVLTree<T> novaArvore = new AVLTree<>();
    fatBal = left.getFatBal() - right.getFatBal();
    public boolean isEmpty() {
        if(novaArvore == null){
            return true;
        } else {
            return false;
        }
    }

    public void inserirNo(T valor){
        if(this.isEmpty() == true) {
            AVLNode<T> root = new AVLNode<T>(valor);
            root.setFatBal(0);
            } else if() 
            if(root.getFatBal() > 1) {
                ro
    }
    }

    public void insert(T value) { //////quase letra a
        if (this.isEmpty() == true) {
            this.root = new TreeNode<T>(value);
        } else {
            this.root.insertNode(value);
        }
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
    
    
    
}
