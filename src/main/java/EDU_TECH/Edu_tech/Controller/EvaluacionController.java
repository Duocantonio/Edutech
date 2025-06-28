package EDU_TECH.Edu_tech.Controller;

import EDU_TECH.Edu_tech.Model.Evaluacion;
import EDU_TECH.Edu_tech.Service.EvaluacionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/edutech/evaluacion")
@Tag(name = "Evaluaciones", description = "Operaciones relacionadas con evaluaciones académicas")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    @Operation(summary = "Listar todas las evaluaciones", description = "Obtiene una lista de todas las evaluaciones registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluaciones listadas correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay evaluaciones registradas")
    })
    public ResponseEntity<List<Evaluacion>> Listar() {
        List<Evaluacion> evaluaciones = evaluacionService.findAll();
        if (evaluaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(evaluaciones);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva evaluación", description = "Registra una nueva evaluación en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evaluación creada exitosamente")
    })
    public ResponseEntity<Evaluacion> guardar(@RequestBody Evaluacion evaluacion) {
        Evaluacion evaluacionNuevo = evaluacionService.save(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluacionNuevo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar evaluación por ID", description = "Obtiene los detalles de una evaluación específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
            @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    public ResponseEntity<Evaluacion> buscar(
            @Parameter(description = "ID de la evaluación a buscar") @PathVariable Integer id) {
        try {
            Evaluacion evaluacion = evaluacionService.findById(id);
            return ResponseEntity.ok(evaluacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una evaluación", description = "Modifica los datos de una evaluación existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluación actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    public ResponseEntity<Evaluacion> actualizar(
            @Parameter(description = "ID de la evaluación a actualizar") @PathVariable Long id,
            @RequestBody Evaluacion evaluacion) {
        try {
            Evaluacion eva = evaluacionService.findById(id);
            eva.setId(id);
            eva.setNombreEvaluacion(evaluacion.getNombreEvaluacion());
            eva.setDescripcion(evaluacion.getDescripcion());
            eva.setFechaEvaluacion(evaluacion.getFechaEvaluacion());
            eva.setPuntajeMaximo(evaluacion.getPuntajeMaximo());

            evaluacionService.save(eva);
            return ResponseEntity.ok(eva);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una evaluación", description = "Elimina una evaluación mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evaluación eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID de la evaluación a eliminar") @PathVariable Long id) {
        try {
            evaluacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
