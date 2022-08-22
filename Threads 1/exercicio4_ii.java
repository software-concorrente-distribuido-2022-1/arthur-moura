public class ThreadSimples {
    static void mensagem(String messagem) {                              // Método void que recebe uma string (mensagem), pega o nome de uma thread, concatena ambas e imprime. 
        String nomeThread = Thread.currentThread().getName();            // Pega o nome da thread
        System.out.println(nomeThread + " " + messagem);                 // Imprime
    }
    private static class Loop implements Runnable {                      // Essa classe implementa o método "run" para execução de threads
        public void run() {                                              // Método run
            String info[] = {                                            // Array de strings
                "Java",
                "é uma boa linguagem.",
                "Com threads,",
                "é melhor ainda."
            };
            try {
                for (int i = 0; i < info.length; i++) {                  // Nesse loop, o método "mensagem" é chamado para imprimir todas as strings presentes no array info[]                                       
                    Thread.sleep(4000);                                  // Intervalo de 4 segundos entre cada impressão
                    mensagem(info[i]);                                   // Método chamado
                }
            } catch (InterruptedException e) {                           // Caso ocorra interrupção na Thread enquanto ela está dormindo, a exceção é tratada aqui, imprimindo-a
                mensagem("Nada feito!");
            }
        }
    }
    public static void main(String args[]) throws InterruptedException { // Método main main
        long paciencia = 1000 * 60 * 60;                                 // Variável long "paciência" é atribuída o valor 3600000       
        if (args.length > 0) {                                           // Condição para saber se args existe
            try {
                paciencia = Long.parseLong(args[0]) * 1000;              // Converte a string na posição 0 do array args em um long e multiplica por 1000, atribuindo o valor na variável paciência
            } catch (NumberFormatException e) {                          // Tratamento para exceção de tipo numérico
                System.err.println("Argumento deve ser um inteiro.");    // Exceção caso o argumento não seja inteiro
                System.exit(1);                                          // Para o programa   
            }
        }
        mensagem("Iniciando a thread Loop");                             // Método mensagem recebe uma string, que é impressa
        long inicio = System.currentTimeMillis();                        // Variável "inicio", que é um long, é atribuída para o valor atual de milissegundos do sistema
        Thread t = new Thread(new Loop());                               // Instancia a Thread, passando como construtor a classe Loop
        t.start();                                                       // Inicia a Thread
        mensagem("Esperando que a thread Loop termine");                 // Método mensagem recebe uma string, que é impressa
        while (t.isAlive()) {                                            // Loop para verificar se a Thread está viva
            mensagem("Ainda esperando...");                              // Método mensagem recebe uma string, que é impressa
            t.join(1000);                                                // Método join() é chamado, colocando a Thread em espera por 1s
            if (((System.currentTimeMillis() - inicio) > paciencia) &&   // Condição que verifica se o tempo atual em milissegundos menos a variável "início" é maior que a variável "paciência". Também verifica se a Thread está viva
            t.isAlive()) {
                mensagem("Cansado de esperar!");                         // Método mensagem recebe uma string, que é impressa
                t.interrupt();                                           // Interrompe a Thread
                t.join();                                                // Coloca a thread em espera
            }
        }
        mensagem("Finalmente!");                                         // Após todas as execuções, método mensagem recebe a última string, que é impressa
    }
}