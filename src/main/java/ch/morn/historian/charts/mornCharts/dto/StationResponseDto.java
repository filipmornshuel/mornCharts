package ch.morn.historian.charts.mornCharts.dto;

import java.time.LocalDateTime;

public record StationResponseDto(
        Long id,
        String title,
        String description,
        double latitude,
        double longitude,
        String status,
        String createdBy,
        String approvedBy,
        LocalDateTime createdAt,
        LocalDateTime approvedAt,
        LocalDateTime rejectedAt,
        LocalDateTime deletedAt
) {
}
