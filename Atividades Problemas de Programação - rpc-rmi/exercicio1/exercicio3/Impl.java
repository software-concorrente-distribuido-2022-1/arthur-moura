import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Impl extends UnicastRemoteObject implements MyInterface {

    double mediaM;
    double outraMedia;

    protected Impl() throws RemoteException {
        super();
    }

    @Override
    public void aprovado(double n1, double n2, double n3) throws RemoteException {
        mediaM = (n1 + n2) / 2;
        if (mediaM >= 7) {
            System.out.println("Aprovado");
        }
         else if ((mediaM > 3) && mediaM < 7) {
            outraMedia = (mediaM + n3) / 2;
            if (outraMedia >= 5) {
                System.out.println("Aprovado");
            } else {
                System.out.println("Reprovado");;
            }
         }
         else {
            System.out.println("Repovado");
         }        
    }
}
