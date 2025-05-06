package ch.morn.historian.charts.mornCharts.controller;

import ch.morn.historian.charts.mornCharts.exception.StationNotFoundException;
import ch.morn.historian.charts.mornCharts.model.Station;
import ch.morn.historian.charts.mornCharts.repository.StationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    private final StationRepository repository;

    public StationController(StationRepository repository) {
        this.repository = repository;
    }

    // GET /stations - Alle Stationen abrufen
    @GetMapping
    public List<Station> getAllStations() {
        return repository.findAll();
    }

    // GET /stations/{id} - Eine bestimmte Station abrufen
    @GetMapping("/{id}")
    public ResponseEntity<Station> getOneStation(@PathVariable Long id) {
        Station station = repository.findById(id)
                .orElseThrow(() -> new StationNotFoundException(id));
        return ResponseEntity.ok(station);
    }

    // POST /stations - Neue Station erstellen
    @PostMapping
    public ResponseEntity<Station> saveNewStation(@RequestBody Station newStation) {
        Station savedStation = repository.save(newStation);
        return ResponseEntity.ok(savedStation);
    }

    // PUT /stations/{id} - Station aktualisieren oder neu anlegen
    @PutMapping("/{id}")
    public ResponseEntity<Station> replaceStation(@RequestBody Station newStation, @PathVariable Long id) {
        Station updatedStation = repository.findById(id)
                .map(station -> {
                    // Beispielhafte Setter, bitte durch echte Felder ersetzen
                    station.setName(newStation.getName());
                    station.setLocation(newStation.getLocation());
                    station.setCreatedAt(newStation.getCreatedAt());
                    return repository.save(station);
                })
                .orElseGet(() -> {
                    newStation.setId(id);
                    return repository.save(newStation);
                });

        return ResponseEntity.ok(updatedStation);
    }

    // DELETE /stations/{id} - Station löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new StationNotFoundException(id);
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
