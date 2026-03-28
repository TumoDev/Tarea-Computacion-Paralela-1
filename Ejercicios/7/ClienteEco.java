import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteEco {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner teclado = new Scanner(System.in);

        System.out.println("Conectado. Escribe algo (salir para terminar):");
        while (true) {
            String msg = teclado.nextLine();
            if (msg.equals("salir")) break;
            out.println(msg);
            System.out.println("Eco: " + in.readLine());
        }
        socket.close();
    }
}
