package EDU_TECH.Edu_tech.Service;

import EDU_TECH.Edu_tech.Model.Evaluacion;
import EDU_TECH.Edu_tech.Model.Reporte;
import EDU_TECH.Edu_tech.Repository.EvaluacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> findAll(){
        return evaluacionRepository.findAll();
    }


    public Evaluacion findById(long id){
        return evaluacionRepository.findById(id).get();

    }

    public Evaluacion save (Evaluacion evaluacion){
        return evaluacionRepository.save(evaluacion);

    }

    public void delete (Long id){
        evaluacionRepository.deleteById(id);
    }

}
