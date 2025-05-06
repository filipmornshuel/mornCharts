package ch.morn.historian.charts.mornCharts.dto;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String role,
        LocalDateTime createdAt
) {
}
