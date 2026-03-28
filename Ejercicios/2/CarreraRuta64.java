public class CarreraRuta64 {

    static class Vehiculo implements Runnable {
        String nombre;

        Vehiculo(String nombre) {
            this.nombre = nombre;
        }

        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(nombre + " avanzo al tramo " + i);
                try { Thread.sleep((int)(Math.random() * 500)); } catch (InterruptedException e) {}
            }
            System.out.println(nombre + " llego a Viña del Mar!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread auto1 = new Thread(new Vehiculo("Auto1"));
        Thread auto2 = new Thread(new Vehiculo("Auto2"));
        Thread auto3 = new Thread(new Vehiculo("Auto3"));

        System.out.println("Carrera Iniciada!");

        auto1.start();
        auto2.start();
        auto3.start();

        auto1.join();
        auto2.join();
        auto3.join();

        System.out.println("Todos llegaron a la meta!");
    }
}
