package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

@SuppressWarnings("RegexpSinglelineJava")
public class Howework6 {

    private Howework6() {
    }

    private static final Map<Integer, String> PORTS =
        Map.of(135, "EPMAP", 143, "IMAP", 5353, "mDNS", 5672, "AMQP", 5432, "PostgreSQL Database");

    public static File cloneFile(Path path) throws Exception {
        var file = new File(path.toString());
        if (file.isDirectory() || !file.exists()) {
            throw new Exception("Это не файл или его не существует");
        }
        var pathAndExtensionFile = getPathAndExtensionFile(file);
        int k = 2;
        var newPath = Paths.get(pathAndExtensionFile[0] + " - копия" + pathAndExtensionFile[1]);
        while (!copyFile(file.toPath(), newPath)) {
            newPath = Paths.get(pathAndExtensionFile[0] + " - копия " + "(" + k + ")" + pathAndExtensionFile[1]);
            k++;
        }
        return newPath.toFile();
    }

    public static void task4(Path path) throws IOException {
        try (var outputStream = Files.newOutputStream(path);
             var checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
             var bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             var outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
             var printWriter = new PrintWriter(outputStreamWriter)) {
            printWriter.print("Programming is learned by writing programs. ― Brian Kernighan");
        }
    }

    public static void task6() throws IOException {
        final var format = "%-10s\t%-10s\t%-10s%n";
        final var tcp = "TCP";
        final var udp = "UDP";
        System.out.printf(format, "Протокол", "Порт", "Сервис");
        for (var port : PORTS.entrySet()) {
            try (var serverSocket = new ServerSocket(port.getKey())) {
                System.out.printf(format, tcp, port.getKey(), "");
            } catch (IOException ex) {
                System.out.printf(format, tcp, port.getKey(), port.getValue());
            }
            try (var datagramSocket = new DatagramSocket(port.getKey())) {
                System.out.printf(format, udp, port.getKey(), "");
            } catch (Exception ex) {
                System.out.printf(format, udp, port.getKey(), port.getValue());
            }
        }
    }

    private static Boolean copyFile(Path source, Path target) throws IOException {
        try {
            Files.copy(source, target);
            return true;
        } catch (FileAlreadyExistsException ex) {
            return false;
        }
    }

    private static String[] getPathAndExtensionFile(File file) {
        var absolutePath = file.getAbsolutePath();
        var idx = absolutePath.lastIndexOf(".");
        var result = new String[2];
        result[0] = absolutePath.substring(0, idx);
        result[1] = absolutePath.substring(idx);
        return result;
    }
}
