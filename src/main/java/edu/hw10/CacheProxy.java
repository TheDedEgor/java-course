package edu.hw10;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheProxy implements InvocationHandler {

    private final Object target;
    private final Map<String, Object> cache;

    private CacheProxy(Object target) {
        this.target = target;
        this.cache = new ConcurrentHashMap<>();
    }

    public static <T> T create(Object target, Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[]{interfaceClass},
            new CacheProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            Cache cacheAnnotation = method.getAnnotation(Cache.class);
            String key = generateCacheKey(method, args);

            if (cache.containsKey(key)) {
                return cache.get(key);
            } else {
                Object result = method.invoke(target, args);
                cache.put(key, result);

                if (cacheAnnotation.persist()) {
                    persistToDisk(key, result);
                }

                return result;
            }
        } else {
            return method.invoke(target, args);
        }
    }

    private String generateCacheKey(Method method, Object[] args) {
        return method.getName() + Arrays.toString(args);
    }

    private void persistToDisk(String key, Object value) {
        try {
            var path = System.getProperty("java.io.tmpdir") + "cache.txt";
            var file = new File(path);
            if (!file.exists() && !file.createNewFile()) {
                return;
            }
            try (var fileWriter = new FileWriter(file)) {
                fileWriter.write(key + ":" + value);
            }
        } catch (Exception ignored) { }
    }
}
