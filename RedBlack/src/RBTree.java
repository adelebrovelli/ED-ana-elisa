public class RBTree<T extends Comparable<T>> {
   private TreeNode<T> root;
   
   public boolean isEmpty(){
       if(root == null){
           return true;
       } else {
           return false;
       }
   }
   
   public void recolorir(TreeNode<T> no) {
    if (no.getCor()=="black") {
    no.setCor("red");
    } else {
    no.setCor("black");
    }
   }
   
   public void rebalanceamento (TreeNode<T> no) { ///no que acaba de ser inserido
    TreeNode<T> pai = no.getPai();
    TreeNode<T> avo = no.getPai().getPai();
    TreeNode<T> tio;
    if(pai.getCor()=="black") {
    System.out.println("A árvore já está balanceada.");
    } else { 
        //while(pai.getCor()=="red")
    
    if(pai.getInfo().compareTo(avo.getInfo()) < 0) { //significa que se o pai está na esquerda e o tio na direita
    tio = avo.getRight();
    } else { //pai na direita e tio na esquerda
    tio = avo.getLeft(); 
    } if (tio.getCor()=="black" || tio == null) { //se o tio for preto ou null, rotacionar. 
    //func rotacao ira verificar qual rotacao precisa
    recolorir(pai);//recolorir os 3 envolvidos pai avo tio
    recolorir(avo);
    rebalanceamento(no); 
    }if(tio.getCor()=="red"){
        //func recolorir o pai avo tio
        recolorir(pai);//recolorir os 3 envolvidos pai avo tio
        recolorir(avo);
        recolorir(tio);
        rebalanceamento(no);
    }
    } 
   }
   
      public void insert(T value) { 
       if (this.isEmpty() == true) {
           this.root = new TreeNode<T>(value);
           this.root.setCor("black");
       } else {
           this.root.insertNode(value);
           this.root.setCor("red");
       }
   }
      
         public T find(T value) {
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