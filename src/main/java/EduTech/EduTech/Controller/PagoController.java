package EduTech.EduTech.Controller;

import EduTech.EduTech.Model.Pago;
import EduTech.EduTech.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/edutech/pagos")


public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<Pago>>Listar(){
        List<Pago>pagos=pagoService.findAll();
        if(pagos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
    return ResponseEntity.ok(pagos);
    }

    @PostMapping
    public ResponseEntity<Pago> guardar(@RequestBody Pago pago){
        Pago pagoNuevo=pagoService.save(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago>buscar(@PathVariable Long id){
        try{
            Pago pago = pagoService.findById(id);
            return ResponseEntity.ok(pago);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>elimina(@PathVariable Long id) {
        try {
            pagoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }










}
