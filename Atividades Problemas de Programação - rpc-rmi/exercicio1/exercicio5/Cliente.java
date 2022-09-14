import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {
    public static void main(String[] args) {

        Integer idade = 17;       

        try {
            MyInterface stub = (MyInterface) Naming.lookup("rmi://localhost:8080/MyInterface");
            System.out.println(stub.classificacoIdade(idade));
                    

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
