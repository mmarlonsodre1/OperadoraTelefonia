package AT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arquivo {
    ArrayList array = new ArrayList();
    String[] verificaocao = new String[4];
    int indice = 0;
    
       
    public void ler(String arquivo) throws FileNotFoundException, IOException{
        // Ler o arquivo
        array.clear();
        FileReader ler = new FileReader(arquivo);
        BufferedReader reader = new BufferedReader(ler);  
        String linha;
        while( (linha = reader.readLine()) != null ){
            array.add(linha);
        }
    }
    
    
    public void gravar(String arquivo, String string) throws IOException{
        //Ler arquivo para não sobreescrever
        FileReader ler = new FileReader(arquivo);
        BufferedReader reader = new BufferedReader(ler);  
        String linha, enviar = "";
        
        while( (linha = reader.readLine()) != null ){
            enviar += linha + "\n";
        }
        
        // Preparar para escrever no arquivo
        FileWriter fw = new FileWriter(new File(arquivo));
        BufferedWriter bw = new BufferedWriter(fw);
        //Enviar e fechar
        bw.append(enviar + string);
        bw.close();
    }
    
    public boolean verificarNumero(String numero) throws FileNotFoundException, IOException{
        String linha ;
        boolean x = false;
        for (int i = 0; i < array.size(); i++) {
            linha = (String) array.get(i) ;
            verificaocao = linha.split(";/");
            
            //Verificar número
            if (verificaocao[0].equals(numero)){
                x = true;
                indice = i;
                break;
            }
        }
        return x;
    }
    
    public void alterar(String arquivo) throws IOException{
        String enviar = "";
        array.set(indice, String.format("%s;/%s;/%s;/%s", verificaocao[0], 
                verificaocao[1], verificaocao[2], verificaocao[3]));
        for (int i = 0; i < array.size(); i++) {
            enviar += array.get(i) + "\n";
        }
        // Preparar para escrever no arquivo
        FileWriter fw = new FileWriter(new File(arquivo));
        BufferedWriter bw = new BufferedWriter(fw);
        //Enviar e fechar
        bw.append(enviar);
        bw.close();
    }
    
    public void remover(String arquivo) throws IOException{
        String enviar = "";
        array.remove(indice);
        for (int i = 0; i < array.size(); i++) {
            enviar += array.get(i) + "\n";
        }
        // Preparar para escrever no arquivo
        FileWriter fw = new FileWriter(new File(arquivo));
        BufferedWriter bw = new BufferedWriter(fw);
        //Enviar e fechar
        bw.append(enviar);
        bw.close();
    }

    public String[] getVerificaocao() {
        return verificaocao;
    }

    public void setVerificaocao(String[] verificaocao) {
        this.verificaocao = verificaocao;
    }

    public ArrayList getArray() {
        return array;
    }

    public void setArray(ArrayList array) {
        this.array = array;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    
    
    
}
