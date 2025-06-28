package EDU_TECH.Edu_tech.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name="Reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombreCurso;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Date fechaReporte;






}
