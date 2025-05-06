package ch.morn.historian.charts.mornCharts.dto;

public record StationStatusChangeDto(
        Long stationId,
        String newStatus, //  "APPROVED", "REJECTED", "DELETED"
        String changedByUsername
) {
}
