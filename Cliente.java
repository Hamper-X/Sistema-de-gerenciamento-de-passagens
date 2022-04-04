import java.net.Socket;
import java.util.Scanner;
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

public class Cliente {
    
    public static void main(String[] args) {
        try {
            Scanner leitor = new Scanner(System.in);
            // Cria a conexão entre o cliente e o servidor 
            Socket socket = new Socket("127.0.0.1",7070);  
                        
            // Para mandar dados para o servidor
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            // Para receber dados do servidor  
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String resp = "";
            boolean continuar = true;
            // confirmação inicial
            output.writeBoolean(true);
            output.flush();

            // recebimento do menu
            resp = input.readUTF(); 
            System.out.println(resp);

            // envio da opção
            output.writeInt(leitor.nextInt());
            output.flush();

            // recebimento dos assentos
            resp = input.readUTF(); 
            System.out.println(resp);
            
            // envio da opção assento
            output.writeUTF(leitor.next());
            output.flush();

            // recebimento da confirmação
            resp = input.readUTF(); 
            System.out.println(resp);

            

            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            //TODO: handle exception
            System.out.println("ERRO cliente: "+e.getMessage());
        }
        
        
    }
}
