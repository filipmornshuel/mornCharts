package ch.morn.historian.charts.mornCharts.dto;

import org.springframework.web.multipart.MultipartFile;

public record StationMediaUploadDto (
        Long stationId,
        MultipartFile file,
        String mediaType // "IMAGE" oder "AUDIO"
) {
}
