import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {
    public static void main(String[] args) {

        String nome = "Arthur";
        String sexo = "feminino";
        int idade = 21;

        try {
            MyInterface stub = (MyInterface) Naming.lookup("rmi://localhost:8080/MyInterface");
            stub.maiorIdade(sexo, idade);          

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
