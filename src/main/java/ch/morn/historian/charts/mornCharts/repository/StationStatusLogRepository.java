package ch.morn.historian.charts.mornCharts.repository;

import ch.morn.historian.charts.mornCharts.model.StationStatusLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationStatusLogRepository extends JpaRepository<StationStatusLog, Long> {
}
