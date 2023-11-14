package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CloneFileTest {

    @TempDir
    Path tempDir;

    Path getPath() {
        return tempDir.resolve("test.txt");
    }

    void createFile() throws IOException {
        Files.createFile(getPath());
    }

    @Test
    @DisplayName("Одноразовое клонирование")
    void oneCloneFileTest() throws Exception {
        createFile();
        var path = getPath();
        var file = Howework6.cloneFile(path);
        assertThat(file.getName()).isEqualTo("test - копия.txt");
    }

    @Test
    @DisplayName("Множественное клонирование")
    void manyCloneFileTest() throws Exception {
        createFile();
        var path = getPath();
        var file = Howework6.cloneFile(path);
        assertThat(file.getName()).isEqualTo("test - копия.txt");
        file = Howework6.cloneFile(path);
        assertThat(file.getName()).isEqualTo("test - копия (2).txt");
        file = Howework6.cloneFile(path);
        assertThat(file.getName()).isEqualTo("test - копия (3).txt");
    }
}
