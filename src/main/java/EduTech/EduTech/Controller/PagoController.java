package EduTech.EduTech.Controller;

import EduTech.EduTech.Model.Pago;
import EduTech.EduTech.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;


@RestController
@RequestMapping("/pagos")
@Tag(name = "Pagos", description = "Operaciones relacionadas con pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    @Operation(summary = "Listar todos los pagos", description = "Obtiene una lista con todos los pagos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay pagos registrados")
    })
    public ResponseEntity<List<Pago>> Listar() {
        List<Pago> pagos = pagoService.findAll();
        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }

    @PostMapping
    @Operation(summary = "Guardar un nuevo pago", description = "Crea y guarda un nuevo pago en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pago creado exitosamente")
    })
    public ResponseEntity<Pago> guardar(@RequestBody Pago pago) {
        Pago pagoNuevo = pagoService.save(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoNuevo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un pago por ID", description = "Retorna un pago específico según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<Pago> buscar(@Parameter(description = "ID del pago a buscar") @PathVariable Long id) {
        try {
            Pago pago = pagoService.findById(id);
            return ResponseEntity.ok(pago);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pago", description = "Elimina un pago existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pago eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<?> elimina(@Parameter(description = "ID del pago a eliminar") @PathVariable Long id) {
        try {
            pagoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

