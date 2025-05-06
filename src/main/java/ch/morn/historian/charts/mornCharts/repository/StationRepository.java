package ch.morn.historian.charts.mornCharts.repository;

import ch.morn.historian.charts.mornCharts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<User, Long> {
}
