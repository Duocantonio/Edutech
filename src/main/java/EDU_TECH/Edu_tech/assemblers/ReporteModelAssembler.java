package EDU_TECH.Edu_tech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import EDU_TECH.Edu_tech.Controller.ReporteControllerV2;
import EDU_TECH.Edu_tech.Model.Reporte;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ReporteModelAssembler implements RepresentationModelAssembler<Reporte, EntityModel<Reporte>> {

    @Override
    public EntityModel<Reporte> toModel(Reporte reporte) {
        return EntityModel.of(reporte,
                linkTo(methodOn(ReporteControllerV2.class).getReporteById(reporte.getId())).withSelfRel(),
                linkTo(methodOn(ReporteControllerV2.class).getAllReportes()).withRel("reportes"));
    }
}
