import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Impl extends UnicastRemoteObject implements MyInterface {

   String categoria;

    protected Impl() throws RemoteException {
        super();
    }

    @Override
    public String classificacoIdade(Integer idade) throws RemoteException {
        if (idade > 18) {
            categoria = "adulto";
            return categoria;
        }
        else if ((idade < 18) && idade >= 14) {
            categoria = "juvenil B";
            return categoria;

        }
        else if ((idade < 14) && idade >= 11) {
            categoria = "juvenil A";
            return categoria;

        }
        else if ((idade < 11) && idade >= 8) {
            categoria = "infantil B";
            return categoria;

        }
        else if ((idade < 8) && idade >= 5) {
            categoria = "infantil A";
            return categoria;

        }
        else {
            return null;
        }
    }
}
