import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import dao.PessoaDAO;
import entity.Pessoa;

public class App {
    public static String leString(String msg) {
        String valor = JOptionPane.showInputDialog(null, msg);
        return valor;
    }

    public static int menu() {
        Scanner teclado = new Scanner(System.in);        
        System.out.println("MENU");
        System.out.println("1- Inserir");
        System.out.println("2- Listar todos");
        System.out.println("3- Listar por id");
        System.out.println("4- Sair");
        System.out.print("Digite: ");
        return teclado.nextInt();
    }
    
    public static void metodoInserir() {
        String nome = leString("Digite nome");
        String email = leString("Digite e-mail");
        Pessoa pessoa = new Pessoa(nome,email);
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.inserir(pessoa);        
    }

    public static void metodoConsultarTodos() {
        // Metodo que percorre a lista retornada e exibe os registros
        List<Pessoa> registros  = new PessoaDAO().consultarTodos();
        if (!registros.isEmpty()){
            String saida = "";
            saida += ("|id| \t |nome| \t |email| \n");
            for (int i = 0; i < registros.size(); i++){
                Pessoa p = registros.get(i);
                saida += p.getId()+ "\t";
                saida= saida+ p.getNome() + "\t";
                saida += p.getEmail()+"\n";

            }
            JOptionPane.showMessageDialog(null, new JTextArea(saida));

        }else{
            System.out.println("There is not registro");
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        int op;
        do{
            op = menu();
            switch (op){
                case 1:
                    metodoInserir();
                    break;
                case 2:
                    metodoConsultarTodos();
                    break;

                case 3:
                    String idStr = leString("Digite id: ");
                    // Convertendo String para Int 
                    int idInt = Integer.parseInt(idStr);
                    PessoaDAO dao = new PessoaDAO();
                    Pessoa pess = dao.consultar(idInt);
                    String saida;
                    if (pess !=null){
                        saida = ("|id| \t |nome| \t |email| \n");
                        saida += pess.getId()+ "\t";
                        saida= saida+ pess.getNome() + "\t";
                        saida += pess.getEmail()+"\n";


                    }else{
                        saida = "Registro invalido";
                       
                    }
                    JOptionPane.showMessageDialog(null, new JTextArea(saida));
                    break;
                case 4:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }while(op!=4);
    //     List<Pessoa> listaPessoas = pessoaDAO.consultarTodos();
    //     System.out.println(listaPessoas.isEmpty());
    //     System.out.println(listaPessoas);
    //     for (Pessoa p : listaPessoas) {
    //         System.out.println("--------");
    //         System.out.println("id: " + p.getId());
    //         System.out.println("nome: " + p.getNome());
    //         System.out.println("email: " + p.getEmail());
    //     }       
    }
}