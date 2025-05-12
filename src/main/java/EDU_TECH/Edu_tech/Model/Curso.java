package EDU_TECH.Edu_tech.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="Curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique = true, nullable = false)
    private String nombreCurso;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean estadoCurso;

    @Column(nullable = false)
    private Date fechaInicio;



}
