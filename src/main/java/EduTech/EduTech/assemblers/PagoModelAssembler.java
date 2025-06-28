package EduTech.EduTech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import EduTech.EduTech.Controller.PagoControllerV2;
import EduTech.EduTech.Model.Pago;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
@Component
public class PagoModelAssembler implements RepresentationModelAssembler<Pago, EntityModel<Pago>> {


        @Override
        public EntityModel<Pago> toModel(Pago pago) {
            return EntityModel.of(pago,
                    linkTo(methodOn(PagoControllerV2.class).getPagoById(pago.getId())).withSelfRel(),
                    linkTo(methodOn(PagoControllerV2.class).getAllPagos()).withRel("pagos"));
        }
}


