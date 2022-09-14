import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {
    public static void main(String[] args) {

        double altura = 1.73;
        String sexo = "masculino";
       

        try {
            MyInterface stub = (MyInterface) Naming.lookup("rmi://localhost:8080/MyInterface");
            System.out.println(stub.pesoIdeal(sexo, altura));
                    

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
