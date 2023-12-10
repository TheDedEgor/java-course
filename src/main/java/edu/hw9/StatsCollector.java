package edu.hw9;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector {
    private final Map<String, List<Double>> data = new ConcurrentHashMap<>();

    @SuppressWarnings("ParameterAssignment")
    public void push(String metricName, double[] values) {
        data.compute(metricName, (key, existingValues) -> {
            if (existingValues == null) {
                existingValues = new CopyOnWriteArrayList<>();
            }
            for (double value : values) {
                existingValues.add(value);
            }
            return existingValues;
        });
    }

    public List<MetricStats> stats() {
        try (var executorService = Executors.newFixedThreadPool(data.size())) {
            List<Future<MetricStats>> futures = new CopyOnWriteArrayList<>();
            for (var metricName: data.keySet()) {
                var values = data.get(metricName);

                Callable<MetricStats> task = () -> {
                    double sum = 0;
                    double min = values.get(0);
                    double max = values.get(0);

                    for (var value : values) {
                        sum += value;
                        min = Math.min(min, value);
                        max = Math.max(max, value);
                    }

                    double average = sum / values.size();

                    return new MetricStats(metricName, sum, average, min, max);
                };

                futures.add(executorService.submit(task));
            }

            List<MetricStats> result = new CopyOnWriteArrayList<>();
            for (var future : futures) {
                result.add(future.get());
            }
            return result;
        } catch (Exception ex) {
            return List.of();
        }
    }
}
