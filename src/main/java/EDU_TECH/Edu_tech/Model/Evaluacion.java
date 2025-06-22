package EDU_TECH.Edu_tech.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "Evaluacion")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombreEvaluacion;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaEvaluacion;

    @Column(nullable = false)
    private Double puntajeMaximo;
}
