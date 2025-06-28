package EDU_TECH.Edu_tech.Controller;

import EDU_TECH.Edu_tech.Model.Evaluacion;
import EDU_TECH.Edu_tech.Service.EvaluacionService;
import EDU_TECH.Edu_tech.assemblers.EvaluacionModelAssembler;

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
@RequestMapping("/api/v2/evaluacion")
public class EvaluacionControllerV2 {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private EvaluacionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Evaluacion>> getAllEvaluaciones() {
        List<EntityModel<Evaluacion>> evaluaciones = evaluacionService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(evaluaciones,
                linkTo(methodOn(EvaluacionControllerV2.class).getAllEvaluaciones()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Evaluacion> getEvaluacionById(@PathVariable Long id) {
        Evaluacion evaluacion = evaluacionService.findById(id);
        return assembler.toModel(evaluacion);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Evaluacion>> createEvaluacion(@RequestBody Evaluacion evaluacion) {
        Evaluacion nueva = evaluacionService.save(evaluacion);
        return ResponseEntity
                .created(linkTo(methodOn(EvaluacionControllerV2.class).getEvaluacionById(nueva.getId())).toUri())
                .body(assembler.toModel(nueva));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Evaluacion>> updateEvaluacion(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        evaluacion.setId(id);
        Evaluacion actualizada = evaluacionService.save(evaluacion);
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteEvaluacion(@PathVariable Long id) {
        evaluacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
