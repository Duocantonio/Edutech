package EduTech.EduTech;

import EduTech.EduTech.Model.Pago;
import EduTech.EduTech.Repository.PagoRepository;
import EduTech.EduTech.Service.PagoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Pago crearPago() {
        return new Pago(
                1,                         // ID como Long
                "Juan Perez",
                "Curso Java",
                12345678,                   // Número tarjeta como Integer (revisar entidad)
                "Visa"
        );
    }

    @Test
    public void testFindAll() {
        Pago pago = crearPago();
        when(pagoRepository.findAll()).thenReturn(List.of(pago));

        List<Pago> pagos = pagoService.findAll();

        assertNotNull(pagos);
        assertEquals(1, pagos.size());
        assertEquals(pago.getId(), pagos.get(0).getId());
    }

    @Test
    public void testFindByIdExistente() {
        Pago pago = crearPago();
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));

        Pago encontrado = pagoService.findById(1L);

        assertNotNull(encontrado);
        assertEquals(pago.getId(), encontrado.getId());
    }

    @Test
    public void testFindByIdNoExistente() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            pagoService.findById(1L);
        });
    }

    @Test
    public void testSave() {
        Pago pago = crearPago();
        when(pagoRepository.save(pago)).thenReturn(pago);

        Pago guardado = pagoService.save(pago);

        assertNotNull(guardado);
        assertEquals(pago.getNombreTarjeta(), guardado.getNombreTarjeta());
    }

    @Test
    public void testDeleteExistente() {
        when(pagoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(pagoRepository).deleteById(1L);

        assertDoesNotThrow(() -> pagoService.delete(1L));

        verify(pagoRepository, times(1)).deleteById(1L);
    }


}
