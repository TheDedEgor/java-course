package edu.project3;

import java.util.List;
import java.util.Map;

public record LogStatistics(Integer requestCount, Long responseAverageSize,
                            List<Map.Entry<String, Long>> requestedResources,
                            List<Map.Entry<HttpStatus, Long>> responseCodes) {
}
