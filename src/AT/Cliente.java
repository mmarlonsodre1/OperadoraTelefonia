
package AT;

public class Cliente {
    String numero, nome;
    int plano, credito;

    public Cliente() {
    }

    public Cliente(String numero, String nome, int plano, int credito) {
        this.numero = numero;
        this.nome = nome;
        this.plano = plano;
        this.credito = credito;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPlano() {
        return plano;
    }

    public void setPlano(int plano) {
        this.plano = plano;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }
}
