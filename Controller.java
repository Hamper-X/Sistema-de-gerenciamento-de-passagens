import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.plaf.synth.SynthTextAreaUI;

class Controller {
    
    private ArrayList<Viagem>viagens;   // Contem lista de todas as viagens correntes 
    private Scanner keyboard = new Scanner(System.in);

    
    public Controller(){
        viagens = new ArrayList<Viagem>();    
    }

    public void startSystem(){
        String resp;
        while(true){
            resp = showMenu();
            switch(resp){
                case "1":
                    ReservarPassagem();
                    break;
                case "2":
                    ExcluirPassagem();
                    break;
                case "3":
                    System.out.println("Ce ja ta morto por dentro irmao..");
                    break;
                default:
                    System.out.println("30 anos de curso e ate hj nao sabe ler as opções.");
                    break;
            }            
        }
    }

    private void ReservarPassagem(){
        System.out.println(" Escolha qual viagem deseja reservar uma passagem:");
        int count = 0;
        for (Viagem viagem : viagens) {
            System.out.println(count + " - " + viagem.getLocalPartida()+" -> "+viagem.getLocalChegada());
            count++;
        }

        String op = keyboard.nextLine();
        for (Viagem viagem : viagens) {
            if(viagem.)    
        }
        // 2- escolher viagem e mostrar assentos possiveis 
    }
    private void ExcluirPassagem(){

    }
    

    private String showMenu(){
        System.out.println("|==========================================| Bem vindo a HateExpress |==========================================| ");
        System.out.println("                                    Você num caixão, logo depois do busão                                         ");
        System.out.println("Escolha o que deseja fazer:");
        System.out.println(
            "1- Reservar uma passagem para uma viagem especifica\n"+
            "2- Excluir reserva de passagem\n"+
            "3- Contratar servico funerario\n"+
            "4- Sair do menu.");        
        return keyboard.nextLine();
    } 

}
