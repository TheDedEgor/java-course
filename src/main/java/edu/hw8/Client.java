package edu.hw8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings({"RegexpSinglelineJava", "UncommentedMain"})
public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;

    private Client() {
    }

    public static void main(String[] args) {
        try (var clientSocket = new Socket(SERVER_HOST, SERVER_PORT)) {
            try (var scanner = new Scanner(System.in);
                 var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 var out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
            ) {
                var word = scanner.nextLine();
                out.write(word + "\n");
                out.flush();

                var serverResponse = in.readLine();
                System.out.println(serverResponse);
            }
        } catch (Exception ignored) { }
    }
}
