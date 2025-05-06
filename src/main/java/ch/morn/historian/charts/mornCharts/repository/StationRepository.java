package ch.morn.historian.charts.mornCharts.repository;

import ch.morn.historian.charts.mornCharts.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findByStatus(Station.Status status);

}
