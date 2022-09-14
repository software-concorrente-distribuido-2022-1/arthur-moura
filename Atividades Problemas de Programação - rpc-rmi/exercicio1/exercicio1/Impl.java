import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Impl extends UnicastRemoteObject implements MyInterface{ 

    double slarioReajustado;
    
    protected Impl() throws RemoteException {
        super();
    }

    @Override
    public double reajusteSalarioOperador(double salario) throws RemoteException {
        slarioReajustado = salario * 1.2;
        return slarioReajustado;
    }

    @Override
    public double reajusteSalarioProgramador(double salario) throws RemoteException {
        slarioReajustado = salario * 1.18;
        return slarioReajustado;
    }
    
}
