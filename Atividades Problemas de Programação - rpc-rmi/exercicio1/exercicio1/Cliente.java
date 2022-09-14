import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {
    public static void main(String[] args) {

        String nome = "Arthur";
        String cargo = "programador";
        double salario= 5000;

        try {
            MyInterface stub = (MyInterface) Naming.lookup("rmi://localhost:8080/MyInterface");

            if(cargo.equals("programador")) {
                System.out.println("Nome do funcionario: " + nome);
                System.out.println("Salario reajustado: " + stub.reajusteSalarioProgramador(salario));
            }
            else if (cargo.equals("operador")) {
                System.out.println("Nome do funcionario: " + nome);
                System.out.println("Salario reajustado: " + stub.reajusteSalarioOperador(salario));
            }

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
