package EduTech.EduTech.Controller;

import EduTech.EduTech.Model.Pago;
import EduTech.EduTech.Service.PagoService;
import EduTech.EduTech.assemblers.PagoModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/pagos")

public class PagoControllerV2 {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PagoModelAssembler assembler;

    // GET: Listar todos los pagos
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Pago>> getAllPagos() {
        List<EntityModel<Pago>> pagos = pagoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pagos,
                linkTo(methodOn(PagoControllerV2.class).getAllPagos()).withSelfRel());
    }

    // GET: Obtener un pago por su ID
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Pago> getPagoById(@PathVariable Long id) {
        Pago pago = pagoService.findById(id);
        if (pago == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pago no encontrado con ID: " + id);
        }
        return assembler.toModel(pago);
    }

    // POST: Crear un nuevo pago
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pago>> createPago(@RequestBody Pago pago) {
        Pago nuevo = pagoService.save(pago);
        return ResponseEntity
                .created(linkTo(methodOn(PagoControllerV2.class).getPagoById(nuevo.getId())).toUri())
                .body(assembler.toModel(nuevo));
    }

    // PUT: Actualizar un pago existente
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pago>> updatePago(@PathVariable Long id, @RequestBody Pago pago) {
        Pago existente = pagoService.findById(id);
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede actualizar. Pago no encontrado.");
        }

        pago.setId(id);
        Pago actualizado = pagoService.save(pago);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    // DELETE: Eliminar un pago por ID
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deletePago(@PathVariable Long id) {
        Pago existente = pagoService.findById(id);
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede eliminar. Pago no encontrado.");
        }

        pagoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
