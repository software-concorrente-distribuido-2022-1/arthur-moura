package exercicio2;


import java.net.*;
import java.io.*;

class Conexao extends Thread {

    final String msgBadRqt = "400 Bad Request";

    Socket socketCliente;

    Conexao(Socket aSocketCliente) throws IOException {
        this.socketCliente = aSocketCliente;
    }

    public void run() {

        boolean erroRqt = false;
        String msgErro = null;

        PrintWriter saida = null;
        BufferedReader entrada = null;

        InetAddress endCliente = this.socketCliente.getInetAddress();

        String nome = null;
        String sexo = null;
        String idade = null;
        
        String linhaArq = null;

        try {
            saida = new PrintWriter(this.socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));

            nome = entrada.readLine();
            sexo = entrada.readLine();
            idade = entrada.readLine();
           
            if (sexo.equals("masculino")) {
                int id = Integer.parseInt(idade);
                if (id >= 18) saida.println("Ja atingiu a maior idade");
                else saida.println("Nao atingiu a maior idade");
            }

            else if (sexo.equals("feminino")) {
                int id = Integer.parseInt(idade);
                if (id >= 21) saida.println("Ja atingiu a maior idade");
                else saida.println("Nao atingiu a maior idade");
            }

            else {
                erroRqt = true;
                msgErro = msgBadRqt;
                saida.println("Erro ...!");
            }           

            socketCliente.close();
            saida.close();
            entrada.close();

        } catch (IOException e) {
            System.out.println("Erro E/S " + e);
        }
    }
}

public class Servidor {
    public static void main(String[] args) throws IOException {

        final int portaDefault = 8080;

        int porta;
        int backlog = 5;

        Socket socketCliente = null;
        ServerSocket socketServidor = null;

        if ((args.length == 1))
            porta = Integer.parseInt(args[0]);
        else
            porta = portaDefault;

        while (true) {
            try {
                socketServidor = new ServerSocket(porta, backlog);
                break;
            } catch (IOException e) {
                porta++;
            }
        }

        System.out.println("\nServidor ativado. " +
                "Aguardando Cliente na porta " + porta + "...\n");

        while (true) {

            socketCliente = null;
            try {
                socketCliente = socketServidor.accept();
            } catch (IOException e) {
                System.err.println("Erro de E/S " + e);
                System.exit(1);
            }

            new Conexao(socketCliente).start();
        }
    }
}
