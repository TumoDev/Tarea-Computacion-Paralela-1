public class StartVsRun {

    static class Tarea implements Runnable {
        String nombre;

        Tarea(String nombre) {
            this.nombre = nombre;
        }

        public void run() {
            System.out.println(nombre + " ejecutada por: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Usando run() ===");
        new Thread(new Tarea("Tarea1")).run();
        new Thread(new Tarea("Tarea2")).run();
        new Thread(new Tarea("Tarea3")).run();

        System.out.println("=== Usando start() ===");
        new Thread(new Tarea("Tarea4")).start();
        new Thread(new Tarea("Tarea5")).start();
        new Thread(new Tarea("Tarea6")).start();
    }
}
