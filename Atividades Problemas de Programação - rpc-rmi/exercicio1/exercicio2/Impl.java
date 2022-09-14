import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Impl extends UnicastRemoteObject implements MyInterface {

    double slarioReajustado;

    protected Impl() throws RemoteException {
        super();
    }

    @Override
    public void maiorIdade(String sexo, int idade) throws RemoteException {
        if (sexo.equals("masculino") && idade >= 18) {
            System.out.println("Maior de idade.");
        } else if (sexo.equals("feminino") && idade >= 21) {
            System.out.println("Maior idade.");
        } else {
            System.out.println("Menor idade.");

        }
    }
}
