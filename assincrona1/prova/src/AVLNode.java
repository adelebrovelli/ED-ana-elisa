public class AVLNode<T> {
    private T info;
    private int fatBal; 
    private AVLNode<T> left; 
    private AVLNode<T> right;
    public AVLNode(T info, int fatBal, AVLNode<T> left, AVLNode<T> right) {
        this.info = info;
        this.fatBal = fatBal;
        this.left = left;
        this.right = right;
    }
    public AVLNode(T info) {
        this.info = info;
    }
    public T getInfo() {
        return info;
    }
    public void setInfo(T info) {
        this.info = info;
    }
    public int getFatBal() {
        return fatBal;
    }
    public void setFatBal(int fatBal) {
        this.fatBal = fatBal;
    }
    public AVLNode<T> getLeft() {
        return left;
    }
    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }
    public AVLNode<T> getRight() {
        return right;
    }
    public void setRight(AVLNode<T> right) {
        this.right = right;
    }


}
