package EDU_TECH.Edu_tech.Service;


import EDU_TECH.Edu_tech.Model.Curso;
import EDU_TECH.Edu_tech.Repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    public List<Curso> findAll(){
        return cursoRepository.findAll();
    }


    public Curso findById(long id){
        return cursoRepository.findById(id).get();

    }

    public Curso save (Curso curso){
        return cursoRepository.save(curso);

    }

    public void delete (Long id){
        cursoRepository.deleteById(id);
    }



}
