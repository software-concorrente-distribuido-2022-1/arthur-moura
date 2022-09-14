import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyInterface extends Remote {
   String possoAposentar(Integer idade, Integer tempoDeTrabalho) throws RemoteException;

   
}
