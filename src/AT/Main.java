package AT;

import static AT.Main.showInex;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[]args) throws IOException {
        int menu = 0;
        
        Arquivo arq = new Arquivo();
        arq.ler();
        arq.lerRelatorio();
        
       do {
            menu = menu();
            switch(menu){
                case 1: //Inclusão
                    showInex(arq.inclusaoMetodo());
                    break;
                case 2: //Alteracao
                    showInex(arq.editarMetodo());
                    break;
                case 3: //Exclusao
                    showInex(arq.deletMetodo());
                    break;
                case 4: //SubMenu
                    relatorioMetodo(arq);
                    break;
                case 5:
                    break;
                default:
                    showInex("Opção Incorreta");
            }
       }while(menu != 5);
    }
    
    public static void relatorioMetodo(Arquivo arq) throws IOException{
        int submenu = subMenu();
        switch(submenu){
             case 1: //listar tudo
                 showInex(arq.listarTudo());
                 break;
             case 2: //listar < 0
                 showInex(arq.zerado());
                 break;
             case 3: //listar > que valor
                 showInex(arq.maiorQue(Integer.parseInt(JOptionPane.showInputDialog("Digite o valor"))));
                 break;
             case 4: // listar maior
                 showInex(arq.maiorCredito());
                 break;
             case 5: //Boleto
                 showInex(arq.despesas());
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
            showInex(e.toString());
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
            showInex(e.toString());
        }
        return subMenu;
    }
    
    public static void showInex(String msg){
        JOptionPane.showMessageDialog(null, msg, null, JOptionPane.DEFAULT_OPTION); 
    }
    
    public static void inpt(String msg){
        JOptionPane.showInputDialog(msg);
    }
}

