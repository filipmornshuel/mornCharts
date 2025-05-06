package ch.morn.historian.charts.mornCharts.controller;

import ch.morn.historian.charts.mornCharts.controller.modelAssembler.StationModelAssembler;
import ch.morn.historian.charts.mornCharts.exception.StationNotFoundException;
import ch.morn.historian.charts.mornCharts.model.Station;
import ch.morn.historian.charts.mornCharts.repository.StationRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StationController {
    private final StationRepository repository;
    private final StationModelAssembler modelAssembler;

    public StationController(StationRepository repository, StationModelAssembler modelAssembler) {
        this.repository = repository;
        this.modelAssembler = modelAssembler;
    }


    @GetMapping("/stations")
    public CollectionModel<EntityModel<Station>> getAllStation() {
        List<EntityModel<Station>> stations = repository.findAll().stream()
                .map(modelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(stations, linkTo(methodOn(StationController.class).getAllStation()).withSelfRel());
    }

    @PostMapping("/stations")
    public ResponseEntity<EntityModel<Station>> saveNewStation(@RequestBody Station newStation) {
        Station savedStation = repository.save(newStation);

        EntityModel<Station> entityModel = modelAssembler.toModel(savedStation);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/stations/{id}")
    public EntityModel<Station> getOneStation(@PathVariable Long id) {
        Station station = repository.findById(id).orElseThrow(() -> new StationNotFoundException(id));

        return modelAssembler.toModel(station);

    }

    @PutMapping("/stations/{id}")
    public ResponseEntity<?> replaceStation(@RequestBody Station newStation, @PathVariable Long id) {
        Station updatedStation = repository.findById(id)
                .map(station -> {
                    station.(newStation.get());
                    station.setEmail(newStation.getEmail());
                    station.setPasswordHash(newStation.getPasswordHash());
                    station.setRole(newStation.getRole());
                    station.setCreatedAt(newStation.getCreatedAt());
                    return repository.save(station);
                })
                .orElseGet(() -> {
                    newStation.setId(id);
                    return repository.save(newStation);
                });

        EntityModel<Station> entityModel = modelAssembler.toModel(updatedStation);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/stations/{id}")
    public ResponseEntity<?> deleteStation(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
