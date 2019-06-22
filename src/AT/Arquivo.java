package AT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {
    String file;

    public Arquivo(String file) {
        this.file = file;
    }
    
    public void ler(ArrayList array) throws FileNotFoundException, IOException{
        String[] campos;
        // Ler o arquivo
        array.clear();
        FileReader ler = new FileReader(file);
        BufferedReader reader = new BufferedReader(ler);  
        String linha;
        
        while( (linha = reader.readLine()) != null ){
            campos = linha.split(";/");
            if(campos.length == 4){
                Cliente cliente = new Cliente(campos[0], campos[1], 
                        Integer.parseInt(campos[2]), Integer.parseInt(campos[3]));
                array.add(cliente);
            } else {
                Ligacao ligacao = new Ligacao(campos[0], campos[1], 
                    Integer.parseInt(campos[2]), campos[3], campos[4]);
                array.add(ligacao);
            }
        }
    }
    
    public void gravarArquivo(ArrayList<Cliente> array) throws IOException{
        String enviar = "";
        
        for (Cliente cliente : array) {
            enviar += enviar.format("%s;/%s;/%d;/%d\n", 
                    cliente.numero, cliente.nome, cliente.plano, cliente.credito);
        }
        
        // Preparar para escrever no arquivo
        FileWriter fw = new FileWriter(new File(file));
        BufferedWriter bw = new BufferedWriter(fw);
        //Enviar e fechar
        bw.append(enviar);
        bw.close();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
