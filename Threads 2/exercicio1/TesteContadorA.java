package lab2.exercicio1;

class Contador extends Thread {

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }
}

public class TesteContadorA {
    public static void main(String[] args) {
        Contador c1 = new Contador();

        c1.start();
    }
}
