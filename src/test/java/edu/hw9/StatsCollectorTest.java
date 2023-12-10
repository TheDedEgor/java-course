package edu.hw9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatsCollectorTest {

    @Test
    @DisplayName("Вычисление статистики 1")
    void metricStatsTest1() {
        var collector = new StatsCollector();
        collector.push("metric_1", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});

        for (var metric: collector.stats()) {
            assertThat(metric.metricName()).isEqualTo("metric_1");
            assertThat(metric.min()).isEqualTo(0.05);
            assertThat(metric.max()).isEqualTo(5.1);
            assertThat(metric.average()).isEqualTo(1.39);
        }
    }

    @Test
    @DisplayName("Вычисление статистики 2")
    void metricStatsTest2() {
        var collector = new StatsCollector();
        collector.push("metric_2", new double[] {2.0, 3.5, 1.8, 4.2, 0.7});

        for (var metric: collector.stats()) {
            assertThat(metric.metricName()).isEqualTo("metric_2");
            assertThat(metric.min()).isEqualTo(0.7);
            assertThat(metric.max()).isEqualTo(4.2);
            assertThat(metric.average()).isEqualTo(2.44);
        }
    }
}
