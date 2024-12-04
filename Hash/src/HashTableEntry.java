import java.io.Serializable;

class HashTableEntry implements Serializable {
    int status;   // 0 - livre, 1 - em uso
    int indiceCarro;  // índice do carro no arquivo de carros
    int proximo;  // encadeamento interno

    public HashTableEntry() {
        this.status = 0;
        this.indiceCarro = -1;
        this.proximo = -1;
    }
}
