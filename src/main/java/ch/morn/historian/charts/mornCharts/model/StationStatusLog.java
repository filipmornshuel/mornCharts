package ch.morn.historian.charts.mornCharts.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "station_status_log")
public class StationStatusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Station.Status status;

    @ManyToOne
    @JoinColumn(name = "changed_by", nullable = false)
    private User changedBy;

    private LocalDateTime changedAt = LocalDateTime.now();

}
