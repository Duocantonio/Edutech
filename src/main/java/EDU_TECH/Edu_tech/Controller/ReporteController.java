package EDU_TECH.Edu_tech.Controller;

import EDU_TECH.Edu_tech.Model.Curso;
import EDU_TECH.Edu_tech.Model.Reporte;
import EDU_TECH.Edu_tech.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/edutech/reporte")
public class ReporteController{
    @Autowired
    private ReporteService reporteService;


    @GetMapping
    public ResponseEntity<List<Reporte>> Listar() {
        List<Reporte> reportes = reporteService.findAll();
        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }


    @PostMapping
    public ResponseEntity<Reporte> guardar(@RequestBody Reporte reporte) {
        Reporte reporteNuevo = reporteService.save(reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteNuevo);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Reporte> buscar(@PathVariable Integer id) {
        try {
            Reporte reporte = reporteService.findById(id);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizar(@PathVariable Integer id, @RequestBody Reporte reporte) {
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
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            reporteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}