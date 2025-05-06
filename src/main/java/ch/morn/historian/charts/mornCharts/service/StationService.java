package ch.morn.historian.charts.mornCharts.service;

import ch.morn.historian.charts.mornCharts.dto.StationCreateDto;
import ch.morn.historian.charts.mornCharts.dto.StationResponseDto;
import ch.morn.historian.charts.mornCharts.exception.UserNotFoundException;
import ch.morn.historian.charts.mornCharts.model.Station;
import ch.morn.historian.charts.mornCharts.model.User;
import ch.morn.historian.charts.mornCharts.repository.StationRepository;
import ch.morn.historian.charts.mornCharts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;
    private final UserRepository userRepository;

    public Station createStation(StationCreateDto dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Station station = new Station();
        station.setTitle(dto.title());
        station.setDescription(dto.description());
        station.setLatitude(dto.latitude());
        station.setLongitude(dto.longitude());
        station.setStatus(Station.Status.PENDING);
        station.setCreatedBy(user);
        station.setCreatedAt(LocalDateTime.now());

        return stationRepository.save(station);
    }

    public List<StationResponseDto> getAllApprovedStations() {
        List<Station> approvedStations = stationRepository.findByStatus(Station.Status.APPROVED);
        return approvedStations.stream()
                .map(station -> new StationResponseDto(
                        station.getId(),
                        station.getTitle(),
                        station.getDescription(),
                        station.getLatitude(),
                        station.getLongitude(),
                        station.getStatus().name(),
                        station.getCreatedBy().getUsername(),
                        station.getApprovedBy() != null ? station.getApprovedBy().getUsername() : null,
                        station.getCreatedAt(),
                        station.getApprovedAt(),
                        station.getRejectedAt(),
                        station.getDeletedAt()
                ))
                .collect(Collectors.toList());
    }

}
