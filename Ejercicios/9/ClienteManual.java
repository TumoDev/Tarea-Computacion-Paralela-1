import java.io.*;
import java.net.*;

public class ClienteManual {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String linea;
        while ((linea = in.readLine()) != null) {
            System.out.println(linea);
        }
        socket.close();
    }
}
