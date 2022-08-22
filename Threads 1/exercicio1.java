class ThreadSimples implements Runnable {
    public void run() {
        System.out.println("Olá de uma thread!");
    }
}

public class ExecutaThread{
    public static void main(String args[]) {
        ThreadSimples simples = new ThreadSimples();
        Thread thread = new Thread(simples);
        thread.start();
    }
}