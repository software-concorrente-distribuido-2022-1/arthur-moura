package exercicio2;

public class Threads {
    public static void main(String args[]) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Banana");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Limão");
            }
        });

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Melão");
            }
        });
        
        Thread t4 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Abacaxi");
            }
        });

        Thread t5 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Uva");
            }
        });
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}