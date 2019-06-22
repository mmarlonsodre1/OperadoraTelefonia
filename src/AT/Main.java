package AT;

import static AT.Main.showInex;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[]args) throws IOException {
        int menu = 0;
        ArrayList<Cliente> arrayCliente = new ArrayList();
        ArrayList<Ligacao> arrayLigacao = new ArrayList();
        
        Arquivo arqCliente = new Arquivo("Dados.txt");
        Arquivo arqLigacao = new Arquivo("Relatorio.txt");
        
        arqCliente.ler(arrayCliente);
        arqLigacao.ler(arrayLigacao);
        
        Cliente cliente = new Cliente();
        
       do {
            menu = menu();
            switch(menu){
                case 1: //Inclusão
                    showInex(inclusaoMetodo(arrayCliente, cliente));
                    break;
                case 2: //Alteracao
                    showInex(editarMetodo(arrayCliente));
                    break;
                case 3: //Exclusao
                    showInex(deletMetodo(arrayCliente));
                    break;
                case 4: //SubMenu
                    relatorioMetodo(arrayCliente, arrayLigacao);
                    break;
                case 5:
                    arqCliente.gravarArquivo(arrayCliente);
                    break;
                default:
                    showInex("Opção Incorreta");
            }
       }while(menu != 5);
    }
    
    public static void relatorioMetodo(ArrayList<Cliente> arrayCliente, ArrayList<Ligacao> arrayLigacao) throws IOException{
        int submenu = subMenu();
        switch(submenu){
             case 1: //listar tudo
                 showInex(listarTudo(arrayCliente));
                 break;
             case 2: //listar < 0
                 showInex(zerado(arrayCliente));
                 break;
             case 3: //listar > que valor
                 showInex(maiorQue(arrayCliente, Integer.parseInt(JOptionPane.showInputDialog("Digite o valor"))));
                 break;
             case 4: // listar maior
                 showInex(maiorCredito(arrayCliente));
                 break;
             case 5: //Boleto
                 showInex(despesas(arrayLigacao, arrayCliente));
                 break;
             case 6:
                  break;
             default:
                 showInex("Opção Incorreta");
                 break;
        }
    }
    
    public static int menu(){
        int menu = 0;
        try{
            menu = Integer.parseInt(JOptionPane.showInputDialog("[1] Inclusão de cliente \n[2] Alteração do cliente "
                       + "\n[3] Exclusão do cliente \n[4] Relatório \n[5] Sair do programa"));
        } catch(Exception e){
        }
        return menu;
    }
    
    public static int subMenu(){
        int subMenu = 0;
        try{
            subMenu = Integer.parseInt(JOptionPane.showInputDialog("[1] Lista de clientes \n[2] Clientes com número de créditos igual ou menor a zero "
                       + "\n[3] Clientes que tem crédito acima de um determinado valor \n[4] Listar a conta com o maior número de crédito "
                               + "\n[5] Relatório de ligações \n[6] Voltar para menu anterior"));
        } catch(Exception e){
        }
        return subMenu;
    }
    
    public static void showInex(String msg){
        JOptionPane.showMessageDialog(null, msg, null, JOptionPane.DEFAULT_OPTION); 
    }
    
    public static void inpt(String msg){
        JOptionPane.showInputDialog(msg);
    }
    
    public static int verificarNumeroDados(ArrayList<Cliente> array, String numero) throws FileNotFoundException, IOException{
        int pos = -1 ;
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i).getNumero());
            if (array.get(i).getNumero().equals(numero)){
                pos = i;
                break;
            }
        }
        return pos; 
    }
    
    public static int verificarNumeroRelatorio(ArrayList<Ligacao> array, String numero) throws FileNotFoundException, IOException{
        int pos = -1 ;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getNumero().equals(numero)){
                return pos = i;
            }
        }
        return pos;
    }
    
    public static String numeroTelefone(){
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
            System.out.println(e.toString());
            return "Digitou de forma incorreta";
        }
    }
    
    public static String nomeCliente(String entrada){
        String saida = "";
        
        do{
            saida = JOptionPane.showInputDialog(entrada);
        } while(saida.equals(""));
        return saida;
    }
    
    public static int verificarPlano(String entrada){
        int ver = 0 ;
        
        do{
            try{
                ver = Integer.parseInt(JOptionPane.showInputDialog(entrada));
                if (ver == 1 || ver == 2){
                    return ver;
                } else{
                    }
            } catch(Exception e){
                showInex("Digitou de forma incorreta");
            }
        } while(ver != 1 || ver != 2);
        return ver;
    }
    
    public static int verificarCredito(int plano, String entrada){
        int ver = 0;

        System.out.println(plano);
        if(plano == 1){
            do{
                try{
                    ver = Integer.parseInt(JOptionPane.showInputDialog(entrada));
                    return ver;
                } catch(Exception e){
                    showInex("Digite apenas números inteiros");
                    ver = -1;
                }
            } while(ver == -1);
        } else {
            ver = 0;
        }
        return ver;
    }
    
    public static String inclusaoMetodo(ArrayList<Cliente> array, Cliente cliente){
        String numero = numeroTelefone();
        
        try {
            if(!numero.equals("Digitou de forma incorreta")){
               if (verificarNumeroDados(array, numero) == -1){
                    cliente.numero = numero;
                    cliente.nome = nomeCliente("Digite o Nome do cliente");
                    cliente.plano = verificarPlano("[1] Pré-Pago \n[2] Pós-Pago");
                    cliente.credito = verificarCredito(cliente.plano, "Digite o valor de crédito (Somente valor inteiro)");
                    array.add(cliente);
                    return "Cliente adicionado";
                } else{
                   return "Número Existente";
                } 
            } else {
                return "Digitou de forma incorreta";
            }
        } catch (Exception e) {
            return "Digitou de forma incorreta";
        }
    }
    
    public static String editarMetodo(ArrayList<Cliente> array) throws IOException{
        String[] stringArray = new String[3];
        String numero = numeroTelefone();
        int plano;
        
        if(!numero.equals("Digitou de forma incorreta")){
            int pos = verificarNumeroDados(array, numero);
            if (pos != -1){
                //Input do JOptionPane
                stringArray[0] = "Digite o Nome do cliente \nAntigo: " + array.get(pos).getNome();
                stringArray[1] = "[1] Pré-Pago \n[2] Pós-Pago \nAntigo: " + String.valueOf(array.get(pos).getPlano());
                stringArray[2] = "Digite o valor de crédito (Somente valor inteiro) \nAntigo: " + String.valueOf(array.get(pos).getCredito());
                System.out.println(array.get(pos).getPlano());
                array.set(pos, new Cliente(numero, nomeCliente(stringArray[0]), 
                        (plano = verificarPlano(stringArray[1])), verificarCredito(plano, stringArray[2])));
                return "Editado com sucesso";
            } else{
               return "Número Inexistente";
            }
        } else{
            return "Digitou de forma incorreta";
        }
    }
    
    public static String deletMetodo(ArrayList array) throws IOException{
        int pos = verificarNumeroDados(array, numeroTelefone());
        if (pos != 1){
             array.remove(pos);
             return "Deletado com sucesso";
         } else{
            return "Número Inexistente";
        }
    }
    
    public static String listarTudo(ArrayList<Cliente> array){
        String mostrarSubmenu = "Sem Clientes";

        mostrarSubmenu = "";
        for (int i = 0; i < array.size(); i++) { // mostrar tudo
            mostrarSubmenu += String.format("Nome: %s \nNúmero: %s \nPlano: %d \nCrédito : %d \n \n",
                    array.get(i).getNome(), array.get(i).getNumero(), array.get(i).getPlano(), array.get(i).getCredito());
        }
        return mostrarSubmenu; 
    }
    
    public static String zerado(ArrayList<Cliente> array) throws IOException{
        String mostrarSubmenu = "";

        for (int i = 0; i < array.size(); i++) {
            if(array.get(i).getPlano() == 1){
                if (array.get(i).getCredito() < 1){ //Se o crédito for menor que 1
                    mostrarSubmenu += String.format("Nome: %s \nNúmero: %s \nPlano: %d \nCrédito :%d \n \n",
                            array.get(i).getNome(), array.get(i).getNumero(), array.get(i).getPlano(), array.get(i).getCredito());
                }
            }
        }
        if(mostrarSubmenu.equals("")){
            mostrarSubmenu = "Sem clientes";
        }
        return mostrarSubmenu; 
    }
    
    public static String maiorQue(ArrayList<Cliente> array, int valor){
        String mostrarSubmenu = "";
        
        mostrarSubmenu = "";
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i).getPlano() == 1){
                if (array.get(i).getCredito() > valor){  // Se o credito for maior que o valor estipulado
                    mostrarSubmenu += String.format("Nome: %s \nNúmero: %s \nPlano: %d \nCrédito : %d \n \n",
                            array.get(i).getNome(), array.get(i).getNumero(), array.get(i).getPlano(), array.get(i).getCredito());
                }
            }
        }
        if(mostrarSubmenu.equals("")){
            mostrarSubmenu = "Sem clientes";
        }
        return mostrarSubmenu;
    }
    
    public static String maiorCredito(ArrayList<Cliente> array){
        int valor = 0;
        String mostrarSubmenu = "";
        
        mostrarSubmenu = "";
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i).getPlano() == 1){
                if (array.get(i).getCredito() > valor){ //Achar o maior valor de crédito
                    valor = array.get(i).getCredito();
                    mostrarSubmenu = String.format("Nome: %s \nNúmero: %s \nPlano: %d \nCrédito : %d \n \n",
                                array.get(i).getNome(), array.get(i).getNumero(), array.get(i).getPlano(), array.get(i).getCredito());
                }
            }
        }
        if(mostrarSubmenu.equals("")){
            mostrarSubmenu = "Sem clientes";
        }
        return mostrarSubmenu;
    }
    
    public static String despesas(ArrayList<Ligacao> arrayRelatorio, ArrayList<Cliente> arrayCliente) throws IOException{
        long valor = 0;

        String mostrarSubmenu = "", numero = numeroTelefone();
        int pos = verificarNumeroRelatorio(arrayRelatorio, numero);
        if (pos != 1){
            for (int i = 0; i < arrayRelatorio.size(); i++) {
                if(arrayRelatorio.get(i).getNumero().equals(numero)){
                    String antes = arrayRelatorio.get(i).gethInicio();
                    String depois = arrayRelatorio.get(i).gethFim();
                    
                    //Calcular valores da ligacao
                    LocalTime lt1 = LocalTime.parse(antes);
                    LocalTime lt2 = LocalTime.parse(depois);
                    valor += lt1.until(lt2, ChronoUnit.MINUTES);
                    
                    if(valor < 1){
                        valor = 1;
                    }
                }
            }

            if(arrayRelatorio.get(pos).getPlano() == 1){
                int posCliente = verificarNumeroDados(arrayCliente, numero);
                arrayCliente.get(posCliente).setCredito(arrayCliente.get(posCliente).getCredito() - Integer.parseInt(String.valueOf(valor)));
                mostrarSubmenu = String.format("Nome: %s \nNúmero: %s \nValor debitado do crédito: R$%d \nSaldo de crédito: R$%d \n \n", 
                    arrayRelatorio.get(pos).getNome(), arrayRelatorio.get(pos).getNumero(), valor, arrayCliente.get(posCliente).getCredito());   
            } else {
                mostrarSubmenu = String.format("Nome: %s \nNúmero: %s \nValor do Boleto: R$%s \n \n", 
                    arrayRelatorio.get(pos).getNome(), arrayRelatorio.get(pos).getNumero(), valor);   
            }
            return mostrarSubmenu;
        } else{
            return "Não Existem gastos para esse número";
        }
    }  
}
