package lab2.exercicio2_2;

class Produtor implements Runnable {

    private Deposito dep;
    private int tempo;

    public Produtor(Deposito d, int t) {
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

    private Deposito dep;
    private int tempo;

    public Consumidor(Deposito d, int t) {
        this.dep = d;
        this.tempo = t;
    }

    public void run() {
        while (true) {
            dep.retirar();
            try {
                Thread.sleep(tempo * 1000);
            } catch (InterruptedException e) {

            }
        }
    }
};

public class Deposito {
    private int items = 0;
    private final int capacidade = 10;

    public synchronized int retirar() {                                                          // Uso de sincronização
        if (items > 0) {
            items--;
            System.out.println("Caixa retirada: Sobram " + items + " caixas");
            return 1;
        }
        return 0;
    }

    public synchronized int colocar() {                                                          // Uso de sincronização
        if (items < capacidade) {
            items++;
            System.out.println("Caixa armazenada: Passaram a ser " + items + " caixas");
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Deposito dep = new Deposito();
        Produtor p = new Produtor(dep, 2);
        Consumidor c = new Consumidor(dep, 1);

        Thread p1 = new Thread(p);
        Thread c1 = new Thread(c);

        p1.start();
        c1.start();

        System.out.println("Execução do main da classe Deposito terminada!");
    }
}