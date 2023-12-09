package edu.hw9;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParallelFileProcessorTest {

    @TempDir
    static Path tempDir;

    @BeforeAll
    static void createFiles() throws IOException {
        for (int i = 1; i <= 5; i++) {
            var path = tempDir.resolve("test_"+ i +".txt");
            Files.createFile(path);
        }
    }

    @Test
    @DisplayName("Мин кол-во файлов в директории 5")
    void minCountFilesInDirectoryTest() {
        var processor = new ParallelFileProcessor(tempDir.toAbsolutePath().toString());
        var result = processor.algorithm1(5);
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Поиск файлов .txt")
    void searchFilesTest() {
        var processor = new ParallelFileProcessor(tempDir.toAbsolutePath().toString());
        var result = processor.algorithm2(".txt",0);
        assertThat(result.size()).isEqualTo(5);
    }
}
