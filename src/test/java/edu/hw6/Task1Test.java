package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @TempDir
    Path tempDir;

    String getPath() {
        Path path = tempDir.resolve("test.txt");
        return path.toAbsolutePath().toString();
    }

    void createFile() throws IOException {
        var path = getPath();
        try (var br = new BufferedWriter(new FileWriter(path))) {
            br.write("key1:value1");
        }
    }

    @Test
    @DisplayName("Загрузка из файла")
    void loadDataTest() throws Exception {
        createFile();
        var path = getPath();
        var diskMap = new DiskMap();
        diskMap.loadDataFile(path);
        assertThat(diskMap.get("key1")).isEqualTo("value1");
    }

    @Test
    @DisplayName("Сохранение в файл")
    void saveDataTest() throws Exception {
        var path = getPath();
        var diskMap = new DiskMap();
        diskMap.put("key2", "value2");
        assertThat(diskMap.get("key2")).isEqualTo("value2");
        diskMap.saveDataInFile(path);
        var file = new File(path);
        assertThat(file.exists()).isEqualTo(true);
        diskMap.loadDataFile(path);
        assertThat(diskMap.get("key2")).isEqualTo("value2");
    }
}
