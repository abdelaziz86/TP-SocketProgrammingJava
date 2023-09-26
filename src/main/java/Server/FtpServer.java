package Server ;
import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FtpServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Server is running...");

        // Create a server socket
        ServerSocket serverSocket = new ServerSocket(5555);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from client.");

            String filePath = "C:\\Users\\Abdelaziz\\IdeaProjects\\tp_1_socket\\src\\main\\java\\Server\\myFile.txt" ;  // Construct the file path


            // Create a file output stream to save the received file
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            // Create an input stream from the client socket
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            // Read data from the client and write it to the file
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Received and saved the file: " + filePath);

            // Close the streams and the client socket
            fileOutputStream.close();
            inputStream.close();
            clientSocket.close();
        }
    }
}
