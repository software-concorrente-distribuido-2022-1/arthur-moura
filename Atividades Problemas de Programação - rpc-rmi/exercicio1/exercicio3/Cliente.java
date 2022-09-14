import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {
    public static void main(String[] args) {

        double n1 = 6;
        double n2 = 2;
        double n3 = 5;

        try {
            MyInterface stub = (MyInterface) Naming.lookup("rmi://localhost:8080/MyInterface");
            stub.aprovado(n1, n2, n3);          

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
