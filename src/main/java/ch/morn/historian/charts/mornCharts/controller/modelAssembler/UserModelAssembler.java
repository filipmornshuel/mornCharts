package ch.morn.historian.charts.mornCharts.controller.modelAssembler;

import ch.morn.historian.charts.mornCharts.controller.UserController;
import ch.morn.historian.charts.mornCharts.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getOneUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUser()).withRel("users"));
    }
}
