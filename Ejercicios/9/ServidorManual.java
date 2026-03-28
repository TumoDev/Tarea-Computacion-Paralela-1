import java.io.*;
import java.net.*;

public class ServidorManual {

    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("manual.txt"));
        pw.println("MANUAL DE PROCEDIMIENTOS");
        pw.println("Capitulo 1: Introduccion");
        pw.println("Capitulo 2: Seguridad");
        pw.println("Capitulo 3: Emergencias");
        pw.println("FIN DEL MANUAL");
        pw.close();

        ServerSocket servidor = new ServerSocket(6000);
        System.out.println("Servidor Manual en puerto 6000");

        while (true) {
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader lector = new BufferedReader(new FileReader("manual.txt"));

            String linea;
            while ((linea = lector.readLine()) != null) {
                out.println(linea);
            }
            lector.close();
            socket.close();
            System.out.println("Manual enviado");
        }
    }
}
