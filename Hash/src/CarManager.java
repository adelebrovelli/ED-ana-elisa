import java.io.*;
import java.util.Scanner;

public class CarManager {
    static final int TABLE_SIZE = 101;
    static final String HASH_TABLE_FILE = "hash_table.dat";
    static final String CAR_FILE = "carros.dat";

    public static int hashing(String placa) {
        int total = 0;
        for (char c : placa.toCharArray()) {
            total += (int) c;
        }
        return total % TABLE_SIZE;
    }

    public static int busca(String placa, HashTableEntry[] tabelaHash, Car[] arquivoCarros) {
        int posicaoHash = hashing(placa);
        HashTableEntry entradaHash = tabelaHash[posicaoHash];

        while (entradaHash != null && entradaHash.proximo != -1) {
            if (entradaHash.status == 1 && arquivoCarros[entradaHash.indiceCarro].placa.equals(placa)) {
                return entradaHash.indiceCarro;
            }
            entradaHash = tabelaHash[entradaHash.proximo];
        }

        if (entradaHash != null && entradaHash.status == 1 && arquivoCarros[entradaHash.indiceCarro].placa.equals(placa)) {
            return entradaHash.indiceCarro;
        }

        return -1;
    }

    public static void cadastrarCarro(HashTableEntry[] tabelaHash, Car[] arquivoCarros) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a placa do carro: ");
        String placa = scanner.nextLine();

        int posicaoExistente = busca(placa, tabelaHash, arquivoCarros);

        if (posicaoExistente == -1) {
            System.out.print("Digite a marca do carro: ");
            String marca = scanner.nextLine();
            System.out.print("Digite o modelo do carro: ");
            String modelo = scanner.nextLine();
            System.out.print("Digite a cor do carro: ");
            String cor = scanner.nextLine();
            System.out.print("Digite a matrícula do funcionário dono do carro: ");
            String matriculaFuncionario = scanner.nextLine();

            Car novoCarro = new Car(placa, marca, modelo, cor, matriculaFuncionario);

            int posicaoHash = hashing(placa);
            HashTableEntry entradaHash = tabelaHash[posicaoHash];

            while (entradaHash != null && entradaHash.proximo != -1) {
                entradaHash = tabelaHash[entradaHash.proximo];
            }

            if (entradaHash == null) {
                tabelaHash[posicaoHash] = new HashTableEntry();
                entradaHash = tabelaHash[posicaoHash];
            }

            for (int i = 0; i < arquivoCarros.length; i++) {
                if (arquivoCarros[i] == null) {
                    arquivoCarros[i] = novoCarro;
                    entradaHash.indiceCarro = i;
                    entradaHash.status = 1;
                    System.out.println("Carro cadastrado com sucesso!");
                    break;
                }
            }
        } else {
            System.out.println("A placa já existe. Cadastro não pode ser efetuado.");
        }
    }

    public static void exibirDadosCarro(HashTableEntry[] tabelaHash, Car[] arquivoCarros) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a placa do carro: ");
        String placa = scanner.nextLine();

        int posicaoCarro = busca(placa, tabelaHash, arquivoCarros);

        if (posicaoCarro != -1) {
            Car carro = arquivoCarros[posicaoCarro];
            System.out.println("Placa: " + carro.placa);
            System.out.println("Modelo: " + carro.modelo);
            System.out.println("Marca: " + carro.marca);
            System.out.println("Cor: " + carro.cor);
            System.out.println("Matrícula do Funcionário: " + carro.matriculaFuncionario);
        } else {
            System.out.println("Carro não encontrado.");
        }
    }

    public static void listarArquivo(Car[] arquivoCarros) {
        for (Car carro : arquivoCarros) {
            if (carro != null) {
                System.out.println("Placa: " + carro.placa);
                System.out.println("Modelo: " + carro.modelo);
                System.out.println("Marca: " + carro.marca);
                System.out.println("Cor: " + carro.cor);
                System.out.println("Matrícula do Funcionário: " + carro.matriculaFuncionario);
                System.out.println();
            }
        }
    }

   public static HashTableEntry[] lerTabelaHash() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HASH_TABLE_FILE))) {
        return (HashTableEntry[]) ois.readObject();
    } catch (FileNotFoundException e) {
        System.out.println("Arquivo não encontrado: " + HASH_TABLE_FILE);
        // Cria o arquivo se não existir
        escreverTabelaHash(new HashTableEntry[TABLE_SIZE]);
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return new HashTableEntry[TABLE_SIZE];
}

    public static void escreverTabelaHash(HashTableEntry[] tabelaHash) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HASH_TABLE_FILE))) {
            oos.writeObject(tabelaHash);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File arquivoCarros = new File(CAR_FILE);

        try {
            if (!arquivoCarros.exists()) {
                arquivoCarros.createNewFile();
                System.out.println("Arquivo " + CAR_FILE + " criado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashTableEntry[] tabelaHash = lerTabelaHash();
        if (tabelaHash == null) {
            tabelaHash = new HashTableEntry[TABLE_SIZE];
        }

        Car[] arquivoCarrosArray = new Car[TABLE_SIZE];

        while (true) {
            System.out.println("\n1. Cadastrar Carro");
            System.out.println("2. Exibir Dados do Carro");
            System.out.println("3. Listar Arquivo de Carros");
            System.out.println("4. Sair");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Escolha a opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    cadastrarCarro(tabelaHash, arquivoCarrosArray);
                    escreverTabelaHash(tabelaHash);
                    break;
                case 2:
                    exibirDadosCarro(tabelaHash, arquivoCarrosArray);
                    break;
                case 3:
                    listarArquivo(arquivoCarrosArray);
                    break;
                case 4:
                    escreverTabelaHash(tabelaHash);
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
