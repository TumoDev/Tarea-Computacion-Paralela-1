import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class WhatsappServer {

    private static List<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(7001);
        System.out.println("Servidor de grupo iniciado...");

        while (true) {
            Socket socket = servidor.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            clientes.add(out);
            System.out.println("Nuevo miembro. Total: " + clientes.size());

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println("Mensaje: " + msg);
                        for (PrintWriter c : clientes) {
                            if (c != out) c.println(msg);
                        }
                    }
                } catch (IOException e) {
                    clientes.remove(out);
                    System.out.println("Miembro desconectado. Total: " + clientes.size());
                }
            }).start();
        }
    }
}
