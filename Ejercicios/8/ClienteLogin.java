import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteLogin {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese usuario:password > ");
        out.println(teclado.nextLine());
        System.out.println("Respuesta: " + in.readLine());
        socket.close();
    }
}
