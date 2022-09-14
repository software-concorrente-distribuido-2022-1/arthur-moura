import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Impl extends UnicastRemoteObject implements MyInterface {

   String mensagem;

    protected Impl() throws RemoteException {
        super();
    }

    @Override
    public String possoAposentar(Integer idade, Integer tempoDeTrabalho) throws RemoteException {
        if (idade >= 65 || (tempoDeTrabalho >= 30) || (idade >= 60 && (tempoDeTrabalho >= 25))) {
            mensagem  = "Apto para se aposentar";
            return mensagem;
        }
        else {
            mensagem = "Nao esta apto para aposentar";
            return mensagem;
        }
    }   
}
