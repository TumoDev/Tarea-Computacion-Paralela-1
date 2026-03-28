import java.io.*;
import java.net.*;

public class ServidorLogin {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(9000);
        System.out.println("Servidor Login en puerto 9000");

        while (true) {
            Socket socket = servidor.accept();

            new Thread(() -> {
                try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ) {
                    String credenciales = in.readLine();
                    System.out.println("Recibido: " + credenciales);

                    if (credenciales != null && credenciales.equals("admin:1234")) {
                        out.println("OK");
                        System.out.println("Login exitoso");
                    } else {
                        out.println("ERROR");
                        System.out.println("Login fallido");
                    }
                } catch (IOException e) {
                    System.out.println("Cliente perdio conexion");
                }
            }).start();
        }
    }
}
