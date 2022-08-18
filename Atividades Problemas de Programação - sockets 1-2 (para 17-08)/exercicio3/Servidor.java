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

        String n1 = null;
        String n2 = null;
        String n3 = null;

        
        String linhaArq = null;

        try {
            saida = new PrintWriter(this.socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));

            n1 = entrada.readLine();
            n2 = entrada.readLine();
            n3 = entrada.readLine();

            double nota1 = Double.parseDouble(n1);
            double nota2 = Double.parseDouble(n2);
            double nota3 = Double.parseDouble(n3);

            double M = (nota1 + nota2) / 2;

            if (M >= 7)
                saida.println("Aluno aprovado.");

            else if (M > 3 && M < 7) {
                if((M + nota3) / 2 >= 5)
                    saida.println("Aluno aprovado.");
                else
                    saida.println("Aluno reprovado");
            }

            else
                saida.println("Aluno reprovado.");

            //else {
            //    erroRqt = true;
            //    msgErro = msgBadRqt;
            //    saida.println("Erro ...!");
            //}

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
