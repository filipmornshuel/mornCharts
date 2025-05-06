package ch.morn.historian.charts.mornCharts.dto;

import java.time.LocalDateTime;

public record StationStatusLogDto(
        String status,
        String changedBy,
        LocalDateTime changedAt
) {
}
