package pe.edu.upeu.calcfx.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.calcfx.modelo.CalCTO;

import java.util.ArrayList;
import java.util.List;
@Service

public class CalcServicioImp implements CalcServicioI{

    List<CalCTO> datos=new ArrayList<>();

    @Override
    public void save(CalCTO calCTO) {
       datos.add(calCTO);
    }

    @Override
    public List<CalCTO> findAll() {
        return datos;
    }

    @Override
    public CalCTO findById(int index) {
        return datos.get(index);
    }

    @Override
    public void update(CalCTO calCTO, int index) {
        datos.remove(calCTO);
    }

    @Override
    public void delete(CalCTO calCTO) {
        datos.remove(calCTO);
    }

    @Override
    public void deleteById(int index) {
        datos.remove(index);
    }
}
