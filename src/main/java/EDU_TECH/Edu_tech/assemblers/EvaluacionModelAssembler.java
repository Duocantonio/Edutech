package EDU_TECH.Edu_tech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import EDU_TECH.Edu_tech.Controller.EvaluacionControllerV2;
import EDU_TECH.Edu_tech.Model.Evaluacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionModelAssembler implements RepresentationModelAssembler<Evaluacion, EntityModel<Evaluacion>> {

    @Override
    public EntityModel<Evaluacion> toModel(Evaluacion evaluacion) {
        return EntityModel.of(evaluacion,
                linkTo(methodOn(EvaluacionControllerV2.class).getEvaluacionById(evaluacion.getId())).withSelfRel(),
                linkTo(methodOn(EvaluacionControllerV2.class).getAllEvaluaciones()).withRel("evaluaciones"));
    }
}
