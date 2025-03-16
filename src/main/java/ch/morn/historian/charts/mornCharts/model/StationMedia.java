package ch.morn.historian.charts.mornCharts.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "station_media")
public class StationMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(nullable = false)
    private String fileUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType mediaType;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    public enum MediaType {
        IMAGE, AUDIO
    }
}

