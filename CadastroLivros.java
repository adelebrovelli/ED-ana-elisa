
package cadastro;

import ed1.unicap.br.BinaryTree;
import infos.Livro;
import java.util.Scanner;
public class CadastroLivros {

    private BinaryTree<Livro> dados; //possivel mudar para qualquer outra estrutura de dados
    
    public CadastroLivros(){
        dados = new BinaryTree<>();
    }
    
    public void cadastrar(String cod, String titulo, String autor, double preco, int qtd){ ///essa é a A
        Livro p = new Livro(cod, titulo, autor, preco, qtd); //cria um novo Livro para ser colocado em dados usando o insert
        dados.insert(p);
    }
    
    public void removerNo(String cod){
        Livro p = new Livro(cod);
        dados.remove(p);
    }
    
    public Livro buscar(String cod){ //LETRA C
        Livro p = new Livro(cod); //criou um Livro só com cod
        Livro result;
        result = dados.find(p); // esse só existe para guardar o resultado da busca
        return result;
    }
    
    public void alterarPreco(String cod){//LETRA D
         Scanner in = new Scanner(System.in);
        Livro p = this.buscar(cod);
        double preco;
        if(p == null){
            System.out.println("Código não cadastrado");
        } else {
            System.out.println("Informe o novo preço: ");
            preco = in.nextDouble();
            p.setPreco(preco);
        }
    }
    
    public void alterarQtd(String cod){//LETRA E
         Scanner in = new Scanner(System.in);
        Livro p = this.buscar(cod);
        int qtd;
        if(p == null){
            System.out.println("Código não cadastrado");
        } else {
            System.out.println("Informe quantos livros entraram no estoque: ");
            qtd = in.nextInt();
            p.setQtd(p.getQtd() + qtd);
            System.out.println("Estoque atual: " + p.getQtd());
        }
    }
    
    public void alterarQtdVenda(String cod) {
        Scanner in = new Scanner(System.in);
        Livro p = this.buscar(cod);
        int qtd;
        if (p == null){
            System.out.println("Código não cadastrado");
        } else {
            System.out.println("Informe a quantidade de livros do estoque a ser retirada: ");
            qtd = in.nextInt();
            if(p.getQtd() < qtd){
                System.out.println("Estoque insuficiente");
            } else {
                p.setQtd(p.getQtd() - qtd);
                System.out.println("Estoque atual: " + p.getQtd());
            }
        }
    }
    
    public void exibirUm(String cod){ //letra F
        Livro p = this.buscar(cod);
        if(p ==null){
            System.out.println("Código não encontrado");
        } else {
            System.out.println(p.toString());
        }
    }
    
    
    public void chamaEmOrdem() {
    	dados.emOrdem();
    }
  
}






