package EDU_TECH.Edu_tech.Controller;

import EDU_TECH.Edu_tech.Model.Curso;
import EDU_TECH.Edu_tech.Service.CursoService;

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
@RequestMapping("api/v1/edutech/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas con los cursos en EduTech")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(summary = "Listar todos los cursos", description = "Obtiene una lista de todos los cursos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cursos listados correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay cursos registrados")
    })
    public ResponseEntity<List<Curso>> Listar() {
        List<Curso> cursos = cursoService.findAll();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo curso", description = "Registra un nuevo curso en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso creado correctamente")
    })
    public ResponseEntity<Curso> guardar(@RequestBody Curso curso) {
        Curso cursoNuevo = cursoService.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoNuevo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un curso por ID", description = "Obtiene los detalles de un curso según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<Curso> buscar(@Parameter(description = "ID del curso a buscar") @PathVariable Long id) {
        try {
            Curso curso = cursoService.findById(id);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un curso", description = "Modifica los datos de un curso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<Curso> actualizar(
            @Parameter(description = "ID del curso a actualizar") @PathVariable Long id,
            @RequestBody Curso curso) {
        try {
            Curso cur = cursoService.findById(id);
            cur.setId(id);
            cur.setNombreCurso(curso.getNombreCurso());
            cur.setDescripcion(curso.getDescripcion());
            cur.setFechaInicio(curso.getFechaInicio());
            cur.setEstadoCurso(curso.isEstadoCurso());

            cursoService.save(cur);
            return ResponseEntity.ok(cur);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un curso", description = "Elimina un curso existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<?> eliminar(@Parameter(description = "ID del curso a eliminar") @PathVariable Long id) {
        try {
            cursoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
