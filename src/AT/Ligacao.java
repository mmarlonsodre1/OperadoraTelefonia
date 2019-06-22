package AT;

public class Ligacao {
    String numero, nome, hInicio, hFim;
    int plano;

    public Ligacao(String numero, String nome, int plano, String hInicio, String hFim) {
        this.numero = numero;
        this.nome = nome;
        this.plano = plano;
        this.hInicio = hInicio;
        this.hFim = hFim;
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

    public String gethInicio() {
        return hInicio;
    }

    public void sethInicio(String hInicio) {
        this.hInicio = hInicio;
    }

    public String gethFim() {
        return hFim;
    }

    public void sethFim(String hFim) {
        this.hFim = hFim;
    }
}
