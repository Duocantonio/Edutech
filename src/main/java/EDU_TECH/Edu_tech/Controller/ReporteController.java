package EDU_TECH.Edu_tech.Controller;

import EDU_TECH.Edu_tech.Model.Reporte;
import EDU_TECH.Edu_tech.Service.ReporteService;

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
@RequestMapping("api/v1/edutech/reporte")
@Tag(name = "Reportes", description = "Operaciones para generar y gestionar reportes académicos")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    @Operation(summary = "Listar todos los reportes", description = "Obtiene una lista de todos los reportes existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reportes listados correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay reportes registrados")
    })
    public ResponseEntity<List<Reporte>> Listar() {
        List<Reporte> reportes = reporteService.findAll();
        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo reporte", description = "Registra un nuevo reporte en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reporte creado correctamente")
    })
    public ResponseEntity<Reporte> guardar(@RequestBody Reporte reporte) {
        Reporte reporteNuevo = reporteService.save(reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteNuevo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un reporte por ID", description = "Obtiene los detalles de un reporte específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte encontrado"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<Reporte> buscar(
            @Parameter(description = "ID del reporte a buscar") @PathVariable Long id) {
        try {
            Reporte reporte = reporteService.findById(id);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un reporte", description = "Modifica los datos de un reporte existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<Reporte> actualizar(
            @Parameter(description = "ID del reporte a actualizar") @PathVariable Long id,
            @RequestBody Reporte reporte) {
        try {
            Reporte rep = reporteService.findById(id);
            rep.setId(id);
            rep.setNombreCurso(reporte.getNombreCurso());
            rep.setDescripcion(reporte.getDescripcion());
            rep.setFechaReporte(reporte.getFechaReporte());

            reporteService.save(rep);
            return ResponseEntity.ok(rep);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un reporte", description = "Elimina un reporte existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reporte eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID del reporte a eliminar") @PathVariable Long id) {
        try {
            reporteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
