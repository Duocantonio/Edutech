package EDU_TECH.Edu_tech.Controller;

import EDU_TECH.Edu_tech.Model.Curso;
import EDU_TECH.Edu_tech.Service.CursoService;
import EDU_TECH.Edu_tech.assemblers.CursoModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/cursos")
public class CursoControllerV2 {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Curso>> getAllCursos() {
        List<EntityModel<Curso>> cursos = cursoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursos,
                linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Curso> getCursoById(@PathVariable Long id) {
        Curso curso = cursoService.findById(id);
        return assembler.toModel(curso);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> createCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.save(curso);
        return ResponseEntity
                .created(linkTo(methodOn(CursoControllerV2.class).getCursoById(nuevoCurso.getId())).toUri())
                .body(assembler.toModel(nuevoCurso));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        Curso actualizado = cursoService.save(curso);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
