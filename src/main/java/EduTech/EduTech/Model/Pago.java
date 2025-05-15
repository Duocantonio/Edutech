package EduTech.EduTech.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombreTarjeta;

    @Column(nullable = false)
    private String Pagocurso;

    @Column(nullable = false)
    private Integer numeroTarjeta;

    @Column(nullable = false)
    private String tipoTarjeta;

}
