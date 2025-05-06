package ch.morn.historian.charts.mornCharts.dto;

public record StationCreateDto (
        String title,
        String description,
        double latitude,
        double longitude
) {}

