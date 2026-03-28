import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WhatsappCliente {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7001);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner teclado = new Scanner(System.in);

        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                }
            } catch (IOException e) {}
        }).start();

        System.out.println("Escribe mensajes:");
        while (teclado.hasNextLine()) {
            String msg = teclado.nextLine();
            if (msg.equals("salir")) break;
            out.println(msg);
        }
        socket.close();
    }
}
