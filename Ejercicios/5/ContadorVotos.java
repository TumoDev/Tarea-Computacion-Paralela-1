public class ContadorVotos {

    static int votosInseguros = 0;
    static int votosSeguro = 0;
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== SIN sincronizacion ===");
        Thread[] hilos1 = new Thread[100];
        for (int i = 0; i < 100; i++) {
            hilos1[i] = new Thread(() -> { for (int j = 0; j < 1000; j++) votosInseguros++; });
            hilos1[i].start();
        }
        for (int i = 0; i < 100; i++) hilos1[i].join();
        System.out.println("Esperados: 100000 -> Contados: " + votosInseguros);

        System.out.println("\n=== CON synchronized ===");
        Thread[] hilos2 = new Thread[100];
        for (int i = 0; i < 100; i++) {
            hilos2[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) { synchronized (lock) { votosSeguro++; } }
            });
            hilos2[i].start();
        }
        for (int i = 0; i < 100; i++) hilos2[i].join();
        System.out.println("Esperados: 100000 -> Contados: " + votosSeguro);
    }
}
