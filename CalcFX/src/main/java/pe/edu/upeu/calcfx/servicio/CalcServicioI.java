package pe.edu.upeu.calcfx.servicio;

import pe.edu.upeu.calcfx.modelo.CalCTO;

import java.util.List;

public interface CalcServicioI {
    //C
    public void save(CalCTO calCTO);
    //R
    public List<CalCTO> findAll();
    public CalCTO findById(Long index);
    //U
    public void update(CalCTO calCTO, Long index);
    //O
    public void delete(CalCTO calCTO);
    public void deleteById(Long index);
}
