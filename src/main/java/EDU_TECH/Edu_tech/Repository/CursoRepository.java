package EDU_TECH.Edu_tech.Repository;


import EDU_TECH.Edu_tech.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
