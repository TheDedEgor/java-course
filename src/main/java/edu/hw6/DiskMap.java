package edu.hw6;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DiskMap implements Map<String, String> {

    private final HashMap<String, String> data = new HashMap<>();

    public void loadDataFile(String path) throws Exception {
        try (var br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                var nums = line.split(":");
                data.put(nums[0], nums[1]);
            }
            System.out.println(data);
        }
    }

    public void saveDataInFile(String path) throws Exception {
        try (var br = new BufferedWriter(new FileWriter(path))) {
            for (var item: data.entrySet()) {
                br.write(item.getKey() + ":" + item.getValue() + "\n");
            }
        }
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return data.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return data.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return data.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return data.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        data.putAll(m);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return data.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return data.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return data.entrySet();
    }
}
