package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Композиция стримов")
    void compositionStreamsTest() throws Exception {
        Path path = tempDir.resolve("test.txt");
        Howework6.task4(path);
        try (var br = new BufferedReader(new FileReader(path.toAbsolutePath().toString()))) {
            assertThat(br.readLine()).isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");
        }
    }
}
