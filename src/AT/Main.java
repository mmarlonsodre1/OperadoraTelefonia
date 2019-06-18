package AT;

import static AT.Main.showInex;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[]args) throws IOException{
        int menu = 0, submenu = 0, valor = 0;
        double minutos;
        String[] stringArray = new String[10];
        String numero, mostrarSubmenu = "";
        DecimalFormat df = new DecimalFormat("###,###");
        DecimalFormat df1 = new DecimalFormat("###,###.00");
        
        try{
            File fileDados = new File("Dados.txt");
            File fileRelatorio = new File("Relatorio.txt");
        } catch(Exception e){
             System.out.println(e);
            }
        Arquivo arq = new Arquivo();
        
       do {
           try{
               menu = Integer.parseInt(JOptionPane.showInputDialog("[1] Inclusão de cliente \n[2] Alteração do cliente "
                       + "\n[3] Exclusão do cliente \n[4] Relatório \n[5] Sair do programa"));
               
               switch(menu){
                   case 1: //Inclusão
                       inclusaoMetodo(arq);
                       break;
                   case 2: //Alteracao
                       editarMetodo(arq);
                       break;
                   case 3: //Exclusao
                       deletMetodo(arq);
                       break;
                   case 4: //SubMenu
                       relatorioMetodo(arq);
                       break;
                   case 5:
                       break;
                   default:
                       showInex("Opção Incorreta");
                       
               }
           }catch(Exception e){
               showInex(e); //"Digite apenas números"
           }
           
       }while(menu != 5);
    }
    
    public static void showInex(String msg){
        JOptionPane.showMessageDialog(null, msg, null, JOptionPane.DEFAULT_OPTION); 
    }
    
    public static void inpt(String msg){
        JOptionPane.showInputDialog(msg);
    }
    
    public static String juntar(String numero, String nome, String plano, String credito){
        String enviar  = numero + ";/" + nome + ";/" + plano + ";/" + credito;
        return enviar;
    }
    
    public static String[] inclusao(){
        String[] saida = new String[4];
        int ver = 0;
        boolean x = true;
        
        saida[1] = JOptionPane.showInputDialog("Digite o Nome do cliente");
        
        x = true;
        while(x) {
            try{
            ver = Integer.parseInt(JOptionPane.showInputDialog("[1] Pré-Pago \n[2]Pós-Pago"));
                saida[2] = String.valueOf(ver);
                if (saida[2].equals("1") || saida[2].equals("2")){
                    x = false;
                } else{
                    showInex("Digitou de forma incorreta");
                }
            } catch(Exception e){
                showInex("Digitou de forma incorreta");
            }
        }
        if(ver == 1){
            x = true;
            while(x) {
                try{
                    ver = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor de crédito (Somente valor inteiro)"));
                    saida[3] = String.valueOf(ver);
                    x = false;
                } catch(Exception e){
                    showInex("Digitou de forma incorreta");
                }
            }
        } else {
            saida[3] = "0";
        }
        return saida;
    }
    
    public static String numeroTelefone(){
        int ver;
        String numero = "erro";
        
        try{
            ver = Integer.parseInt(JOptionPane.showInputDialog("Digite o Número de telefone (8 digitos numéricos)"));
            numero = String.valueOf(ver);
            if (numero.length() == 8){
            } else{
                showInex("Digitou de forma incorreta");
                numero = "erro";
                
            }
        } catch(Exception e){
            showInex("Digitou de forma incorreta");
        }
        return numero;
    }
    
    public static String[] alteracao(String[] string){
        String[] saida = new String[4];
        int ver;
        boolean x = true;
        
        saida[1] = JOptionPane.showInputDialog("Digite o Nome do cliente \nAntigo: " + string[1]);
        
        x = true;
        while(x) {
            try{
            ver = Integer.parseInt(JOptionPane.showInputDialog("[1] Pré-Pago \n[2]Pós-Pago \nAntigo: " + string[2]));
                saida[2] = String.valueOf(ver);
                if (saida[2].equals("1") || saida[2].equals("2")){
                    x = false;
                } else{
                    showInex("Digitou de forma incorreta");
                }
            } catch(Exception e){
                showInex("Digitou de forma incorreta");
            }
        }
        x = true;
        while(x) {
            try{
                ver = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor de crédito (Somente valor inteiro) \nAntigo: " + string[3]));
                saida[3] = String.valueOf(ver);
                x = false;
            } catch(Exception e){
                showInex("Digitou de forma incorreta");
            }
        }
        return saida;
    }
    
    public static void inclusaoMetodo(Arquivo arq){
        String numero = "";
        String[] stringArray = new String[4];
        try {
            arq.ler("Dados.txt");
        numero = numeroTelefone();
        if(numero != "erro"){
         if (arq.verificarNumero(numero)== false){
             stringArray = inclusao();
             arq.gravar("Dados.txt", juntar(numero, stringArray[1],
                stringArray[2], stringArray[3]));
         } else{
            showInex("Número Inexistente");
         }
        }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public static void editarMetodo(Arquivo arq){
        String[] stringArray = new String[4];
        try {
            arq.ler("Dados.txt");
            if (arq.verificarNumero(numeroTelefone())){
                stringArray = alteracao(arq.verificaocao);
                arq.verificaocao[1] = stringArray[1];
                arq.verificaocao[2] = stringArray[2];
                arq.verificaocao[3] = stringArray[3];
                arq.alterar("Dados.txt");
            } else{
               showInex("Número Inexistente");
            }  
        } catch (Exception e) {
        }
    }
        
    public static void deletMetodo(Arquivo arq){
        try {
            arq.ler("Dados.txt");
            if (arq.verificarNumero(numeroTelefone())){
                 arq.remover("Dados.txt");
             } else{
                showInex("Número Inexistente");
             }
        } catch (Exception e) {
        }
    }

  

    public static void relatorioMetodo(Arquivo arq){
        int submenu = 0;
        try{
            submenu = Integer.parseInt(JOptionPane.showInputDialog("[1] Lista de clientes \n[2] Clientes com número de créditos igual ou menor a zero "
                       + "\n[3] Clientes que tem crédito acima de um determinado valor \n[4] Listar a conta com o maior número de crédito "
                               + "\n[5] Relatório de ligações \n[6] Voltar para menu anterior"));
                       switch(submenu){
                            case 1: //listar tudo
                                PrimeiroMetodo(arq);
                                break;
                            case 2: //listar < 0
                                SegundoMetodo(arq);
                                break;
                            case 3: //listar > que valor
                                TerceiroMetodo(arq);
                                break;
                            case 4: // listar maior
                                QuartoMetodo(arq);
                                break;
                            case 5: //Boleto
                                QuintoMetodo(arq);
                                break;
                            case 6:
                                 break;
                            default:
                                showInex("Opção Incorreta");
                                break;
                       }
        } catch (Exception e){
        }
        
    }
    public static void PrimeiroMetodo(Arquivo arq){
        String[] stringArray = new String[4];
        String mostrarSubmenu = "";

        try {
          arq.ler("Dados.txt");
            mostrarSubmenu = "";
            for (int i = 0; i < arq.array.size(); i++) { // mostrar tudo
                stringArray = String.valueOf(arq.array.get(i)).split(";/");
                mostrarSubmenu += String.format("Nome: %s \nNúmero: %s \nPlano: %s \nCrédito : %s \n \n",
                        stringArray[1], stringArray[0], stringArray[2], stringArray[3]);
            }
            showInex(mostrarSubmenu);  
        } catch (Exception e) {
        }
    }
    
    public static void SegundoMetodo(Arquivo arq){
        String[] stringArray = new String[4];
        String mostrarSubmenu = "";
        
        try {
            arq.ler("Dados.txt");
            mostrarSubmenu = "";
            for (int i = 0; i < arq.array.size(); i++) {
                stringArray = String.valueOf(arq.array.get(i)).split(";/");
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
            showInex(mostrarSubmenu); 
        } catch (Exception e) {
        }
    }
    
    public static void TerceiroMetodo(Arquivo arq){
        int valor = 0;
        String[] stringArray = new String[10];
        String mostrarSubmenu = "";
        try {
            valor = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor"));
            arq.ler("Dados.txt");
            mostrarSubmenu = "";
            for (int i = 0; i < arq.array.size(); i++) {
                stringArray = String.valueOf(arq.array.get(i)).split(";/");
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
            valor = 0;
            showInex(mostrarSubmenu);
        } catch (Exception e) {
        }
    }
    
    public static void QuartoMetodo(Arquivo arq){
        int valor = 0;
        String[] stringArray = new String[10];
        String mostrarSubmenu = "";
        
        try {
            arq.ler("Dados.txt");
            mostrarSubmenu = "";
            for (int i = 0; i < arq.array.size(); i++) {
                if(stringArray[2].equals("1")){
                    if (Integer.parseInt(String.valueOf(arq.array.get(i)).split(";/")[3]) > valor){ //Achar o maior valor de crédito
                        valor = Integer.parseInt(String.valueOf(arq.array.get(i)).split(";/")[3]);
                        stringArray = String.valueOf(arq.array.get(i)).split(";/");
                        mostrarSubmenu = String.format("Nome: %s \nNúmero: %s \nPlano: %s \nCrédito : %s \n \n",
                                stringArray[1], stringArray[0], stringArray[2], stringArray[3]);
                    }
                }
            }
            valor = 0;
            if(mostrarSubmenu.equals("")){
                mostrarSubmenu = "Sem clientes";
            }
            showInex(mostrarSubmenu);
        } catch (Exception e) {
        }
    }
    
    public static void QuintoMetodo(Arquivo arq){
        int valor = 0;
        String[] stringArray = new String[10];
        String numero, mostrarSubmenu = "";

        try {
            arq.ler("Relatorio.txt");
            mostrarSubmenu = "";
            numero = numeroTelefone();
            if (arq.verificarNumero(numero)){
                for (int i = 0; i < arq.array.size(); i++) {
                    if(String.valueOf(arq.array.get(i)).split(";/")[0].equals(numero)){
                        valor += Double.parseDouble(String.valueOf(arq.array.get(i)).split(";/")[4].replace(":", "")) - Double.parseDouble(String.valueOf(arq.array.get(i)).split(";/")[3].replace(":", ""));
                        stringArray[0] = String.valueOf(arq.array.get(i)).split(";/")[1];
                        stringArray[1] = String.valueOf(arq.array.get(i)).split(";/")[0];
                        stringArray[3] = String.valueOf(arq.array.get(i)).split(";/")[2];
                    }
                }
                stringArray[2] = String.valueOf(valor);

                if(stringArray[3].equals("1")){
                    arq.ler("Dados.txt");
                    arq.verificarNumero(numero);
                    arq.verificaocao[3] = String.valueOf(Integer.parseInt(arq.verificaocao[3]) - Integer.parseInt(stringArray[2]));
                    arq.alterar("Dados.txt");
                    mostrarSubmenu = String.format("Nome: %s \nNúmero: %s \nValor debitado do crédito: R$%s \nSaldo de crédito: R$%s \n \n", 
                        stringArray[0], stringArray[1], stringArray[2], arq.verificaocao[3]);   
                } else {
                    mostrarSubmenu = String.format("Nome: %s \nNúmero: %s \nValor do Boleto: R$%s \n \n", 
                        stringArray[0], stringArray[1], stringArray[2]);   
                }
                valor = 0;
            } else{
                showInex("Não Existem gastos para esse número");
            }
            showInex(mostrarSubmenu);
        } catch (Exception e) {
        }
    }    
    
 }

