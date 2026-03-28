public class CuentaCompartida {

    static int saldoInseguro = 500;
    static int saldoSeguro = 500;
    static final Object lock = new Object();

    static void retirarInseguro(String habitante, int monto) {
        if (saldoInseguro >= monto) {
            System.out.println(habitante + " ve saldo $" + saldoInseguro + " y va a retirar $" + monto);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            saldoInseguro -= monto;
            System.out.println(habitante + " retiro $" + monto + " -> Saldo: $" + saldoInseguro);
        } else {
            System.out.println(habitante + " rechazado, saldo insuficiente: $" + saldoInseguro);
        }
    }

    static void retirarSeguro(String habitante, int monto) {
        synchronized (lock) {
            if (saldoSeguro >= monto) {
                System.out.println(habitante + " ve saldo $" + saldoSeguro + " y va a retirar $" + monto);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                saldoSeguro -= monto;
                System.out.println(habitante + " retiro $" + monto + " -> Saldo: $" + saldoSeguro);
            } else {
                System.out.println(habitante + " rechazado, saldo insuficiente: $" + saldoSeguro);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== SIN synchronized ===");
        System.out.println("Saldo inicial: $500 | Arriendo: $400 cada uno\n");

        Thread h1 = new Thread(() -> retirarInseguro("Habitante1", 400));
        Thread h2 = new Thread(() -> retirarInseguro("Habitante2", 400));

        h1.start(); h2.start();
        h1.join(); h2.join();
        System.out.println("Saldo final: $" + saldoInseguro);

        System.out.println("=== CON synchronized ===");
        System.out.println("Saldo inicial: $500 | Arriendo: $400 cada uno\n");

        Thread h3 = new Thread(() -> retirarSeguro("Habitante1", 400));
        Thread h4 = new Thread(() -> retirarSeguro("Habitante2", 400));

        h3.start(); h4.start();
        h3.join(); h4.join();
        System.out.println("Saldo final: $" + saldoSeguro);
    }
}
