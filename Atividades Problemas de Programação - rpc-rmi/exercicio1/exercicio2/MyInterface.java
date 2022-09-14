import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyInterface extends Remote {
   void maiorIdade(String sexo, int idade) throws RemoteException;   
}
