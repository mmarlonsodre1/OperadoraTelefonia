package AT;

import java.util.Arrays;

public class Dados {
    String numero, nome, plano, credito;
    
    
    public Dados(String numero, String nome, String plano, String credito) {
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

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }
}
