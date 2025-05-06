package pe.edu.upeu.calcfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.calcfx.modelo.CalCTO;
import pe.edu.upeu.calcfx.repositorio.CalcRepository;

import java.util.ArrayList;
import java.util.List;
@Service

public class CalcServicioImp implements CalcServicioI{

   @Autowired
    private CalcRepository calcRepository;

    @Override
    public void save(CalCTO calCTO) {
        calcRepository.save(calCTO);
    }

    @Override
    public List<CalCTO> findAll() {
        return calcRepository.findAll();
    }

    @Override
    public CalCTO findById(Long index) {
        return calcRepository.findById(index).orElse(new CalCTO());
    }

    @Override
    public void update(CalCTO calCTO, Long index) {
        calCTO.setId(index);
        calcRepository.save(calCTO);
    }

    @Override
    public void delete(CalCTO calCTO) {
        calcRepository.delete(calCTO);
    }

    @Override
    public void deleteById(Long index) {
        calcRepository.deleteById(index);
    }
}
