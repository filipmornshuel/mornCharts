package ch.morn.historian.charts.mornCharts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "stations")
@Getter
@Setter
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime approvedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime deletedAt;

    public enum Status {
        PENDING, APPROVED, REJECTED, DELETED
    }

    public Station(String title, String description, double latitude, double longitude, Status status, User createdBy, User approvedBy, LocalDateTime createdAt, LocalDateTime approvedAt, LocalDateTime rejectedAt, LocalDateTime deletedAt) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.createdBy = createdBy;
        this.approvedBy = approvedBy;
        this.createdAt = createdAt;
        this.approvedAt = approvedAt;
        this.rejectedAt = rejectedAt;
        this.deletedAt = deletedAt;
    }
}