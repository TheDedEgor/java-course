package edu.hw10;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CacheProxyTest {

    @Test
    void cacheProxyTest1() {
        FibCalculator c = new Fib();
        FibCalculator proxy = CacheProxy.create(c, FibCalculator.class);
        var result = proxy.fib(20);
        var path = System.getProperty("java.io.tmpdir") + "cache.txt";
        var file = new File(path);
        try(var bufferedReader = new BufferedReader(new FileReader(file))) {
            var value = bufferedReader.readLine().split(":")[1];
            assertThat(result).isEqualTo(Long.parseLong(value));
        } catch (Exception ex) { }
    }
}

class Fib implements FibCalculator {

    @Override
    public long fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n - 2) + fib(n - 1);
    }
}

interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
