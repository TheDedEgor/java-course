package edu.hw8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

@SuppressWarnings({"RegexpSinglelineJava", "UncommentedMain"})
public class Server {
    private static final int PORT = 8080;
    private static final int MAX_CONNECTIONS = 5;
    private static final Dictionary DICTIONARY = new Dictionary();

    private Server() {
    }

    public static void main(String[] args) {
        try (var executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
             var server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен! Порт: " + PORT);
            while (true) {
                var clientSocket = server.accept();
                executorService.submit(() -> handleClient(clientSocket));
            }
        } catch (Exception ignored) { }
    }

    private static void handleClient(Socket clientSocket) {
        try (var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             var out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            String request = in.readLine();
            var quote = DICTIONARY.getQuote(request);
            if (quote == null) {
                out.write("Цитат по ключевому слову не найдено.");
            } else {
                out.write(quote);
            }
            out.flush();
        } catch (Exception ignored) { }
    }
}
