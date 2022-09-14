import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Impl extends UnicastRemoteObject implements MyInterface {

   double pesoIdeal;

    protected Impl() throws RemoteException {
        super();
    }

    @Override
    public double pesoIdeal(String sexo, double altura) throws RemoteException {
        if (sexo.equals("masculino")) {
            pesoIdeal = (72.7 * altura) - 58;
            return pesoIdeal;
        }
        else if (sexo.equals("feminino")) {
            pesoIdeal = (62.1 * altura) - 44.7;
            return pesoIdeal;

        }
        return 0;
    } 
}
