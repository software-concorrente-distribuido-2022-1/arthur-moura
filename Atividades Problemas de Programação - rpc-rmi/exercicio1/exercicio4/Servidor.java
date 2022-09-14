import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(8080);
            Naming.rebind("rmi://localhost:8080/MyInterface", new Impl());

            System.out.println("Servidor online. Aguardando conexoes...");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
