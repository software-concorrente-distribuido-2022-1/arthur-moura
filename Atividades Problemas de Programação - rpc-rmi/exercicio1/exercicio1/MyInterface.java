import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyInterface extends Remote {
    double reajusteSalarioOperador(double salario) throws RemoteException;
    double reajusteSalarioProgramador(double salario) throws RemoteException;    
}
