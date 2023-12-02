package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @TempDir
    Path tempDir;

    void createFiles() throws IOException {
        var nameFiles = List.of("test-1.png", "test2.txt", "test3.png");
        for (int i = 0; i < 3; i++) {
            Files.createFile(tempDir.resolve(nameFiles.get(i)));
        }
    }

    @Test
    @DisplayName("Проверка файлов")
    void checkFilesTest() throws Exception {
        createFiles();
        DirectoryStream.Filter<Path> filter = AbstractFilter.READABLE
            .and(AbstractFilter.largerThan(-10))
            .and(AbstractFilter.globMatches("*.png"))
            .and(AbstractFilter.regexContains("[-]"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(tempDir, filter)) {
            for (var path : entries) {
                assertThat(path.getFileName().toString()).isEqualTo("test-1.png");
            }
        }
    }
}
