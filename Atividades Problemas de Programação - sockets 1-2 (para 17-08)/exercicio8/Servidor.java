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

        String salarioMedio = null;

        String linhaArq = null;

        try {
            saida = new PrintWriter(this.socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));

            salarioMedio = entrada.readLine();

            double sm = Double.parseDouble(salarioMedio);

            if(sm >= 0 && sm <= 200) saida.println("Saldo medio: " + sm + "Crédito: " + "sem direito.");
            else if(sm >= 201 && sm <= 400) saida.println("Saldo medio: " + sm + "Crédito: " + sm * 0.2);
            else if(sm >= 401 && sm <= 600) saida.println("Saldo medio: " + sm + "Crédito: " + sm * 0.3);
            else if(sm >= 601) saida.println("Saldo medio: " + sm + "Crédito: " + sm * 0.4);


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
