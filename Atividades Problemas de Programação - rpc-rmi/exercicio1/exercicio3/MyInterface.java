import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyInterface extends Remote {
   void aprovado(double n1, double n2, double n3) throws RemoteException;

   
}
