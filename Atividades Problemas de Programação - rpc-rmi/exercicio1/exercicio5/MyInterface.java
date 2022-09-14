import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyInterface extends Remote {
   String classificacoIdade(Integer idade) throws RemoteException;

   
}
