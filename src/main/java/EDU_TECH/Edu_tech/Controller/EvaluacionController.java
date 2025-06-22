package EDU_TECH.Edu_tech.Controller;

import EDU_TECH.Edu_tech.Model.Curso;
import EDU_TECH.Edu_tech.Model.Evaluacion;
import EDU_TECH.Edu_tech.Service.CursoService;
import EDU_TECH.Edu_tech.Service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/edutech/evaluacion")


public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;


    @GetMapping
    public ResponseEntity<List<Evaluacion>> Listar() {
        List<Evaluacion> evaluaciones = evaluacionService.findAll();
        if (evaluaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(evaluaciones);
    }
    @PostMapping
    public ResponseEntity<Evaluacion> guardar(@RequestBody Evaluacion evaluacion) {
        Evaluacion evaluacionNuevo = evaluacionService.save(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluacionNuevo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> buscar(@PathVariable Integer id) {
        try {
            Evaluacion evaluacion = evaluacionService.findById(id);
            return ResponseEntity.ok(evaluacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> actualizar(@PathVariable Integer id, @RequestBody Evaluacion evaluacion) {
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
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            evaluacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

