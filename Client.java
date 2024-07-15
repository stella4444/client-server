import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer between 1 and 100: ");
        int number = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        String clientName = "Client of Stella Barraza";

        try {
            Socket socket = new Socket("localhost", 12345);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String clientMessage = clientName + ":" + number;
            out.println(clientMessage);

            String serverMessage = in.readLine();
            String[] parts = serverMessage.split(":");
            String serverName = parts[0];
            int serverNumber = Integer.parseInt(parts[1]);
            int sum = Integer.parseInt(parts[2]);

            System.out.println("Received from " + serverName);
            System.out.println("Server number: " + serverNumber);
            System.out.println("Sum: " + sum);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
