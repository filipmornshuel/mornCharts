package ch.morn.historian.charts.mornCharts.controller.modelAssembler;

import ch.morn.historian.charts.mornCharts.controller.StationController;
import ch.morn.historian.charts.mornCharts.model.Station;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StationModelAssembler implements RepresentationModelAssembler<Station, EntityModel<Station>> {
    @Override
    public EntityModel<Station> toModel(Station station) {
        return EntityModel.of(station,
                WebMvcLinkBuilder.linkTo(methodOn(StationController.class).getOneStation(station.getId())).withSelfRel(),
                linkTo(methodOn(StationController.class).getAllStation()).withRel("stations"));
    }
}
