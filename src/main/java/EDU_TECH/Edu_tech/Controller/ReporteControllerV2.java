package EDU_TECH.Edu_tech.Controller;

import EDU_TECH.Edu_tech.Model.Reporte;
import EDU_TECH.Edu_tech.Service.ReporteService;
import EDU_TECH.Edu_tech.assemblers.ReporteModelAssembler;

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
@RequestMapping("/api/v2/reportes")
public class ReporteControllerV2 {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private ReporteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reporte>> getAllReportes() {
        List<EntityModel<Reporte>> reportes = reporteService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(reportes,
                linkTo(methodOn(ReporteControllerV2.class).getAllReportes()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Reporte> getReporteById(@PathVariable Long id) {
        Reporte reporte = reporteService.findById(id);
        return assembler.toModel(reporte);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reporte>> createReporte(@RequestBody Reporte reporte) {
        Reporte nuevo = reporteService.save(reporte);
        return ResponseEntity
                .created(linkTo(methodOn(ReporteControllerV2.class).getReporteById(nuevo.getId())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reporte>> updateReporte(@PathVariable Long id, @RequestBody Reporte reporte) {
        reporte.setId(id);
        Reporte actualizado = reporteService.save(reporte);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteReporte(@PathVariable Long id) {
        reporteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
