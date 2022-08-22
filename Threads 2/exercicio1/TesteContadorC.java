package lab2.exercicio1;

class Contador extends Thread {

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }
}

public class TesteContadorC {
    public static void main(String[] args) {
        Contador c1 = new Contador();
        Contador c2 = new Contador();
        Contador c3 = new Contador();

        c1.start();
        c2.start();
        c3.start();
    }
}
