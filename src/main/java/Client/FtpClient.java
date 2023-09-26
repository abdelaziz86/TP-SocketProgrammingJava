package Client;
import java.io.*;
import java.net.*;

public class FtpClient {
    public static void main(String argv[]) {
        String serverAddress = "localhost"; // Replace with the server's IP address
        int serverPort = 5555; // Server port to connect to
        String filePath = "C:\\Users\\Abdelaziz\\IdeaProjects\\tp_1_socket\\src\\main\\java\\Client\\myFile.txt"; // Specify the file path you want to send

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File does not exist: " + filePath);
            // Handle the error or return
        }

        try {
            Socket socketClient = new Socket("localhost", 5555);
            System.out.println("Client: " + "Connection Established");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            String serverMsg;

            // Read the content of myFile.txt and send it to the server
            BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = fileReader.readLine()) != null) {
                writer.write(line + "\r\n"); // Send each line to the server
            }
            fileReader.close();

            writer.flush();
            while ((serverMsg = reader.readLine()) != null) {
                System.out.println("Client: " + serverMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
