package EDU_TECH.Edu_tech;

import EDU_TECH.Edu_tech.Model.Reporte;
import EDU_TECH.Edu_tech.Repository.ReporteRepository;
import EDU_TECH.Edu_tech.Service.ReporteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReporteServiceTest {

    @Autowired
    private ReporteService reporteService;

    @MockBean
    private ReporteRepository reporteRepository;

    @Test
    public void testFindAll() {
        Reporte reporte = new Reporte(1, "Curso Java", "Reporte de avance", new Date());

        when(reporteRepository.findAll()).thenReturn(List.of(reporte));

        List<Reporte> reportes = reporteService.findAll();

        assertNotNull(reportes);
        assertEquals(1, reportes.size());
        assertEquals("Curso Java", reportes.get(0).getNombreCurso());
    }

    @Test
    public void testFindById() {
        Reporte reporte = new Reporte(1, "Curso Python", "Reporte final", new Date());

        when(reporteRepository.findById(1L)).thenReturn(Optional.of(reporte));

        Reporte found = reporteService.findById(1L);

        assertNotNull(found);
        assertEquals("Curso Python", found.getNombreCurso());
    }

    @Test
    public void testSave() {
        Reporte reporte = new Reporte(null, "Curso Spring", "Reporte intermedio", new Date());
        Reporte reporteGuardado = new Reporte(1, "Curso Spring", "Reporte intermedio", new Date());

        when(reporteRepository.save(reporte)).thenReturn(reporteGuardado);

        Reporte saved = reporteService.save(reporte);

        assertNotNull(saved);
        assertEquals(1, saved.getId());
        assertEquals("Curso Spring", saved.getNombreCurso());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(reporteRepository).deleteById(id);

        reporteService.delete(id);

        verify(reporteRepository, times(1)).deleteById(id);
    }
}
