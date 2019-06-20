package AT;

import static AT.Main.showInex;
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
import javax.swing.JOptionPane;

public class Arquivo {
    ArrayList array = new ArrayList();
    ArrayList arrayRelatorio = new ArrayList();
    String[] verificaocao = new String[4];
    File fileDados;
    File fileRelatorio; 
    int indice = 0;
    
       
    public void ler() throws FileNotFoundException, IOException{
        // Ler o arquivo
        array.clear();
        FileReader ler = new FileReader("Dados.txt");
        BufferedReader reader = new BufferedReader(ler);  
        String linha;
        while( (linha = reader.readLine()) != null ){
            array.add(linha);
        }
    }
    
    public void lerRelatorio() throws FileNotFoundException, IOException{
        // Ler o arquivo
        arrayRelatorio.clear();
        FileReader ler = new FileReader("Relatorio.txt");
        BufferedReader reader = new BufferedReader(ler);  
        String linha;
        while( (linha = reader.readLine()) != null ){
            arrayRelatorio.add(linha);
        }
    }
    
    public void gravar(String string) throws IOException{
        //Ler arquivo para não sobreescrever
        FileReader ler = new FileReader("Dados.txt");
        BufferedReader reader = new BufferedReader(ler);  
        String linha, enviar = "";
        
        while( (linha = reader.readLine()) != null ){
            enviar += linha + "\n";
        }
        
        // Preparar para escrever no arquivo
        FileWriter fw = new FileWriter(new File("Dados.txt"));
        BufferedWriter bw = new BufferedWriter(fw);
        //Enviar e fechar
        bw.append(enviar + string);
        bw.close();
        ler();
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
    
    public void alterar() throws IOException{
        String enviar = "";
        array.set(indice, String.format("%s;/%s;/%s;/%s", verificaocao[0], 
                verificaocao[1], verificaocao[2], verificaocao[3]));
        for (int i = 0; i < array.size(); i++) {
            enviar += array.get(i) + "\n";
        }
        // Preparar para escrever no arquivo
        FileWriter fw = new FileWriter(new File("Dados.txt"));
        BufferedWriter bw = new BufferedWriter(fw);
        //Enviar e fechar
        bw.append(enviar);
        bw.close();
        ler();
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
        ler();
    }
    
    public String numeroTelefone(){
        int ver;
        String numero = "";
        
        try{
            ver = Integer.parseInt(JOptionPane.showInputDialog("Digite o Número de telefone (8 digitos numéricos)"));
            numero = String.valueOf(ver);
            if (numero.length() == 8){
                return numero;
            } else{
                return "Digitou de forma incorreta";
            }
        } catch(Exception e){
            return "Digitou de forma incorreta";
        }
    }
    
    public String nomeCliente(String entrada){
        return JOptionPane.showInputDialog(entrada);
    }
    
    public String verificarPlano(String entrada){
        String saida;
        int ver ;
        
        try{
            ver = Integer.parseInt(JOptionPane.showInputDialog(entrada));
            saida = String.valueOf(ver);
            if (saida.equals("1") || saida.equals("2")){
                return saida;
            } else{
                return "Plano Incorreto";
                }
        } catch(Exception e){
            return "Digitou de forma incorreta";
        }
    }
    
    public String verificarCredito(int plano, String entrada){
        int ver;
        if(plano == 1){
            try{
                ver = Integer.parseInt(JOptionPane.showInputDialog(entrada));
                return String.valueOf(ver);
            } catch(Exception e){
                return "Digitou de forma incorreta";
            }
        } else {
            return "0";
        }
    }
    
    public String[] verificacaoInformacoes(String[] entradas){
        String[] array = new String[4];
        boolean x = true;

        while(x){
            array[1] = nomeCliente(entradas[0]);
            if(!array[1].equals("")){
                break;
            }
        }
        
        while(x){
            array[2] = verificarPlano(entradas[1]);
            if(!array[2].equals("Plano Incorreto") && !array[2].equals("Digitou de forma incorreta")){
                break;
            }
        }
        
        while(x){
            array[3] = verificarCredito(Integer.parseInt(array[2]), entradas[2]);
            if(!array[3].equals("Digitou de forma incorreta")){
                break;
            }
        }
        return array;
    }
    
    public String juntarInclusao(String numero, String nome, String plano, String credito){
        String enviar  = numero + ";/" + nome + ";/" + plano + ";/" + credito;
        return enviar;
    }
    
    public String inclusaoMetodo(){
        String numero;
        String[] stringArray = new String[4];
        
        //Input do JOptionPane
        stringArray[0] = "Digite o Nome do cliente";
        stringArray[1] = "[1] Pré-Pago \n[2]Pós-Pago";
        stringArray[2] = "Digite o valor de crédito (Somente valor inteiro)";
        
        try {
            numero = numeroTelefone();
            if(!numero.equals("Digitou de forma incorreta")){
               if (verificarNumero(numero)== false){
                    stringArray = verificacaoInformacoes(stringArray);
                    gravar(juntarInclusao(numero, stringArray[1],
                       stringArray[2], stringArray[3]));
                    return "Cliente adicionado";
                } else{
                   return "Número Existente";
                } 
            } else {
                return "Digitou de forma incorreta";
            }
        } catch (Exception e) {
            return e.toString();
        }
    }
    
    public String editarMetodo() throws IOException{
        String[] stringArray = new String[4];
        String numero = numeroTelefone();
        
        if(!numero.equals("Digitou de forma incorreta")){
            if (verificarNumero(numero)){
                //Input do JOptionPane
                stringArray[0] = "Digite o Nome do cliente \nAntigo: " + verificaocao[1];
                stringArray[1] = "[1] Pré-Pago \n[2]Pós-Pago \nAntigo: " + verificaocao[2];
                stringArray[2] = "Digite o valor de crédito (Somente valor inteiro) \nAntigo: " + verificaocao[3];
                
                stringArray = verificacaoInformacoes(stringArray);
                verificaocao[1] = stringArray[1];
                verificaocao[2] = stringArray[2];
                verificaocao[3] = stringArray[3];
                alterar();
                return "Editado com sucesso";
            } else{
               return "Número Inexistente";
            }
        } else{
            return "Digitou de forma incorreta";
        }
    }
    
    public String deletMetodo() throws IOException{
        if (verificarNumero(numeroTelefone())){
             remover("Dados.txt");
             return "Deletado com sucesso";
         } else{
            return "Número Inexistente";
        }
    }
    
    public String listarTudo(){
        String[] stringArray = new String[4];
        String mostrarSubmenu = "Sem Clientes";

        try {
            mostrarSubmenu = "";
            for (int i = 0; i < array.size(); i++) { // mostrar tudo
                stringArray = String.valueOf(array.get(i)).split(";/");
                mostrarSubmenu += String.format("Nome: %s \nNúmero: %s \nPlano: %s \nCrédito : %s \n \n",
                        stringArray[1], stringArray[0], stringArray[2], stringArray[3]);
            }
             
        } catch (Exception e) {
            showInex(e.toString());
        }
        return mostrarSubmenu; 
    }
    
    public String zerado() throws IOException{
        String[] stringArray = new String[4];
        String mostrarSubmenu = "";

        for (int i = 0; i < array.size(); i++) {
            stringArray = String.valueOf(array.get(i)).split(";/");
            if(stringArray[2].equals("1")){
                if (Integer.parseInt(stringArray[3]) < 1){ //Se o crédito for menor que 1
                    mostrarSubmenu += String.format("Nome: %s \nNúmero: %s \nPlano: %s \nCrédito :%s \n \n",
                            stringArray[1], stringArray[0], stringArray[2], stringArray[3]);
                }
            }
        }
        if(mostrarSubmenu.equals("")){
            mostrarSubmenu = "Sem clientes";
        }
        return mostrarSubmenu; 
    }
    
    public String maiorQue(int valor){
        String[] stringArray = new String[4];
        String mostrarSubmenu = "";
        
        mostrarSubmenu = "";
        for (int i = 0; i < array.size(); i++) {
            stringArray = String.valueOf(array.get(i)).split(";/");
            if(stringArray[2].equals("1")){
                if (Integer.parseInt(stringArray[3]) > valor){  // Se o credito for maior que o valor estipulado
                    mostrarSubmenu += String.format("Nome: %s \nNúmero: %s \nPlano: %s \nCrédito : %s \n \n",
                            stringArray[1], stringArray[0], stringArray[2], stringArray[3]);
                }
            }
        }
        if(mostrarSubmenu.equals("")){
            mostrarSubmenu = "Sem clientes";
        }
        return mostrarSubmenu;
    }
    
    public String maiorCredito(){
        int valor = 0;
        String[] stringArray = new String[10];
        String mostrarSubmenu = "";
        
        try {
            mostrarSubmenu = "";
            for (int i = 0; i < array.size(); i++) {
                stringArray = String.valueOf(array.get(i)).split(";/");
                if(stringArray[2].equals("1")){
                    if (Integer.parseInt(String.valueOf(array.get(i)).split(";/")[3]) > valor){ //Achar o maior valor de crédito
                        valor = Integer.parseInt(String.valueOf(array.get(i)).split(";/")[3]);
                        stringArray = String.valueOf(array.get(i)).split(";/");
                        mostrarSubmenu = String.format("Nome: %s \nNúmero: %s \nPlano: %s \nCrédito : %s \n \n",
                                stringArray[1], stringArray[0], stringArray[2], stringArray[3]);
                    }
                }
            }
            if(mostrarSubmenu.equals("")){
                mostrarSubmenu = "Sem clientes";
            }
            return mostrarSubmenu;
        } catch (Exception e) {
            return e.toString();
        }
    }
    
    public String despesas() throws IOException{
        int valor = 0;
        String[] stringArray = new String[10];

        String mostrarSubmenu = "", numero = numeroTelefone();

        if (verificarNumero(numero)){
            for (int i = 0; i < arrayRelatorio.size(); i++) {
                if(String.valueOf(arrayRelatorio.get(i)).split(";/")[0].equals(numero)){
                    valor += Double.parseDouble(String.valueOf(arrayRelatorio.get(i)).split(";/")[4].replace(":", "")) - Double.parseDouble(String.valueOf(arrayRelatorio.get(i)).split(";/")[3].replace(":", ""));
                    stringArray[0] = String.valueOf(arrayRelatorio.get(i)).split(";/")[1];
                    stringArray[1] = String.valueOf(arrayRelatorio.get(i)).split(";/")[0];
                    stringArray[3] = String.valueOf(arrayRelatorio.get(i)).split(";/")[2];
                }
            }
            stringArray[2] = String.valueOf(valor);

            if(stringArray[3].equals("1")){
                verificaocao[3] = String.valueOf(Integer.parseInt(verificaocao[3]) - Integer.parseInt(stringArray[2]));
                alterar();
                mostrarSubmenu = String.format("Nome: %s \nNúmero: %s \nValor debitado do crédito: R$%s \nSaldo de crédito: R$%s \n \n", 
                    stringArray[0], stringArray[1], stringArray[2], verificaocao[3]);   
            } else {
                mostrarSubmenu = String.format("Nome: %s \nNúmero: %s \nValor do Boleto: R$%s \n \n", 
                    stringArray[0], stringArray[1], stringArray[2]);   
            }
            return mostrarSubmenu;
        } else{
            return "Não Existem gastos para esse número";
        }
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
