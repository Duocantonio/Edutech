package EDU_TECH.Edu_tech;

import EDU_TECH.Edu_tech.Model.Curso;
import EDU_TECH.Edu_tech.Repository.CursoRepository;
import EDU_TECH.Edu_tech.Service.CursoService;
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
public class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @MockBean
    private CursoRepository cursoRepository;

    @Test
    public void testFindAll() {
        Curso curso = new Curso(1, "Matemáticas", "Curso básico", true, new Date(), 1234);

        when(cursoRepository.findAll()).thenReturn(List.of(curso));

        List<Curso> cursos = cursoService.findAll();

        assertNotNull(cursos);
        assertEquals(1, cursos.size());
        assertEquals("Matemáticas", cursos.get(0).getNombreCurso());
    }

    @Test
    public void testFindById() {
        Curso curso = new Curso(1, "Física", "Curso intermedio", true, new Date(), 5678);
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        Curso found = cursoService.findById(1L);

        assertNotNull(found);
        assertEquals("Física", found.getNombreCurso());
    }

    @Test
    public void testSave() {
        Curso curso = new Curso(1, "Química", "Curso avanzado", true, new Date(), 9876);
        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso saved = cursoService.save(curso);

        assertNotNull(saved);
        assertEquals("Química", saved.getNombreCurso());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;

        doNothing().when(cursoRepository).deleteById(id);

        cursoService.delete(id);

        verify(cursoRepository, times(1)).deleteById(id);
    }
}
