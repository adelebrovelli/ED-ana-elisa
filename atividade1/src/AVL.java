import java.util.*;

public class AVL<T extends Comparable<T>> {
    private AVLNode<T> root;

    public boolean isEmpty(){
        if(root == null){
            return true;
        } else {
            return false;
        }
    }

    public void insertNode(T value) {
        AVLNode<T> novoNo = new AVLNode<T>(value);
        AVLNode<T> current = root;
        if (current == null){
            current = novoNo;
            current.setFatBal(0);
        } else {
            if(current.getInfo().compareTo(novoNo.getInfo())==0){
            System.out.println("Valor repetido");
            } else if (current.getInfo().compareTo(novoNo.getInfo()) > 0){
                if(current.getLeft() == null){ 

                current.setLeft(novoNo);
                root.setFatBal(root.getFatBal() - 1);
                } else {  
                    current.getLeft().insertNode(novoNo);
                }
            } else {
                if(current.getRight() == null){
                current.setRight(novoNo);
                root.setFatBal(root.getFatBal() + 1);
                } else {
                    current.getRight().insertNode(novoNo);
                }
                
            }
        }
    }
        

}
