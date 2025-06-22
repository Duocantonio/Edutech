package EDU_TECH.Edu_tech.Service;

import EDU_TECH.Edu_tech.Model.Reporte;
import EDU_TECH.Edu_tech.Repository.ReporteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReporteService {


    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> findAll(){
        return reporteRepository.findAll();
    }


    public Reporte findById(long id){
        return reporteRepository.findById(id).get();

    }

    public Reporte save (Reporte reporte){
        return reporteRepository.save(reporte);

    }

    public void delete (Long id){
        reporteRepository.deleteById(id);
    }



}
