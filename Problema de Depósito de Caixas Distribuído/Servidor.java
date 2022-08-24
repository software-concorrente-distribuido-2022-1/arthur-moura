import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("A porta 8080 esta aberta!");
        System.out.println("Servidor esperando mensagem dos clientes...");

        ItemsList itemsList = new ItemsList();

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + " conectado.");

            ThreadSockets thread = new ThreadSockets(socket, itemsList);
            thread.start();
        }
    }
}
