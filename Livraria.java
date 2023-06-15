package aplic;

import cadastro.CadastroLivros;
import java.util.Scanner;

public class Livraria {

	public static void exibeOpcoes() {
		System.out.println("Bem vindo! Escolha uma opção: ");
		System.out.println("1 - Cadastrar novo livro");
		System.out.println("2 - Exibir todos os livros cadastrados em ordem");
		System.out.println("3 - Alterar preço de um livro");
		System.out.println("4 - Aumentar a quantidade em estoque de um livro");
                System.out.println("5 - Diminuir a quantidade em estoque de um livro");
		System.out.println("6 - Exibir as informações de um livro");
                System.out.println("7 - Remover um livro");
		System.out.println("8 - Fechar");
	}

	public static void main(String[] args) {
	CadastroLivros c = new CadastroLivros();
      int op, op2;
        String cod, titulo, autor;
        double preco;
        int qtd;
       Scanner in = new Scanner(System.in);
       do {
           exibeOpcoes();
           op = in.nextInt();
           switch (op) {
               case 1:
                   in.nextLine();
                   System.out.println("Insira o código ISBN do livro: ");
                   cod = in.nextLine();
                   System.out.println("Insira o título: ");
                   titulo = in.nextLine();
                   System.out.println("Insira o autor: ");
                   autor = in.nextLine();
                    System.out.println("Insira o preço: ");
                   preco = in.nextDouble();
                    System.out.println("Insira a quantidade: ");
                   qtd = in.nextInt();
                   c.cadastrar(cod, titulo, autor, preco, qtd);
                   break;
               case 2:
                  c.chamaEmOrdem();
                   break;
               case 3:
                   System.out.println("Insira o código do livro a ser alterado o preço: ");
                   in.nextLine();
                   cod = in.nextLine();
                   c.alterarPreco(cod);
                   break;
               case 4:
            	   System.out.println("Insira o código do livro a ser alterada a quantidade em estoque: ");
            	   in.nextLine();
            	   cod = in.nextLine();
                   c.alterarQtd(cod);
                   break;
               case 5:
                   System.out.println("Insira o código do livro a ser alterada a quantidade em estoque: ");
                   in.nextLine();
                   cod = in.nextLine();
                   c.alterarQtdVenda(cod);
                   break;
               case 6:
            	   System.out.println("Insira o código do livro a ser exibido: ");
            	   in.nextLine();
            	   cod = in.nextLine();
                   c.exibirUm(cod);
                   break;
               case 7:
                   System.out.println("Insira o código do livro a ser removido: ");
            	   in.nextLine();
            	   cod = in.nextLine();
                   c.removerNo(cod);
               case 8:
                   break;
               default:
                   System.out.print("Opção inválida.");
           }
          
       } while (op != 8); 
       
   }
}

