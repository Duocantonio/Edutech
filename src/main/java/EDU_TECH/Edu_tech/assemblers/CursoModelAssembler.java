package EDU_TECH.Edu_tech.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import EDU_TECH.Edu_tech.Controller.CursoControllerV2;
import EDU_TECH.Edu_tech.Model.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
                linkTo(methodOn(CursoControllerV2.class).getCursoById(curso.getId())).withSelfRel(),
                linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withRel("cursos"));
    }
}
