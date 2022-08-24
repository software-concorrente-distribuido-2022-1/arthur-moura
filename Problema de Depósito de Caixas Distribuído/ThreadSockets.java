import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadSockets extends Thread {
    private Socket socket;
    private ItemsList itemsList;

    public ThreadSockets(Socket s, ItemsList il) {
        this.socket = s;
        this.itemsList = il;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName());

        try {
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String comando = entrada.readUTF();

            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            String resposta;

            synchronized (this) {

                if (comando.equals("consumir")) {
                    itemsList.ocupado = true;

                    if (itemsList.qtd_items <= 0) {
                        resposta = "Nao existem itens para serem consumidos.";
                        saida.writeUTF(resposta);
                        itemsList.ocupado = false;
                        notifyAll();

                    } else {
                        itemsList.removeItem();
                        resposta = "Item consumido. Itens restantes: " + itemsList.qtd_items + ".";
                        saida.writeUTF(resposta);
                        itemsList.ocupado = false;
                        notifyAll();
                    }
                }

                else if (comando.equals("produzir")) {
                    itemsList.ocupado = true;

                    if (itemsList.qtd_items >= 10) {
                        resposta = "Nao ha espaço para produzir novos itens.";
                        saida.writeUTF(resposta);
                        itemsList.ocupado = false;
                        notifyAll();

                    } else {
                        itemsList.addItem();
                        resposta = "Item produzido. Itens restantes: " + itemsList.qtd_items + ".";
                        saida.writeUTF(resposta);
                        itemsList.ocupado = false;
                        notifyAll();
                    }
                }

                while (itemsList.ocupado = true) {
                    try {
                        wait();
                    } catch (Exception e) {
                    }
                }
            }

            entrada.close();
            saida.close();

            socket.close();

        } catch (IOException ioe) {
            System.out.println("Erro: " + ioe.toString());
        }
    }
}
