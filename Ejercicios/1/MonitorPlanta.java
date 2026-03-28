import java.util.Random;

public class MonitorPlanta {

    static class Sensor implements Runnable {
        String nombre;

        Sensor(String nombre) {
            this.nombre = nombre;
        }

        public void run() {
            Random r = new Random();
            while (true) {
                double temp = r.nextDouble() * 150;
                System.out.println(nombre + " -> " + String.format("%.1f", temp) + " C");
                if (temp > 100) {
                    System.out.println(nombre + " SOBRECALENTAMIENTO! Sensor detenido.");
                    return;
                }
                try { Thread.sleep(500); } catch (InterruptedException e) { return; }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Sensor("Sensor-" + i)).start();
        }
    }
}
