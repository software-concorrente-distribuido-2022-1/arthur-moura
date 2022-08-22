package lab2.exercicio2_1;

class Produtor implements Runnable {

    private DepositoA dep;
    private int tempo;

    public Produtor(DepositoA d, int t) {
        this.dep = d;
        this.tempo = t;
    }

    public void run() {
        while (true) {
            dep.colocar();
            try {
                Thread.sleep(tempo * 1000);
            } catch (InterruptedException e) {

            }
        }
    }
};

class Consumidor implements Runnable {

    private DepositoA dep;
    private int tempo;

    public Consumidor(DepositoA d, int t) {
        this.dep = d;
        this.tempo = t;
    }

    public void run() {
        while (true) {
            if (dep.items < 1) {
                System.out.println("A espera de caixas");  // Mensagem de bloqueio do consumidor
            } else {
                dep.retirar();
            }
            try {
                Thread.sleep(tempo * 1000);
            } catch (InterruptedException e) {

            }
        }
    }
};

public class DepositoB {
    public int items = 0;
    public final int capacidade = 10;

    public int retirar() {
        if (items > 0) {
            items--;
            System.out.println("Caixa retirada: Sobram " + items + " caixas");
            return 1;
        }
        return 0;
    }

    public int colocar() {
        if (items < capacidade) {
            items++;
            System.out.println("Caixa armazenada: Passaram a ser " + items + " caixas");
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        DepositoA dep = new DepositoA();
        Produtor p = new Produtor(dep, 1);        // Valores de tempo são invertidos
        Consumidor c = new Consumidor(dep, 2);    //

        Thread p1 = new Thread(p);
        Thread c1 = new Thread(c);

        p1.start();
        c1.start();

        System.out.println("Execução do main da classe Deposito terminada!");
    }
}