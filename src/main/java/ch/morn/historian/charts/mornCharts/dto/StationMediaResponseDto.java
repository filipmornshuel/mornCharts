package ch.morn.historian.charts.mornCharts.dto;

import java.time.LocalDateTime;

public record StationMediaResponseDto(
        Long id,
        String fileUrl,
        String mediaType,
        LocalDateTime uploadedAt
) {
}
