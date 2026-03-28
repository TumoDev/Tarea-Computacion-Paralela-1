public class Deadlock {

    static final Object grua = new Object();
    static final Object escaner = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== CON DEADLOCK ===");

        Thread op1 = new Thread(() -> {
            synchronized (grua) {
                System.out.println("Operario1 tiene Grua, espera Escaner...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (escaner) {
                    System.out.println("Operario1 tiene ambos");
                }
            }
        });

        Thread op2 = new Thread(() -> {
            synchronized (escaner) {
                System.out.println("Operario2 tiene Escaner, espera Grua...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (grua) {
                    System.out.println("Operario2 tiene ambos");
                }
            }
        });

        op1.start(); op2.start();
        op1.join(3000); op2.join(3000);

        if (op1.isAlive() || op2.isAlive()) {
            System.out.println("DEADLOCK DETECTADO!");
        }

        System.out.println("\n=== SIN DEADLOCK (mismo orden) ===");

        Object grua2 = new Object();
        Object escaner2 = new Object();

        Thread op3 = new Thread(() -> {
            synchronized (grua2) {
                System.out.println("Operario1 tiene Grua, espera Escaner...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (escaner2) {
                    System.out.println("Operario1 tiene ambos");
                }
            }
        });

        Thread op4 = new Thread(() -> {
            synchronized (grua2) {
                System.out.println("Operario2 tiene Grua, espera Escaner...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (escaner2) {
                    System.out.println("Operario2 tiene ambos");
                }
            }
        });

        op3.start(); op4.start();
        op3.join(); op4.join();
        System.out.println("Terminaron sin deadlock!");
    }
}
