import java.io.*;
import java.net.*;
import java.util.Random;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server of Stella Barraza is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientMessage = in.readLine();
            String[] parts = clientMessage.split(":");
            String clientName = parts[0];
            int clientNumber = Integer.parseInt(parts[1]);

            System.out.println(clientName + " connected to Server of Stella Barraza");

            Random rand = new Random();
            int serverNumber = rand.nextInt(100) + 1;
            int sum = clientNumber + serverNumber;

            String serverName = "Server of Stella Barraza";
            String serverResponse = serverName + ":" + serverNumber + ":" + sum;

            out.println(serverResponse);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
