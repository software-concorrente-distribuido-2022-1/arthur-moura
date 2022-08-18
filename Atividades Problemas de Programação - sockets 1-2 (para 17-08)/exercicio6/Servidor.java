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
        String nivel = null;
        String salarioB = null;
        String dependentes = null;
        
        String linhaArq = null;

        try {
            saida = new PrintWriter(this.socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));

            nome = entrada.readLine();
            nivel = entrada.readLine();
            salarioB = entrada.readLine();
            dependentes = entrada.readLine();

            Integer ndep = Integer.parseInt(dependentes);
            double sb = Double.parseDouble(salarioB);

            if (nivel.equals("A")) {
                if(ndep > 0) sb = sb * 0.92;
                else sb = sb * 0.97;
                saida.println("Salario atualizado: " + sb);
            }

            else if (nivel.equals("B")) {
                if(ndep > 0) sb = sb * 0.90;
                else sb = sb * 0.95;
                saida.println("Salario atualizado: " + sb);
            }

            else if (nivel.equals("C")) {
                if(ndep > 0) sb = sb * 0.85;
                else sb = sb * 0.92;
                saida.println("Salario atualizado: " + sb);
            }

            else if (nivel.equals("D")) {
                if(ndep > 0) sb = sb * 0.83;
                else sb = sb * 0.9;
                saida.println("Salario atualizado: " + sb);
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
