class ThreadSimples extends Thread {
    public void run() {
        String info[] = {
        "Java",
        "é uma boa linguagem.",
        "Com threads",
        "é melhor ainda."
        };
    
        for (int i = 0; i < info.length; i++) {
            System.out.println(info[i]);
        }
    }
}

public class ExecutaThread{
    
    public static void getThreadName(Thread t){
       System.out.println(t.getName());
    }
    
    public static void main(String args[]) {
        ThreadSimples simples = new ThreadSimples();
        simples.start();
        try {
            simples.sleep(4000);
        } catch(InterruptedException e){
            
        }
        
        getThreadName(simples);
    }
}

