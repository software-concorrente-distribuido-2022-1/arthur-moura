package lab2.exercicio1;

class Contador implements Runnable {

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }
}

public class TesteContadorB {
    public static void main(String[] args) {
        Contador c1 = new Contador();
        Thread t1 = new Thread(c1);

        t1.start();
    }
}
