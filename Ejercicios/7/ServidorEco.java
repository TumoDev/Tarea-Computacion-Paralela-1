import java.io.*;
import java.net.*;

public class ServidorEco {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(6000);
        System.out.println("Servidor Eco en puerto 6000");

        while (true) {
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado");

            new Thread(() -> {
                try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ) {
                    String linea;
                    while ((linea = in.readLine()) != null) {
                        String invertida = new StringBuilder(linea).reverse().toString();
                        out.println(invertida);
                        System.out.println("Recibido: " + linea + " -> Enviado: " + invertida);
                    }
                } catch (IOException e) {
                    System.out.println("Cliente desconectado");
                }
            }).start();
        }
    }
}
