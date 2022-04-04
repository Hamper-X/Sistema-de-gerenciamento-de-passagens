import java.util.Scanner;

import javax.swing.text.AbstractDocument.BranchElement;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

class Viagem {
    private String localPartida;
    private String localChegada;
    private char[][] lugaresDisponiveis = new char[4][12]; 
    private char[] coluns = {'A','B','C','D'};

    public Viagem(String chegada, String partida){
        localChegada = chegada;
        localPartida = partida;
        for(int i = 0; i< 4; i++){
            for(int j=0; j<10; j++){
                lugaresDisponiveis[i][j] = ' ';
            }
        }
    }

    public String getLocalChegada() { return localChegada; }
    public String getLocalPartida() { return localPartida; }
    public char[][] getLugaresDisponiveis() { return lugaresDisponiveis; }
    
    public void setLocalChegada(String localChegada) { this.localChegada = localChegada; }
    public void setLocalPartida(String localPartida) { this.localPartida = localPartida; }
    public void setLugaresDisponiveis(char[][] lugaresDisponiveis) { this.lugaresDisponiveis = lugaresDisponiveis; }
    
    // Colocar letra na frente de cada linha 
    public String mostrarLugares(){
        String resp = "Escolha qual assento vc deseja reservar/cancelar, escreva o numero e letra sem espaço entre eles\n";
        resp += "   [0]  [1]  [2]  [3]  [4]  [5]  [6]  [7]  [8]  [9]\n";
        
        for(int i = 0; i< 4; i++){
            resp += coluns[i]+" < ";
            for(int j=0; j<10; j++){
                resp += " ["+lugaresDisponiveis[i][j]+"] ";
            }
            resp += "\n";
        }

        return resp;
    }

    public synchronized boolean ReservarPassagem(String lugar){
        int numero = Integer.parseInt(""+lugar.charAt(0));
        boolean resp = false;
        switch(lugar.charAt(1)){
            case 'A':
                if(lugaresDisponiveis[0][numero]==' '){
                    lugaresDisponiveis[0][numero]='x';
                    resp = true;
                }
                break;
            case 'B':
                if(lugaresDisponiveis[1][numero]==' '){
                    lugaresDisponiveis[1][numero]='x';
                    resp = true;
                }
                break;
            case 'C':
                if(lugaresDisponiveis[2][numero]==' '){
                    lugaresDisponiveis[2][numero]='x';
                    resp = true;
                }
                break;
            case 'D':
                if(lugaresDisponiveis[3][numero]==' '){
                    lugaresDisponiveis[3][numero]='x';
                    resp = true;
                }
                break;
            default:
                break;
        }  
        return resp;  
    }

    public synchronized boolean ExcluirPassagem(String lugar){
        int numero = Integer.parseInt(""+lugar.charAt(0));
        boolean resp = false;
        switch(lugar.charAt(1)){
            case 'A':
                if(lugaresDisponiveis[0][numero]=='x'){
                    lugaresDisponiveis[0][numero]=' ';
                    resp = true;
                }
                break;
            case 'B':
                if(lugaresDisponiveis[1][numero]=='x'){
                    lugaresDisponiveis[1][numero]=' ';
                    resp = true;
                }
                break;
            case 'C':
                if(lugaresDisponiveis[2][numero]=='x'){
                    lugaresDisponiveis[2][numero]=' ';
                    resp = true;
                }
                break;
            case 'D':
                if(lugaresDisponiveis[3][numero]=='x'){
                    lugaresDisponiveis[3][numero]=' ';
                    resp = true;
                }
                break;
            default:
                break;
        }  
        return resp;      
    }
}

public class Server extends Thread {

    //private static Socket socketCliente = new Socket("localhost", 7070);
    private ServerSocket server;
    private Viagem viagem;


    // Construtor 
    public Server(){
        viagem = new Viagem("Belo Horizonte", "Rio de Janeiro");
        try {
            System.out.println("Aguardando conexão...");
            criarServerSocket(7070);
            
            while(true){
                Socket socket = esperarConeccao();
                System.out.println("Cliente conectado... \n IP: "+socket.getInetAddress());
                
                tratarConexao(socket);            
                System.out.println("Cliente finalizado...");
            }
        } catch (IOException e) {
            //TODO: handle exception
        }
    }

    // Setar porta
    public void criarServerSocket(int porta) throws IOException{ server = new ServerSocket(porta);}

    // Fica ouvindo parado ate que alguem estabeleça a conexão 
    private Socket esperarConeccao() throws IOException { return server.accept(); }

    // Criar streans de entrada e saida 
    private void tratarConexao(Socket socket)throws IOException{
        try {
            // Para mandar dados para o cliente
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            // Para receber dados do cliente 
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());    

            // Receber 1° mensagem cliente 
            if( input.readBoolean()){

                output.writeUTF(showMenu());
                output.flush();

                int op = input.readInt();
                String lugar = "";
                
                // System.out.println(viagem.mostrarLugares());
                switch(op){
                    case 1: 
                        System.out.println("1");
                        output.writeUTF(viagem.mostrarLugares());
                        output.flush();

                        lugar = input.readUTF();
                        if(viagem.ReservarPassagem(lugar)){
                            output.writeUTF("reserva realizada com sucesso!");
                            output.flush();
                        }else{
                            output.writeUTF("Erro: reserva mal sucedida! Verifique se o assento esta realmente reservado ou se vc deu a indicação correta do assento.");
                            output.flush();
                        }
                        break;
                    case 2:
                        System.out.println("2");
                        output.writeUTF(viagem.mostrarLugares());
                        output.flush();

                        lugar = input.readUTF();
                        if(viagem.ExcluirPassagem(lugar)){
                            output.writeUTF("Exclusão realizada com sucesso!");
                            output.flush();
                        }else{
                            output.writeUTF("Erro: exclusão mal sucedida! Verifique se o assento esta realmente reservado ou se vc deu a indicação correta do assento.");
                            output.flush();
                        }
                        break;
                    case 3:
                        System.out.println("3");
                        output.writeUTF("Sem comentario irmão, ce ja morreu por dentro...");
                        output.flush();
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Default");
                        output.writeUTF("Tem numero na frente das opções nao é atoa...");
                        output.flush();
                        break;
                }             
            }

            // Fechando streams
            input.close();
            output.close();
        } catch (IOException e) {
            //TODO: handle exception
            System.out.println("Problema no tratamento da mensagem do cliente: "+socket.getInetAddress());
            System.out.println("ERRO: "+e.getMessage());
        }finally{
            fecharSocket(socket); // Fechando comunicação cliente/servidor
        }      
    }

    // Fechar socket 
    private void fecharSocket(Socket socket)throws IOException{ socket.close(); }


    private String showMenu(){
        String menu = "";
        menu += ("|==========================================| Bem vindo a HateExpress |==========================================|\n");
        menu += ("                                    Você num caixão, logo depois do busão                                         \n");
        menu += ("Escolha o que deseja fazer:\n");
        menu += (
            "1- Reservar uma passagem para uma viagem especifica\n"+
            "2- Excluir reserva de passagem\n"+
            "3- Contratar servico funerario\n"+
            "4- Sair do menu.\n");        
        return menu;
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        
    }

    public static void main(String[] args) {
        Thread t = new Server();
        t.start();
            
    }
}
