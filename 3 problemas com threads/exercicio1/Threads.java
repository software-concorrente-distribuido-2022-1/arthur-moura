package exercicio1;

public class Threads {
    public static void main(String args[]) {
        Thread t1 = new Thread(r1);
        t1.start();
        try {
            t1.join();
            System.out.println("Programa finalizado");
        } catch (InterruptedException e) {
        }
    }

    private static Runnable r1 = new Runnable() {
        public void run() {
            for (int i = 1; i <= 100; i++) {
                System.out.println(i);
            }
        }
    };
}