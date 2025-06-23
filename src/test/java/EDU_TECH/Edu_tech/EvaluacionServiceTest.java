package EDU_TECH.Edu_tech;

import EDU_TECH.Edu_tech.Model.Evaluacion;
import EDU_TECH.Edu_tech.Repository.EvaluacionRepository;
import EDU_TECH.Edu_tech.Service.EvaluacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EvaluacionServiceTest {

    @Autowired
    private EvaluacionService evaluacionService;

    @MockitoBean
    private EvaluacionRepository evaluacionRepository;

    @Test
    public void testFindAll() {
        Evaluacion evaluacion = new Evaluacion(1, "Examen Parcial", "Primer examen parcial", LocalDate.now(), 100.0);

        when(evaluacionRepository.findAll()).thenReturn(List.of(evaluacion));

        List<Evaluacion> evaluaciones = evaluacionService.findAll();

        assertNotNull(evaluaciones);
        assertEquals(1, evaluaciones.size());
        assertEquals("Examen Parcial", evaluaciones.get(0).getNombreEvaluacion());
    }

    @Test
    public void testFindById() {
        Evaluacion evaluacion = new Evaluacion(1, "Examen Final", "Examen final del curso", LocalDate.now(), 150.0);

        when(evaluacionRepository.findById(1L)).thenReturn(Optional.of(evaluacion));

        Evaluacion found = evaluacionService.findById(1L);

        assertNotNull(found);
        assertEquals("Examen Final", found.getNombreEvaluacion());
    }

    @Test
    public void testSave() {
        Evaluacion evaluacion = new Evaluacion(null, "Quiz", "Pequeña evaluación", LocalDate.now(), 20.0);
        Evaluacion evaluacionGuardada = new Evaluacion(1, "Quiz", "Pequeña evaluación", LocalDate.now(), 20.0);

        when(evaluacionRepository.save(evaluacion)).thenReturn(evaluacionGuardada);

        Evaluacion saved = evaluacionService.save(evaluacion);

        assertNotNull(saved);
        assertEquals(1, saved.getId());
        assertEquals("Quiz", saved.getNombreEvaluacion());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(evaluacionRepository).deleteById(id);

        evaluacionService.delete(id);

        verify(evaluacionRepository, times(1)).deleteById(id);
    }
}
