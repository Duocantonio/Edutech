package edutech.edutech;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edutech.edutech.modelo.Usuario;
import edutech.edutech.repository.UsuarioRepository;
import edutech.edutech.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll() {
        Usuario usuario = new Usuario(1L, "Juan", "juan@email.com", "1234", "Estudiante");
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> usuarios = usuarioService.findAll();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("Juan", usuarios.get(0).getNombre());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "Juan", "juan@email.com", "1234", "Estudiante");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.findById(id);
        assertNotNull(found);
        assertEquals("Juan", found.getNombre());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario(1L, "Juan", "juan@email.com", "1234", "Estudiante"); // <-- corregido
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario saved = usuarioService.save(usuario);
        assertNotNull(saved);
        assertEquals("juan@email.com", saved.getCorreo());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.delete(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
