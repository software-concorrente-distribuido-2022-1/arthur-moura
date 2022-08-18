package exercicio1;


import java.io.*; //Package de classes para manipulacao de E/S
import java.net.*; //Package de classes para manipulacao de Sockets, IP, etc

public class Cliente {
    public static void main(String[] args) throws IOException {

        /* ---declaracao dos objetos utilizados--- */

        final int portaDefault = 80; // Definicao da porta default

        String nomeHost = null; // Nome do host para conexao

        String nome = null;
        String cargo = null;
        String salario = null;

        int porta = portaDefault; // Porta para conexao

        Socket sock = null; // Declaracao de objeto da classe Socket

        PrintWriter saida = null; // Fluxo de saida
        BufferedReader entrada = null;// Fluxo de entrada
        String linhaResposta = null; // Linha de resposta do host

        /* ---tratamento dos argumentos--- */

        if ((args.length == 4) || (args.length == 5)) {
            nomeHost = args[0]; // Host e' 1o. argumento
            nome = args[1];
            cargo = args[2];
            salario = args[3];
            if (args.length == 5) {
                porta = Integer.parseInt(args[4]);
            }
            // Porta fornecida como argumento sobrepoe porta default
        } else { // Fornecimento erroneo dos argumentos
            System.out.println("\n\nUso Correto: Cliente NomeDoHost Nome Cargo Salario [porta]\n\n");
            System.exit(1);
        }

        try {
            sock = new Socket(nomeHost, porta);
            // Objeto sock criado atraves do construtor Socket
            // adequado a uma conexao TCP confiavel (stream).
            // Corresponde as instrucoes socket() e connect()

            saida = new PrintWriter(sock.getOutputStream(), true);          
            // Prepara saida para envio posterior da PDU

            entrada = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            // Prepara entrada para recepcao de mensagens do host
        } catch (UnknownHostException e) {
            System.err.println("\n\nHost nao encontrado!\n");
            System.out.println("\nUso: Cliente NomeDoHost mensagem [porta]\n\n");
            System.exit(1);
        } catch (java.io.IOException e) {
            System.err.println("\n\nConexao com Host nao pode ser estabelecida.\n");
            System.out.println("\nUso: Cliente NomeDoHost mensagem [porta]\n\n");
            System.exit(1);
        }

        /* ---montagem e envio da Mensagem --- */

        saida.println(nome);
        saida.println(cargo);
        saida.println(salario);

        /* --- recepcao das mensagens do host --- */

        System.out.println("\nResposta do Host:\n");
        linhaResposta = entrada.readLine();
        while (linhaResposta != null) {
            System.out.println(linhaResposta);
            linhaResposta = entrada.readLine();
        }

        /* ---fechamento do socket --- */

        sock.close();
    }
}
