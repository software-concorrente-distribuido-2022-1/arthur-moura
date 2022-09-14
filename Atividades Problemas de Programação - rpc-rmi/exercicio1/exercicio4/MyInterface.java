import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyInterface extends Remote {
   double pesoIdeal(String sexo, double altura) throws RemoteException;

   
}
